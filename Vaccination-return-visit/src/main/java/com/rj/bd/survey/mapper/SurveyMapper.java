package com.rj.bd.survey.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import com.rj.bd.survey.entity.SurveyShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author mxj
 * @desc survey持久层
 * @since 2021-06-01
 */
@Mapper
public interface SurveyMapper {

    @Select("SELECT sid,vid,vname,l.name,health,time,text, GROUP_CONCAT(s.name) as symptom\n" +
            "from \n" +
            "(SELECT s.sid,hv.vid,u.name as name,v.name as vname,health,s.time,text, replace(REPLACE(symptom,\"[\",\"\"),\"]\",\"\") as symptom\n" +
            "from survey s,subscribe su,hospitalvaccine hv,vaccinetype v ,userinfo u\n" +
            "where\n" +
            "s.sid = su.sid AND\n" +
            "su.hvid = hv.hvid AND\n" +
            "hv.vid = v.vid AND\n" +
            "su.uid = u.uid) l,\n" +
            "symptom s\n" +
            "WHERE\n" +
            "symptom = \"\"\n" +
            "or\n" +
            "SUBSTRING_INDEX(SUBSTRING_INDEX(symptom,',',syid),',',-1) = s.syid\n" +
            "GROUP BY sid\n")
    List<SurveyShow> queryAll();

    @Select("SELECT sid,vid,vname,l.name,health,time,text, GROUP_CONCAT(s.name) as symptom\n" +
            "from \n" +
            "(SELECT s.sid,hv.vid,u.name as name,v.name as vname,health,s.time,text, replace(REPLACE(symptom,\"[\",\"\"),\"]\",\"\") as symptom\n" +
            "from survey s,subscribe su,hospitalvaccine hv,vaccinetype v ,userinfo u\n" +
            "where\n" +
            "u.name like #{real_name} AND\n" +
            "s.sid = su.sid AND\n" +
            "su.hvid = hv.hvid AND\n" +
            "hv.vid = v.vid AND\n" +
            "su.uid = u.uid) l,\n" +
            "symptom s\n" +
            "WHERE\n" +
            "symptom = \"\"\n" +
            "or\n" +
            "SUBSTRING_INDEX(SUBSTRING_INDEX(symptom,',',syid),',',-1) = s.syid\n" +
            "GROUP BY sid")
    List<SurveyShow> queryByName(@Param("real_name") String real_name);



    @Delete("delete from  `survey` where sid=#{sid}")
    int del(String id);
}
