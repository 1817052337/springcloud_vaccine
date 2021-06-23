package com.rj.bd.Vaccine.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rj.bd.Vaccine.dao.VaccineMapper;
import com.rj.bd.Vaccine.entity.User;
import com.rj.bd.Vaccine.entity.Userinfo;
import com.rj.bd.utils.*;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("vaccineservice")
public class VaccineService  {
    Jedis jedis= JedisPoolUtils.getJedisSession();
    @Autowired
    private VaccineMapper vaccineMapper;


    //登录
    public Map<String, Object> login(String phone, String code1) {
        if (phone == null || phone.equals("")) {
            return  JsonUtil.sendJson(-1, "请输入手机号", null, null);
        }
        if (code1 == null || code1.equals("")) {
           return JsonUtil.sendJson(-1, "请输入验证码", null, null);
        }
        String phoneis = jedis.get(phone);
        if (phoneis == null) {
            jedis.close();
           return   JsonUtil.sendJson(-1, "验证码已过期请重新发送", null, null);
        } else if (code1.equals(phoneis)) {
            QueryWrapper<User> queryWrapper = Wrappers.query();
            queryWrapper.eq("phone", phone);
            User user = vaccineMapper.selectOne(queryWrapper);
            System.out.println("user=="+user);
            if (user == null) {
                return JsonUtil.sendJson(-1, "用户信息异常", null, null);
            }
            //生成MD5
            String md5 = VerificationUtil.getMD5(phone + code1 + user.getUid() + UUID.randomUUID().toString());
            Map<String, Object> map = new HashMap<>();
            map.put("uid", user.getUid());
            map.put("phone", user.getPhone());
            //存入对象
            JedisPoolUtils.setMap(jedis, md5, map);
            map.put("token", md5);
            //设置过期时间
            jedis.expire(md5, 60 * 60);
            //关闭连接池
            jedis.close();
            return JsonUtil.sendJson(0, "登陆成功",map , null);
        } else {
            jedis.close();
            return JsonUtil.sendJson(-1, "验证码不正确", null, null);
        }
    }



    //发送验证码
    public Map<String, Object> yanzhengma(String phone) throws TencentCloudSDKException {
        String code= SendCodeUtils.createdCode();
        System.out.println("验证码是："+code);
        SendCodeUtils.send(phone,code);
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("phone", phone);
        User user = vaccineMapper.selectOne(queryWrapper);
        if (user==null){
            User user2=new User();
            user2.setPhone(phone);
            vaccineMapper.insert(user2);
        }else{
            //存入
            jedis.set(phone,code);
            //设置过期时间
            jedis.expire(phone, 300);
            jedis.close();
        }
    return  JsonUtil.sendJson(0,"发送成功",null,null);
    }



    }



