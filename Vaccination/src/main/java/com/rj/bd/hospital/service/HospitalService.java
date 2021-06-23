package com.rj.bd.hospital.service;

import com.rj.bd.hospital.entity.hospital;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface HospitalService {

    List<Map> getPage(Map maps,String detail);

    List<hospital>  queryAll(String detail);

}
