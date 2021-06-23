package com.rj.bd.subscribe.dao;



import com.rj.bd.subscribe.entity.subscribe;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubscribeMapper {

    @Select("SELECT\n" +
            "\tsubscribe.sid,\n" +
            "\t`userinfo`.uid,\n" +
            "\tuserinfo.`name`,\n" +
            "\tuserinfo.`number`,\n" +
            "\thospital.`name` AS hospitalid,\n" +
            "\tregionprovince.`name` AS regionprovinceid,\n" +
            "\tregioncity.`name` AS regioncityid,\n" +
            "\tregionarea.`name` AS regionareaid,\n" +
            "\tvaccinetype.`name` AS vaccinetypeid,\n" +
            "\tsubscribe.time \n" +
            "FROM\n" +
            "\tsubscribe\n" +
            "\tLEFT JOIN userinfo ON subscribe.uid = userinfo.uid\n" +
            "\tLEFT JOIN hospitalvaccine ON subscribe.hvid = hospitalvaccine.hvid\n" +
            "\tLEFT JOIN hospital ON hospitalvaccine.hid = hospital.hid\n" +
            "\tLEFT JOIN vaccinetype ON hospitalvaccine.vid = vaccinetype.vid\n" +
            "\tLEFT JOIN regionprovince ON hospital.regionprovinceid = regionprovince.id\n" +
            "\tLEFT JOIN regioncity ON hospital.regioncityid = regioncity.id\n" +
            "\tLEFT JOIN regionarea ON hospital.regionareaid = regionarea.id  where userinfo.`name` LIKE CONCAT('%',#{detail},'%')  AND userinfo.uid=#{uid} LIMIT #{start},#{end}  ")
    List<Map> getPage(Map maps);

    @Select("SELECT\n" +
            "        subscribe.sid,\n" +
            "\t\t\t\t  `userinfo`.uid,\n" +
            "            userinfo.`name`,\n" +
            "            userinfo.`number`,\n" +
            "            hospital.`name` AS hospitalid,\n" +
            "            regionprovince.`name` AS regionprovinceid,\n" +
            "            regioncity.`name` AS regioncityid,\n" +
            "            regionarea.`name` AS regionareaid,\n" +
            "            vaccinetype.`name` AS vaccinetypeid,\n" +
            "            subscribe.time\n" +
            "            FROM\n" +
            "            subscribe\n" +
            "            LEFT JOIN userinfo ON subscribe.uid = userinfo.uid\n" +
            "            LEFT JOIN hospitalvaccine ON subscribe.hvid=hospitalvaccine.hvid\n" +
            "            LEFT JOIN hospital ON hospitalvaccine.hid=hospital.hid\n" +
            "            LEFT JOIN vaccinetype ON hospitalvaccine.vid=vaccinetype.vid\n" +
            "            LEFT JOIN regionprovince ON hospital.regionprovinceid = regionprovince.id\n" +
            "            LEFT JOIN regioncity ON hospital.regioncityid = regioncity.id\n" +
            "            LEFT JOIN regionarea ON hospital.regionareaid = regionarea.id where userinfo.uid=#{uid}")
    List<subscribe>  queryAll(String uid);


    @Delete("DELETE FROM subscribe WHERE sid = #{did}")
    void deletetById(String sid);


}
