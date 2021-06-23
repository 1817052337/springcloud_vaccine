package com.rj.bd.subscribe.service;



import com.rj.bd.subscribe.dao.SubscribeMapper;
import com.rj.bd.subscribe.entity.subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubscribeServiceImpl implements SubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;

    public List<Map> getPage(Map maps,String detail,String uid){

        System.out.println("pageNo:"+maps.get("pageNo")+" pageSize:"+maps.get("pageSize"));
        int pageNo=maps.get("pageNo")==null?1:Integer.valueOf(maps.get("pageNo")+"");
        int pageSize=maps.get("pageSize")==null?2:Integer.valueOf(maps.get("pageSize")+"");
        //String d="%"+detail+"%";
        maps.put("start",(pageNo-1)*pageSize);
        maps.put("end",pageSize);
        maps.put("detail", detail);
        maps.put("uid", uid);
        return subscribeMapper.getPage(maps);

    }



    public List<subscribe> queryAll(String detail){
        return subscribeMapper.queryAll(detail);
    }


    public void deletetById(String sid){
        subscribeMapper.deletetById(sid);
    }

}
