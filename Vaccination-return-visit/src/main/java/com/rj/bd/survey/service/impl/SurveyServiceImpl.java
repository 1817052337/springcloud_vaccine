package com.rj.bd.survey.service.impl;

import com.github.pagehelper.PageHelper;
import com.rj.bd.survey.entity.SurveyShow;
import com.rj.bd.survey.mapper.SurveyMapper;
import com.rj.bd.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mxj
 * @desc surveyM层实现类
 * @since 2021-06-01
 */
@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    SurveyMapper surveyMapper;

    @Override
    public List<SurveyShow> queryAllPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        //bug PageHelper的bug需要执行2次查询才能请求到数据
        System.out.println(surveyMapper.queryAll());
        return surveyMapper.queryAll();
    }

    @Override
    @Cacheable(value ="SurveyQueryByName", key = "'name'+#name", unless = "#result == null")
    public List<SurveyShow> queryByName(String name, Integer page, Integer limit) {
        String real_name = "%" + name + "%";
        PageHelper.startPage(page, limit);
        //bug PageHelper的bug需要执行2次查询才能请求到数据
        System.out.println(surveyMapper.queryByName(real_name));
        return surveyMapper.queryByName(real_name);

    }

    @Override
    public int del(String id) {
        return surveyMapper.del(id);
    }
}
