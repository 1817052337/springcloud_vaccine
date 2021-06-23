package com.rj.bd.hospital.service;


import com.rj.bd.hospital.dao.HospitalMapper;
import com.rj.bd.hospital.entity.hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService{

    @Autowired
    private HospitalMapper hospitalMapper;

    public List<Map> getPage(Map maps,String detail){

        System.out.println("pageNo:"+maps.get("pageNo")+" pageSize:"+maps.get("pageSize"));
        int pageNo=maps.get("pageNo")==null?1:Integer.valueOf(maps.get("pageNo")+"");
        int pageSize=maps.get("pageSize")==null?2:Integer.valueOf(maps.get("pageSize")+"");
        //String d="%"+detail+"%";
        maps.put("start",(pageNo-1)*pageSize);
        maps.put("end",pageSize);
        maps.put("detail", detail);
        return hospitalMapper.getPage(maps);

    }



    public List<hospital> queryAll(String detail){
        return hospitalMapper.queryAll(detail);
    }

}
