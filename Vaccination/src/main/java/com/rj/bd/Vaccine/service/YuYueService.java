package com.rj.bd.Vaccine.service;

import com.rj.bd.Vaccine.dao.AddYuYueMapper;
import com.rj.bd.Vaccine.dao.YuYueMapper;
import com.rj.bd.Vaccine.entity.Subscribe;
import com.rj.bd.Vaccine.entity.User;
import com.rj.bd.Vaccine.entity.Userinfo;
import com.rj.bd.utils.JedisPoolUtils;
import com.rj.bd.utils.JsonUtil;
import com.rj.bd.utils.Putdata;
import io.lettuce.core.ScriptOutputType;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ZC
 * @desc
 * @time 2021--06--02 9:02
 */
@Service("yuyueservice")
public class YuYueService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private YuYueMapper yuYueMapper;
    @Autowired
            private AddYuYueMapper addYuYueMapper;
    String uid;
    int state;
    String hvid;

    public Map<String, Object> addxinxi(String token, Userinfo info,String regionareaid) {
        System.out.println("regionareaid:"+regionareaid);
        //认证用户权限
      Map<String, Object> power = TokenService.getUserPower(token);
        if (power !=null){
            return power;
        }
        if (info.getName() == null || info.getName().equals("")) {
            return JsonUtil.sendJson(-1, "请填写姓名", null, null);
        } else if (info.getNumber() == null || info.getNumber().equals("")) {
            return JsonUtil.sendJson(-1, "请填写身份证号", null, null);
        } else if (info.getRegionprovinceid() == null || info.getRegionprovinceid().equals("")) {
            return JsonUtil.sendJson(-1, "省", null, null);
        } else if (info.getRegioncityid() == null || info.getRegioncityid().equals("")) {
            return JsonUtil.sendJson(-1, "市", null, null);
        } else if (info.getRegionareaid() == null || info.getRegionareaid().equals("")) {
            return JsonUtil.sendJson(-1, "县", null, null);

        }

        Jedis jedis=JedisPoolUtils.getJedisSession();
        Map<String, String> map = jedis.hgetAll(token);
        String uid = map.get("uid").toString();
            info.setUid(uid);

        yuYueMapper.insert(info);

        yuYueMapper.edit(uid, state);

        return JsonUtil.sendJson(0, "保存成功", null, null);
    }

    public Map<String, Object> querysheng(String token) {
        //认证用户权限
   /*   Map<String, Object> power = TokenService.getUserPower(token);
        if (power !=null){
            return power;
        }*/
        List<Map<String, Object>> list = yuYueMapper.querysheng();
        Set result = new HashSet(list);
        System.out.println("result==" + result);
        return JsonUtil.sendJson(0, "请求成功", null, list);
    }

    public Map<String, Object> queryshi(String token, String sheng) {
        //认证用户权限
    /*  Map<String, Object> power = TokenService.getUserPower(token);
        if (power !=null){
            return power;
        }*/
        List<Map<String, Object>> list2 = yuYueMapper.queryshi(sheng);
        for (int i = 0; i < list2.size(); i++) {
            Map<String, Object> map = list2.get(i);
            map.get("province_code");
            String province_code = map.get("province_code").toString();
            System.out.println("province_code===" + province_code);
            List<Map<String, Object>> list = yuYueMapper.queryshi2(province_code);
            return JsonUtil.sendJson(0, "请求成功", null, list);
        }

        return null;
    }

    public Map<String, Object> queryxian(String token, String shi) {
        //认证用户权限
    /*  Map<String, Object> power = TokenService.getUserPower(token);
        if (power !=null){
            return power;
        }*/
        List<Map<String, Object>> list2 = yuYueMapper.queryxian(shi);
        for (int i = 0; i < list2.size(); i++) {
            Map<String, Object> map = list2.get(i);
            map.get("city_code");

            String city_code = map.get("city_code").toString();
            System.out.println("city_code==" + city_code);
            List<Map<String, Object>> list = yuYueMapper.queryxian2(city_code);
            return JsonUtil.sendJson(0, "请求成功", null, list);
        }
        return null;
    }


    public Map<String, Object> queryyiyuan(String token, String shi) {
        List<Map<String, Object>> list = yuYueMapper.queryyiyuan(shi);
        return JsonUtil.sendJson(0, "请求成功", null, list);

    }

    public Map<String, Object> queryshengxinxi(String token) {
        List<Map<String, Object>> list = yuYueMapper.queryshengxinxi();
        return JsonUtil.sendJson(0, "获取成功", null, list);
    }

    public Map<String, Object> queryshixinxi(String token, String regionprovinceid) {
        Map<String, Object> map = yuYueMapper.queryshixinxi(regionprovinceid);
        String province_code = map.get("province_code").toString();
        List<Map<String, Object>> list = yuYueMapper.queryshixinxi2(province_code);
        return JsonUtil.sendJson(0, "获取成功", null, list);
    }

    public Map<String, Object> queryxianxinxi(String token, String regioncityid) {
        System.out.println("regioncityid==" + regioncityid);
        Map<String, Object> map = yuYueMapper.queryxianxinxi(regioncityid);
        String city_code = map.get("city_code").toString();
        List<Map<String, Object>> list = yuYueMapper.queryxianxinxi2(city_code);
        return JsonUtil.sendJson(0, "获取成功", null, list);
    }

    public Map<String, Object> yuyuecode(String token) {
        Jedis jedis=JedisPoolUtils.getJedisSession();
        Map<String, String> map = jedis.hgetAll(token);
        String uid = map.get("uid").toString();

        Map<String,Object> map1=yuYueMapper.querycode(uid);
        int code=Integer.parseInt(map1.get("state").toString()+"");
       if (code==1){
            JsonUtil.sendJson(0, "可以预约", null, null);
        }else{
            return JsonUtil.sendJson(-1, "请先完善信息", null, null);
        }
       return  null;

    }

    public Map<String, Object> addyuyue(String token, String yy, String vid) {

        System.out.println("vid=="+vid);
        System.out.println("yy=="+yy);
        Jedis jedis=JedisPoolUtils.getJedisSession();
        Map<String, String> map = jedis.hgetAll(token);
        String uid = map.get("uid").toString();
        System.out.println("uid=="+uid);
        List<Map<String,Object>> list2=yuYueMapper.queryhid(yy,vid);
        for (int i = 0;i<list2.size();i++){
            Map<String,Object>map1=list2.get(i);
           hvid=map1.get("hvid").toString();
        }




 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = sdf.format(date);
        Subscribe subscribe=new Subscribe();
        subscribe.setHvid(hvid);
        subscribe.setUid(uid);
        subscribe.setTime(time);


            addYuYueMapper.insert(subscribe);







        return  JsonUtil.sendJson(0,"预约成功", null,null);
    }

    public Map<String, Object> querygeren(String token) {
        Jedis jedis=JedisPoolUtils.getJedisSession();
        Map<String, String> map = jedis.hgetAll(token);
        String uid = map.get("uid").toString();
        List<Map<String,Object>> list = yuYueMapper.querygeren(uid);
        System.out.println(list);
        return  JsonUtil.sendJson(0,"获取成功",null,list);
    }

    public Map<String, Object> xinxiupdate(String token, Userinfo info) {

        int insert = yuYueMapper.updateById(info);
        if (insert>0){
            return Putdata.printf(0,"修改成功",null);
        }
        return Putdata.printf(-1,"修改失败",null);



    }
}