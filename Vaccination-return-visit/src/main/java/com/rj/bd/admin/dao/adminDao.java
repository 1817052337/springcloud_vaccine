package com.rj.bd.admin.dao;


import com.rj.bd.admin.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wxy
 * @desc admin持久层
 * @time 2021-06-01-10:02
 */
@Mapper
public interface adminDao {

    @Select("select adminname,adminpwd from admin where adminname = #{adminname} and adminpwd = #{adminpwd}")
    List<Admin> adminLogin(@Param("adminname") String adminname, @Param("adminpwd") String adminpwd);
}
