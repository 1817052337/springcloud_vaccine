package com.rj.bd.admin.controller;

import com.rj.bd.admin.entity.Admin;
import com.rj.bd.admin.service.IAdminervice;
import com.rj.bd.utils.JedisPoolUtils;
import com.rj.bd.utils.JsonUtils;
import com.rj.bd.utils.RanImgCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wxy
 * @desc admin控制层
 * @time 2021-06-01-9:57
 */
@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private IAdminervice adminervice;

    Jedis jedis = JedisPoolUtils.getJedisSession();

    /**
     * @param adminname
     * @param adminpwd
     * @return 登录成功的json
     * @desc 通过管理员账号+密码进行登录
     */
    @RequestMapping("/login")
    @CrossOrigin
    public Map<String, Object> adminLogin(String adminname, String adminpwd, String captcha) {
        //管理员账号+密码进行登录
        List<Admin> list = adminervice.adminLogin(adminname, adminpwd);
        if (list!=null||list.equals("")){
            String code=jedis.get("Code");
            if (captcha.equals(code)){
                return JsonUtils.toJson("登录成功", 0);
            }else {
                return JsonUtils.toJson("登录失败,验证码失效,请重新登录。", 1);
            }
        }else {
            return JsonUtils.toJson("登录失败,验证码失效,请重新登录。", 1);
        }
    }

    @RequestMapping("/code")
    @CrossOrigin
    public Map<String, Object> code() throws IOException {
        //生成验证码工具类
        RanImgCodeUtils r = new RanImgCodeUtils();
        //验证码图片名（传到页面）
        String codeimg = UUID.randomUUID() + ".jpg";
        //验证码图片的地址（存放到静态页面）
        String codeimgadd = "E:\\疫苗接种项目\\子项目\\前端\\yimiaoAdmin\\imgs\\" + codeimg;
        //将上述地址存入静态页面中的imgs里面
        r.output(r.getImage(), new FileOutputStream(codeimgadd));
        //取出生成的验证码
        String code = r.getText();
        //生成前清除缓存信息
        jedis.del("Code");
        //生成验证码存redis中的code
        jedis.set("Code", code);
        //缓存时间
        jedis.expire("Code", 60);
        //创建map容器
        Map map = new HashMap();
        map.put("code", code);
        map.put("codeimg", codeimg);
        //将验证码以及其图片地址传入网页
        return JsonUtils.toJson("请求成功", 0, map);
    }

}
