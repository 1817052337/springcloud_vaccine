package com.rj.bd.review.service;

import com.rj.bd.review.dao.reviewDao;
import com.rj.bd.review.entity.Review;
import com.rj.bd.review.entity.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author wxy
 * @desc review M层实现类
 * @time 2021-06-01-10:02
 *
 */
@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    public reviewDao reviewDao;

    @Override
    public List<Review> subscribeLogin(String sid) {
        return reviewDao.subscribeLogin(sid);
    }

    @Override
    public List<Review> surveyLogin(String sid, String phone) {
        return reviewDao.surveyLogin(sid, phone);
    }

    @Override
    public List<Review> surveyQueryById(String sid) {
        return reviewDao.surveyQueryById(sid);
    }

    @Override
    public int surveyAdd(int sidr, String text, String symptom, String health, String time) {
        return reviewDao.surveyAdd(sidr, text, symptom, health, time);
    }

    @Override
    public List<Symptom> symptomQueryAll() {
        return reviewDao.symptomQueryAll();
    }


}
