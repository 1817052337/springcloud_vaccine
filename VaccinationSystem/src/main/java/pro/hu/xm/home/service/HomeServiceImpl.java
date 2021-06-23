package pro.hu.xm.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.home.entity.HotMess;
import pro.hu.xm.home.mapper.HomeMapper;

import java.util.*;

/**
 * @author Littiger
 * @title: HomeServiceImpl
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/218:37
 */
@Service
public class HomeServiceImpl implements IHomeService{

    @Autowired
    HomeMapper homeMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> queryHomeMess() {
        return JsonUtils.toJson("请求成功",0,homeMapper.queryHomeMess());
    }

    @Override
    public Map<String, Object> queryVaccineComplete(Integer vid) {
        Map<String,Object> map = new HashMap<>();
        map.put("title",homeMapper.queryOneName(vid));
        map.put("futitle","实时在线地图监控全国疫苗接种情况");
        map.put("mess",homeMapper.queryVaccineComplete(vid));
        return JsonUtils.toJson("请求成功",0,map);
    }

    @Override
    public Map<String, Object> queryHealth() {
        return JsonUtils.toJson("请求成功",0,homeMapper.queryHealth());
    }

    @Override
    public void updateRedisHotMess() {
        List<HotMess> list = homeMapper.queryHotMess();
        for (HotMess hotMess:list) {
            redisTemplate.opsForZSet().add("hotlist",hotMess.getName()+","+hotMess.getVid(),hotMess.getValue());
        }
    }

    @Override
    public Map<String, Object> queryVaccineRank() {

        List<Map<String,Object>> list = new ArrayList<>();
        Set<ZSetOperations.TypedTuple> set = redisTemplate.opsForZSet().rangeWithScores("hotlist", 1, 10);
        List<ZSetOperations.TypedTuple> list_redis = new ArrayList<>(set);
        for (int i=list_redis.size()-1 ; i>=0 ;i--){
            Map<String,Object> map = new HashMap<>();
            String[] list_name = list_redis.get(i).getValue().toString().split(",");
            map.put("name",list_name[0]);
            map.put("vid",Integer.parseInt(list_name[1]));
            map.put("value",list_redis.get(i).getScore());
            list.add(map);
        }

        return JsonUtils.toJson("请求成功",0,list);
    }
}
