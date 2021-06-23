package com.rj.bd.admin.service;

import com.rj.bd.admin.entity.Admin;

import java.util.List;

/**
 * @author wxy
 * @desc admin M层接口
 * @time 2021-06-01-10:02
 */
public interface IAdminervice {

    List<Admin> adminLogin(String adminname, String adminpwd);
}
