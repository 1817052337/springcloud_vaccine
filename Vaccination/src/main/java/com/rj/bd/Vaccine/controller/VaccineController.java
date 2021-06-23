package com.rj.bd.Vaccine.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rj.bd.Vaccine.dao.YuYueMapper;
import com.rj.bd.Vaccine.entity.User;
import com.rj.bd.Vaccine.entity.Userinfo;
import com.rj.bd.Vaccine.entity.Vaccinetype;
import com.rj.bd.Vaccine.service.VaccineService;
import com.rj.bd.Vaccine.service.VaccineTypeService;
import com.rj.bd.Vaccine.service.YuYueService;
import com.rj.bd.utils.JedisPoolUtils;
import com.rj.bd.utils.JsonUtil;
import com.rj.bd.utils.SendCodeUtils;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ecm.v20190719.models.City;
import javafx.scene.input.ScrollEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

@RestController
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;
    @Autowired
    private VaccineTypeService vaccineTypeService;
    @Autowired
    private YuYueService yuYueService;
    @Autowired
    private RedisTemplate redisTemplate;



    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/login",method = RequestMethod.GET)
    public Map<String,Object> login(String phone,String code1){
        return  vaccineService.login(phone,code1);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/yzm",method = RequestMethod.GET)
    public Map<String,Object> yanzhengma(String phone) throws TencentCloudSDKException {
        return  vaccineService.yanzhengma(phone);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/queryym",method = RequestMethod.GET)
    public  Map<String,Object> queryym(String like,String token,Integer page,Integer limit){
        return  vaccineTypeService.queryym(like==null?"":like,token,page,limit);
    }
    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/sheng",method = RequestMethod.GET)
    public  Map<String,Object> querysheng(String token){
        return yuYueService.querysheng(token);
    }
    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/shi",method = RequestMethod.GET)
    public  Map<String,Object> queryshi(String token,String sheng){
        return yuYueService.queryshi(token,sheng);
    }
    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/xian",method = RequestMethod.GET)
    public  Map<String,Object> queryxian(String token,String shi){
        return yuYueService.queryxian(token,shi);
    }



    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/yiyuan",method = RequestMethod.GET)
    public  Map<String,Object> queryyiyuan(String token,String shi){
        return yuYueService.queryyiyuan(token,shi);
    }
    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/yuyue",method = RequestMethod.POST)
    public Map<String,Object> addxinxi(String token,Userinfo info,String regionareaid){
           return yuYueService.addxinxi(token,info,regionareaid);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/yuyuecode",method = RequestMethod.POST)
    public Map<String,Object> yuyuecode(String token){
        return yuYueService.yuyuecode(token);
    }






    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/shengxinxi",method = RequestMethod.GET)
    public  Map<String,Object> queryshengxinxi(String token){
        return yuYueService.queryshengxinxi(token);
    }
    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/shixinxi",method = RequestMethod.GET)
    public  Map<String,Object> queryshixinxi(String token,String regionprovinceid){
        return yuYueService.queryshixinxi(token,regionprovinceid);
    }
    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/xianxinxi",method = RequestMethod.GET)
    public  Map<String,Object> queryxianxinxi(String token,String regioncityid){
        return yuYueService.queryxianxinxi(token,regioncityid);
    }


    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/addyuyue",method = RequestMethod.POST)
    public Map<String,Object> addyuyue(String token,String yy,String vid){
        return yuYueService.addyuyue(token,yy,vid);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/geren",method = RequestMethod.GET)
    public  Map<String,Object> querygeren(String token){
        return yuYueService.querygeren(token);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/subscribe/xinxiupdate",method = RequestMethod.POST)
    public  Map<String,Object> xinxiupdate(String token,Userinfo info){
        return yuYueService.xinxiupdate(token,info);
    }











}
