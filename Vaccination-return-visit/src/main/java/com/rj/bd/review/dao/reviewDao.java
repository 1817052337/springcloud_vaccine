package com.rj.bd.review.dao;

import com.rj.bd.review.entity.Review;
import com.rj.bd.review.entity.Symptom;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wxy
 * @desc review持久层
 * @time 2021-06-01-10:02
 *
 */
@Mapper
public interface reviewDao {

    @Select("select sid from subscribe where sid = #{sid} and scode = 1")
    List<Review> subscribeLogin(@Param("sid") String sid);

    @Select("select s.sid,u.phone from user u left join subscribe s on u.uid = s.uid\n" +
            " where phone = #{phone} and sid = #{sid}")
    List<Review> surveyLogin(@Param("sid") String sid, @Param("phone") String phone);

    @Select("select u.name as uname,h.name as hname,v.name as vname, s.time from subscribe s left join hospitalvaccine hv on s.hvid = hv.hvid\n" +
            "left join hospital h on h.hid = hv.hid\n" +
            "left join vaccinetype v on v.vid = hv.vid\n" +
            "left join userinfo u on s.uid=u.uid \n" +
            "where s.sid = #{sid}")
    List<Review> surveyQueryById(@Param("sid") String sid);

    @Insert("insert into survey values(#{sidr},#{text},#{symptom},#{health},#{time})")
    int surveyAdd(@Param("sidr") int sidr, @Param("text") String text, @Param("symptom") String symptom, @Param("health") String health, @Param("time") String time);

    @Select("select syid,name from symptom")
    List<Symptom> symptomQueryAll();
}
