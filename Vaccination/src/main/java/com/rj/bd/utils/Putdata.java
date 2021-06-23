/**
 * 
 */
package com.rj.bd.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 输出工具类
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午1:38:56
 */
public class Putdata {

	/**
	 * @desc  格式化输出  Map格式的数据
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static Map<String, Object> printf(int code, String msg, Object data) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("code", code);
		ret.put("msg", msg);
		ret.put("data", data);
		return ret;
	}

	/**
	 * @desc  格式化输出  Map格式的数据    适用于layui分页
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static Map<String, Object> printfByPage(int code, String msg, Object data,long count) {
		Map<String, Object> ret = new HashMap<String,Object>();
		ret.put("code", code);
		ret.put("msg", msg);
		ret.put("data", data);
		ret.put("count",count);
		return ret;
	}
}
