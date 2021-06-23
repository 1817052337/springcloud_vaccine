package com.rj.bd.review.controller;

import com.rj.bd.review.entity.Review;
import com.rj.bd.review.entity.Symptom;
import com.rj.bd.review.service.IReviewService;
import com.rj.bd.utils.DateUtils;
import com.rj.bd.utils.JedisPoolUtils;
import com.rj.bd.utils.JsonUtils;
import com.rj.bd.utils.SendCodeUtils;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @author wxy
 * @desc review控制层
 * @time 2021-06-01-9:57
 */
@RestController
@RequestMapping("/review")
public class reviewController {
    @Autowired
    private IReviewService reviewService;

    Jedis jedis = JedisPoolUtils.getJedisSession();

    /**
     * @param sid
     * @return 登录成功后的json
     * @desc 根据页面传递来的sid, 进行订单验证
     */
    @RequestMapping("/subscribe/login")
    @CrossOrigin
    public Map<String, Object> subscribeLogin(String sid) {
        //查询数据库中sid
        List<Review> list = reviewService.subscribeLogin(sid);
        //非空判断
        if (list.isEmpty()) {
            return JsonUtils.toJson("验证失败", 1);
        }
        //返回json
        return JsonUtils.toJson("验证成功", 0);
    }

    /**
     * @param sid
     * @param phone
     * @return 登录成功的json
     * @desc 通过手机号+验证码进行登录
     */
    @RequestMapping("/survey/login")
    @CrossOrigin
    public Map<String, Object> surveyLogin(String sid, String phone, String code) {
        //手机号+验证码进行登录
        List<Review> list = reviewService.surveyLogin(sid, phone);
        //非空判断
        if (list.isEmpty()) {
            return JsonUtils.toJson("登录失败,请检查您手机号与验证码。", 1);
        }
        //判断redis中的code
        if (code.equals(jedis.get("surveyCode"))) {
            return JsonUtils.toJson("登录成功", 0);
        }
        //返回json
        return JsonUtils.toJson("登录失败,请检查您手机号与验证码。", 1);
    }

    /**
     * @param phone
     * @return 验证码： "code":xxxx
     * @throws TencentCloudSDKException
     * @desc 生成验证码
     */
    @RequestMapping("/survey/code")
    @CrossOrigin
    public Map<String, Object> surveyCode(String phone) throws TencentCloudSDKException {
        //非空判断
        if (("".equals(phone) || phone == null)) {
            return JsonUtils.toJson("请求失败，请检查您手机号", 1);
        }
        //工具类生成的code
        String code = SendCodeUtils.createdCode();
        //根据手机号生成code
        SendCodeUtils.send(phone, code);
        //存redis中的code
        jedis.set("surveyCode", code);
        //设置过期时间，300秒
        jedis.expire("surveyCode", 300);
        jedis.close();

        return JsonUtils.toJson("请求成功", 0);
    }

    /**
     * @param sid
     * @return 页面中需要的各种name :客户姓名、医院名称、疫苗名称、以及时间
     * @desc 进入添加回访信息页面
     */
    @RequestMapping("/survey/toAdd")
    @CrossOrigin
    public Map<String, Object> surveyToAdd(String sid) {
        //根据sid查询数据中各种信息
        List<Review> list = reviewService.surveyQueryById(sid);
        //非空判断
        if (list.isEmpty()) {
            return JsonUtils.toJson("请求失败", 1, null);
        }
        //返回带有需要信息的json
        return JsonUtils.toJson("请求成功", 0, list);
    }

    /**
     * @return 页面中需要的症状信息 :发热、头疼、红肿
     * @desc 进入添加回访症状
     */
    @RequestMapping("/symptom/toAdd")
    @CrossOrigin
    public Map<String, Object> symptomToAdd() {
        //根据sid查询数据中各种信息
        List<Symptom> list = reviewService.symptomQueryAll();
        //非空判断
        if (list.isEmpty()) {
            return JsonUtils.toJson("请求失败", 1, null);
        }
        //返回带有需要信息的json
        return JsonUtils.toJson("请求成功", 0, list);
    }


    /**
     * @param sid
     * @param text
     * @param symptom
     * @param health
     * @return 带有请求成功与否的json
     * @desc 根据页面传递来的值，添加回访
     */
    @RequestMapping("/survey/add")
    @CrossOrigin
    public Map<String, Object> surveyAdd(String sid, String text, String symptom, String health) {
        //非空判断
        if (("".equals(sid) || sid == null)
                || ("".equals(text) || text == null)
                || ("".equals(symptom) || symptom == null)
        ) {
            return JsonUtils.toJson("请求失败", 1, null);
        }
        //设置默认值
        if (("".equals(health) || health == null)) {
            health += "0";
        }
        //转型
        int sidr = Integer.parseInt(sid);
        //获取当前时间进行存储
        String time = DateUtils.getCurrentTimeYMD();
        //获取数据库执行后返回的条数
        int count = reviewService.surveyAdd(sidr, text, symptom, health, time);
        //判断条数是否等于1
        if (count != 1) {
            return JsonUtils.toJson("请求失败", 1);
        }
        return JsonUtils.toJson("请求成功", 0);
    }


}
