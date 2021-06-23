package com.rj.bd.admin.service;

import com.rj.bd.admin.dao.adminDao;
import com.rj.bd.admin.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxy
 * @desc admin M层实现类
 * @time 2021-06-01-10:02
 */
@Service
public class AdminServiceImpl implements IAdminervice {

    @Autowired
    public adminDao adminDao;

    @Override
    public List<Admin> adminLogin(String adminname, String adminpwd) {
        return adminDao.adminLogin(adminname, adminpwd);
    }

}
