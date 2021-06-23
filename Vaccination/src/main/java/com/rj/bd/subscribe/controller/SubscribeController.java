package com.rj.bd.subscribe.controller;



import com.rj.bd.subscribe.entity.subscribe;
import com.rj.bd.subscribe.service.SubscribeService;
import com.rj.bd.utils.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    /**
     * 分页&条件查询
     */

    @RequestMapping("/subscribe/query")
    public Map<String,Object> query(@RequestParam Map maps, HttpServletRequest request,String token) {
        System.out.println("HospitalController--->query()");
        String detail=request.getParameter("detail");
        if (detail==null){
            detail="";
        }


        Jedis jedis=JedisPoolUtils.getJedisSession();
        Map<String, String> map1 = jedis.hgetAll(token);
        String uid = map1.get("uid").toString();
        List<Map> Page= subscribeService.getPage(maps,detail,uid);
       List<subscribe> list=subscribeService.queryAll(uid);
        System.out.println("page=="+Page);
        System.out.println("list=="+list);
        int count=list.size();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("count",count);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("data",Page);
        return map;

    }

    /**
     * @删除
     */
    @RequestMapping("/subscribe/delete")
    public Map<String,Object> delete(HttpServletRequest request){
        System.out.println("SubscribeController--->delete()");
        Map<String,Object> map=new HashMap<String,Object>();
        String sid=request.getParameter("sid");
        if (sid==null){
            map.put("code",-1);
            map.put("msg","请选择要删除的订单");
            return map;

        }
        subscribeService.deletetById(sid);
        map.put("code",0);
        map.put("msg","请求成功");

        return map;

    }


}
