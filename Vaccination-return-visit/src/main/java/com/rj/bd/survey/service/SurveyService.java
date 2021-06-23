package com.rj.bd.survey.service;

import com.rj.bd.survey.entity.SurveyShow;

import java.util.List;

/**
 * @author mxj
 * @desc surveyM层接口
 * @since 2021-06-01
 */
public interface SurveyService {

    List<SurveyShow> queryAllPage(Integer page, Integer limit);

    List<SurveyShow> queryByName(String name, Integer page, Integer limit);

    int del(String id);
}
