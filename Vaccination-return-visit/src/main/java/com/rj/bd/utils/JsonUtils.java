package com.rj.bd.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxy
 * @desc JsonUtils json处理类
 */
public class JsonUtils {

    public static Map<String, Object> toJson(String msg, int code, Object count,Object data) {

        Map<String, Object> map = new HashMap<String, Object>();

        if (data == null) {
            map.put("code", 1);
            map.put("msg", "请求失败");
            return map;
        }

        map.put("msg", msg);
        map.put("code", code);
        map.put("count", count);
        map.put("data", data);

        return map;

    }

    public static Map<String, Object> toJson(String msg, int code, Object data) {

        Map<String, Object> map = new HashMap<String, Object>();

        if (data == null) {
            map.put("code", 1);
            map.put("msg", "请求失败");
            return map;
        }

        map.put("msg", msg);
        map.put("code", code);
        map.put("data", data);

        return map;

    }

    public static Map<String, Object> toJson(String msg, int code) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("msg", msg);
        map.put("code", code);

        return map;

    }

}
