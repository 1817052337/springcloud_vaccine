package com.rj.bd.review.service;

import com.rj.bd.review.entity.Review;
import com.rj.bd.review.entity.Symptom;

import java.util.List;

/**
 * @author wxy
 * @desc review M层接口
 * @time 2021-06-01-10:02
 *
 */
public interface IReviewService {
    List<Review> subscribeLogin(String sid);

    List<Review> surveyLogin(String sid, String phone);

    List<Review> surveyQueryById(String sid);

    int surveyAdd(int sidr, String text, String symptom, String health, String time);

    List<Symptom> symptomQueryAll();
}
