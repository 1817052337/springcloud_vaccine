package com.rj.bd.Vaccine.service;

import com.rj.bd.Vaccine.entity.User;
import com.rj.bd.utils.JedisPoolUtils;
import com.rj.bd.utils.JsonUtil;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc  token 权限认证操作
 */
public class TokenService {

    /**
     * @desc 验证token  如果是正确的则返回User 对象 否则返回unll
     * @param token
     * @return
     */
    public static User verifyToken(String token ){
        if (token==null)
            return null;
        Jedis jedis = JedisPoolUtils.getJedisSession();
        Map<String, String> map = JedisPoolUtils.getMap(jedis, token);
        //如果没取到值 直接返回空对象
        if (map==null)
            return null;
        //重新设置token的过期时间
        jedis.expire(token, 60*60);
        jedis.close();
        User user = new User();

        //再次认证uid 为空则 是未登录
        if (map.get("uid")==null)
            return null;
        user.setUid(Integer.parseInt(map.get("uid")));
        user.setPhone(map.get("phone"));
        return user;
    }

    /**
     * @desc 普通用户认证   用户权限认证
     * @param token 用户令牌
     * @return  如果 不返回null 则 直接返回给前端
     */
    public static Map<String, Object> getUserPower(String token){
        //权限认证 服务
        if (token ==null)
            return JsonUtil.sendJson(-1,"未登录",null,null);
        User user = TokenService.verifyToken(token);
        if (user == null) {
            return JsonUtil.sendJson(-1,"登录已过期",null,null);
        }


        return null;
    }
}
