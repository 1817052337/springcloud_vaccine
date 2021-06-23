package com.rj.bd.utils;

import com.rj.bd.Vaccine.entity.Vaccinetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zc
 * @desc
 * @time 2021-04-30-8:52
 */
public class JsonUtil {

    /**
     * 发送json
     * @param code
     * @param msg
     * @param map
     * @param list
     * @return
     */
    public static Map<String,Object> sendJson(int code, String msg, Map<String,Object> map, List<?> list){
        Map<String,Object> json = new HashMap();
        json.put("code",code);
        json.put("msg",msg);
        if(map != null){
            json.put("data",map);
        }
        if(list != null){
            json.put("data",list);
        }
        return json;
    }

    /**
     * 发送json(带有其他参数)
     * @param code
     * @param msg
     * @param map
     * @param list
     * @param name
     * @param o
     * @return
     */
    public static Map<String,Object> sendJson(int code, String msg, Map<String,Object> map, List<?> list,String name,Object o){
        Map<String,Object> json = new HashMap();
        json.put("code",code);
        json.put("msg",msg);
        json.put(name,o);
        if(map != null){
            json.put("data",map);
        }
        if(list != null){
            json.put("data",list);
        }
        return json;
    }



}
