package com.rj.bd.Vaccine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.Vaccine.entity.User;
import com.rj.bd.Vaccine.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author ZC
 * @desc
 * @time 2021--06--02 9:01
 */
@Mapper
public interface YuYueMapper extends BaseMapper<Userinfo> {


    @Select("SELECT  \n" +
            "  distinct  regionprovince.`name` AS shengname,\n" +
            "\t regionprovince.province_code,\n" +
            "\t regionprovince.id\n" +
            "FROM\n" +
            "hospital \n" +
            "\tJOIN regionprovince ON hospital.regionprovinceid = regionprovince.id")
   List<Map<String, Object>> querysheng();
    @Select("\t\n" +
            "SELECT\n" +
            "\tregioncity.`name` AS shiname,\n" +
            "\thospital.hid,\n" +
            "\thospital.`name`,\n" +
            "\tregioncity.id,\n" +
            "\tregionprovince.id AS regionprovinceid,\n" +
            "\tregioncity.province_code \n" +
            "FROM\n" +
            "\thospital\n" +
            "\tJOIN regioncity ON hospital.regioncityid = regioncity.id \n" +
            "\tLEFT JOIN regionprovince on hospital.regionprovinceid=regionprovince.id  where regionprovinceid=#{arg0}")
    List<Map<String,Object>>  queryshi(String sheng);

    @Select("SELECT\n" +
            "\tdistinct regioncity.`name` AS shiname,\n" +
            "\tregioncity.id,\n" +
            "\tregionprovince.id AS regionprovinceid,\n" +
            "\tregioncity.province_code \n" +
            "FROM\n" +
            "\thospital\n" +
            "\tJOIN regioncity ON hospital.regioncityid = regioncity.id\n" +
            "LEFT JOIN regionprovince on hospital.regionprovinceid=regionprovince.id  WHERE\n" +
            "regionprovince.province_code=#{arg0}")
   List<Map<String, Object>> queryshi2(String province_code);

    @Select("SELECT\n" +
            "regioncity.city_code\n" +
            "FROM\n" +
            "\thospital\n" +
            "\tJOIN regioncity ON hospital.regioncityid = regioncity.id where id=#{arg0}")
    List<Map<String,Object>>  queryxian(String shi);

    @Select("\t\tSELECT \n" +
            "            DISTINCT regionarea.`name` AS xianname,\n" +
            "            regionarea.id,\n" +
            "             regioncity.id AS regioncityid,\n" +
            "             regionarea.city_code\n" +
            "\t\t\t\t\t\t FROM\n" +
            "\t\t\t\t\t\t  hospital \n" +
            "\t\t\t\t\t\t\tLEFT\n" +
            "            JOIN regionarea ON hospital.regionareaid = regionarea.id \n" +
            "            LEFT JOIN regioncity ON hospital.regioncityid = regioncity.id where regioncity.city_code=#{arg0}")
    List<Map<String, Object>> queryxian2(String city_code);

    @Select("\tSELECT hospital.hid,hospital.name,regioncity.id FROM hospital\n" +
            "\tLEFT JOIN regioncity ON hospital.regioncityid=regioncity.id where regioncityid =#{arg0} ")
    List<Map<String, Object>> queryyiyuan(String shi);


    @Select("select * from regionprovince")
    List<Map<String, Object>> queryshengxinxi();
    @Select("select province_code from  regionprovince where id=#{arg0}")
    Map<String, Object> queryshixinxi(String regionprovinceid);
    @Select("SELECT * from regioncity where province_code =#{arg0}")
    List<Map<String, Object>> queryshixinxi2(String province_code);
    @Select("select city_code from  regioncity where id=#{arg0}")
    Map<String, Object> queryxianxinxi(String regioncityid);
    @Select("SELECT * from regionarea where city_code =#{arg0}")
    List<Map<String, Object>> queryxianxinxi2(String city_code);
@Select("select * from user")
List<Map<String,Object>>  selectall();
@Select("SELECT * from `user` where  uid=#{arg0} AND state=0")
 Map<String, Object> queryid(String uid);
@Update("update user set state=1 where uid=#{uid}")
 void edit(String uid, int state);
@Select("SELECT * from `user` WHERE `user`.uid=#{uid} ")
    Map<String, Object> querycode(String uid);



@Select("SELECT * FROM hospitalvaccine LEFT JOIN hospital on hospitalvaccine.hid=hospital.hid WHERE hospital.hid=#{yy} AND hospitalvaccine.vid=#{vid}")
List<Map<String,Object>> queryhid(String yy, String vid);
@Select("SELECT\n" +
        "`user`.uid,\n" +
        "`user`.phone,\n" +
        "userinfo.`name`,\n" +
        "userinfo.number,\n" +
        "regionprovince.`name` AS shengname,\n" +
        "regionprovince.id,\n" +
        "regioncity.id AS shiid,\n" +
        "regionarea.id AS xianid,\n" +
        "regioncity.`name` AS shiname,\n" +
        "regionarea.`name` AS xianname\n" +
        "\t\n" +
        "FROM\n" +
        "\t`user`\n" +
        "\tLEFT JOIN userinfo ON `user`.uid = userinfo.uid\n" +
        "\tLEFT JOIN  regionprovince on userinfo.regionprovinceid=regionprovince.id\n" +
        "\tLEFT JOIN  regioncity on userinfo.regioncityid=regioncity.id\n" +
        "\tLEFT JOIN  regionarea on userinfo.regionareaid=regionarea.id\n" +
        "\t where user.uid=#{uid}")
      List<Map<String, Object>> querygeren(String uid);


}
