package pro.hu.xm.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import pro.hu.xm.admin.entity.Admin;
import pro.hu.xm.admin.mapper.AdminMapper;
import pro.hu.xm.admin.service.ADMINervice;
import org.springframework.stereotype.Service;
import pro.hu.xm.framework.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@Service("adminService")
public class AdminServiceImpl implements ADMINervice {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * @desc  管理员登陆m层
     * @param admin
     * @return
     */
    @Override
    public Map<String, Object> login(Admin admin) {

        QueryWrapper<Admin> queryWrapper = Wrappers.query();
        queryWrapper.eq("adminname",admin.getAdminname());
        queryWrapper.eq("adminpwd",admin.getAdminpwd());
        Admin admin_login = adminMapper.selectOne(queryWrapper);
        // mysql查询数据 判断是否查到 查到则生成token并返回
        if (admin_login != null){
            String token = IdUtil.simpleUUID();
            stringRedisTemplate.opsForValue().set(token,admin_login.getAdminname(),2, TimeUnit.HOURS);
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            return JsonUtils.toJson("登陆成功",0,map);
        }
        return JsonUtils.toJson("账号密码错误，登陆失败",-1,"");
    }

    /**
     * @desc  管理员登陆状态验证
     * @param token
     * @return
     */
    @Override
    public boolean verification(String token) {
        // 查询redis库中是否有这条token
        // 有则返回true 无则返回false
        try {
            if (stringRedisTemplate.keys(token).size() == 0){
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        stringRedisTemplate.expire(token,2,TimeUnit.HOURS);
        return true;
    }


}
