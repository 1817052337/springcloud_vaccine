package com.rj.bd.survey.controller;


import com.github.pagehelper.PageInfo;
import com.rj.bd.survey.entity.SurveyShow;
import com.rj.bd.survey.service.SurveyService;
import com.rj.bd.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mxj
 * @desc survey控制层
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/admin/survey")
@CrossOrigin("*")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    /**
     * @desc 查询全部
     * @param page
     * @param limit
     * @return 分页好的数据
     */
    @RequestMapping("/queryAllPage")
    public Map<String, Object> queryAllPage(Integer page, Integer limit) {
        //查询全部
        List<SurveyShow> list = surveyService.queryAllPage(page, limit);
        //非空判断
        if (list.isEmpty()){
            JsonUtils.toJson("请求失败",1);
        }
        //PageHelper处理数据
        PageInfo<SurveyShow> reservationPageInfo = new PageInfo<>(list);
        return JsonUtils.toJson("请求成功",0,reservationPageInfo.getTotal(),reservationPageInfo.getList());
    }

    /**
     * @desc  模糊查询（根据name）
     * @param name
     * @param page
     * @param limit
     * @return 分页好的查询数据
     */
    @RequestMapping("/queryByName")
    public Map<String, Object> queryByName(String name, Integer page, Integer limit) {
        //模糊查询
        List<SurveyShow> list = surveyService.queryByName(name, page, limit);
        //非空判断
        if (list.isEmpty()){
            JsonUtils.toJson("请求失败",1);
        }
        //PageHelper处理数据
        PageInfo<SurveyShow> surveyShowPageInfo = new PageInfo<>(list);
        return JsonUtils.toJson("请求成功",0,surveyShowPageInfo.getTotal(),surveyShowPageInfo.getList());
    }

    /**
     * @desc 根据id删除记录
     * @param id
     */
    @RequestMapping("/del")
    public Map<String,Object> deletetById(String id){
            Map<String,Object> json = new HashMap<>();
            if (id==null||id.equals("")){
                json.put("code",1);
                json.put("mag","非法请求");
                return json;
            }
            int flag= surveyService.del(id);

            if (flag==0){
                json.put("code",1);
                json.put("msg","删除失败！");
                return json;
            }
            json.put("code",0);
            json.put("msg","删除成功！");

        return json;
    }




}

