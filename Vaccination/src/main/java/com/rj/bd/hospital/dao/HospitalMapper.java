package com.rj.bd.hospital.dao;


import com.rj.bd.hospital.entity.hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HospitalMapper {

    @Select("SELECT\n" +
            "\thospital.hid AS hid,\n" +
            "\thospital.`name` AS name,\n" +
            "\tregionprovince.`name` AS regionprovinceid,\n" +
            "\tregioncity.`name` AS regioncityid,\n" +
            "\tregionarea.`name` AS regionareaid\n" +
            "FROM\n" +
            "\thospital\n" +
            "\tLEFT JOIN regionprovince ON hospital.regionprovinceid = regionprovince.id\n" +
            "\tLEFT JOIN regioncity ON hospital.regioncityid = regioncity.id\n" +
            "\tLEFT JOIN regionarea ON hospital.regionareaid = regionarea.id where hospital.`name` LIKE CONCAT('%',#{detail},'%') LIMIT #{start},#{end}")
    List<Map> getPage(Map maps);

    @Select("SELECT\n" +
            "\thospital.hid AS hid,\n" +
            "\thospital.`name` AS `name`,\n" +
            "\tregionprovince.`name` AS regionprovinceid,\n" +
            "\tregioncity.`name` AS regioncityid,\n" +
            "\tregionarea.`name` AS regionareaid\n" +
            "FROM\n" +
            "\thospital\n" +
            "\tLEFT JOIN regionprovince ON hospital.regionprovinceid = regionprovince.id\n" +
            "\tLEFT JOIN regioncity ON hospital.regioncityid = regioncity.id\n" +
            "\tLEFT JOIN regionarea ON hospital.regionareaid = regionarea.id")
    List<hospital>  queryAll(String detail);

}
