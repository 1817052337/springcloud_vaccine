package pro.hu.xm.baseselect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.hu.xm.baseselect.mapper.BaseMapper;
import pro.hu.xm.framework.JsonUtils;

import java.util.Map;

/**
 * @author Littiger
 * @title: BaseServiceImpl
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/210:10
 */
@Service("baseService")
public class BaseServiceImpl implements IBaseService{

    @Autowired
    BaseMapper baseMapper;

    @Override
    public Map<String, Object> queryAllProvince() {
        return JsonUtils.toJson("请求成功",0,baseMapper.queryAllProvince());
    }

    @Override
    public Map<String, Object> queryAllCity(String id) {
        return JsonUtils.toJson("请求成功",0,baseMapper.queryAllCity(id));
    }

    @Override
    public Map<String, Object> queryAllArea(String id) {
        System.out.println(baseMapper.queryAllArea(id) == null);
        return JsonUtils.toJson("请求成功",0,baseMapper.queryAllArea(id));
    }

    @Override
    public Map<String, Object> queryAllVaccine() {
        return JsonUtils.toJson("请求成功",0,baseMapper.queryAllVaccine());
    }
}
