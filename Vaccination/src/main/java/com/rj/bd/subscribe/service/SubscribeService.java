package com.rj.bd.subscribe.service;



import com.rj.bd.subscribe.entity.subscribe;

import java.util.List;
import java.util.Map;

public interface SubscribeService {

    List<Map> getPage(Map maps,String detail,String uid);

    List<subscribe>  queryAll(String uid);

    void deletetById(String sid);

}
