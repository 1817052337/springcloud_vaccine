package com.rj.bd.hospital.controller;


import com.rj.bd.hospital.entity.User;
import com.rj.bd.hospital.entity.hospital;
import com.rj.bd.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 分页&条件查询
     */

    @RequestMapping("/hospital/query")
    public Map<String,Object> query(@RequestParam Map maps, HttpServletRequest request) {
        System.out.println("HospitalController--->query()");
        String detail=request.getParameter("detail");
        if (detail==null){
            detail="";
        }

        List<Map> Page= hospitalService.getPage(maps,detail);
       List<hospital> list=hospitalService.queryAll(detail);
        System.out.println("page=="+Page);
        System.out.println("list=="+list);
        int count=list.size();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("count",count);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("data",Page);
        return map;

    }

}
