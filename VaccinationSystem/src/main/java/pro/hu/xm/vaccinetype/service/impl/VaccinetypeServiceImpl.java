package pro.hu.xm.vaccinetype.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.vaccinetype.entity.Vaccinetype;
import pro.hu.xm.vaccinetype.mapper.VaccinetypeMapper;
import pro.hu.xm.vaccinetype.service.VACCINETYPEervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@Service("vaccinetypeService")
public class VaccinetypeServiceImpl implements VACCINETYPEervice {

    @Autowired
    VaccinetypeMapper vaccinetypeMapper;

    @Override
    public List<Vaccinetype> queryAllPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        return vaccinetypeMapper.queryAll();
    }

    @Override
    public Map<String, Object> add(String name) {
        Vaccinetype vaccinetype = new Vaccinetype();
        vaccinetype.setName(name);
        return JsonUtils.toJson("请求成功",0,vaccinetypeMapper.insert(vaccinetype));
    }

    @Override
    public Map<String, Object> edit(Integer vid, String name) {
        Vaccinetype vaccinetype = new Vaccinetype();
        vaccinetype.setName(name);
        vaccinetype.setVid(vid);
        return JsonUtils.toJson("请求成功",0,vaccinetypeMapper.updateById(vaccinetype));
    }
}
