package pro.hu.xm.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.admin.entity.Admin;
import pro.hu.xm.admin.service.ADMINervice;
import pro.hu.xm.framework.JsonUtils;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@RestController
@RequestMapping("admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    ADMINervice admiNervice;

    /**
     * @desc  管理员登陆接口
     * @param adminname
     * @param adminpwd
     * @return
     */
    @RequestMapping("/login")
    public Map<String,Object> login(String adminname,String adminpwd){

        // 验证账号密码是否非法
        if (("".equals(adminname) || adminname == null) || ("".equals(adminpwd) || adminpwd == null)){
            return JsonUtils.toJson("登陆失败,账号密码为空",-1,"");
        }

        Admin admin = new Admin();
        admin.setAdminname(adminname);
        admin.setAdminpwd(adminpwd);
        return admiNervice.login(admin);

    }

}

