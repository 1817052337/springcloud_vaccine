package pro.hu.xm.hospital.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.hospital.entity.Hospital;
import pro.hu.xm.hospital.entity.HospitalShow;
import pro.hu.xm.hospital.mapper.HospitalMapper;
import pro.hu.xm.hospital.service.HOSPITALervice;
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
@Service
public class HospitalServiceImpl implements HOSPITALervice {

    @Autowired
    HospitalMapper hospitalMapper;

    @Override
    public List<HospitalShow> queryAllPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        return hospitalMapper.queryAll();
    }

    @Override
    public List<HospitalShow> queryByName(String name, String regionprovinceid, String regioncityid, String regionareaid,Integer page,Integer limit) {

        String real_name = "%"+name.trim()+"%";

        if ("".equals(regionprovinceid) || regionprovinceid == null){
            regionprovinceid = "%";
        }
        if ("".equals(regioncityid) || regioncityid == null){
            regioncityid = "%";
        }
        if ("".equals(regionareaid) || regionareaid == null){
            regionareaid = "%";
        }

        PageHelper.startPage(page,limit);
        return hospitalMapper.queryByName(real_name,regionprovinceid,regioncityid,regionareaid);
    }

    @Override
    public Map<String, Object> add(String name, String regionprovinceid, String regioncityid, String regionareaid) {
        Hospital hospital = new Hospital();
        hospital.setName(name);
        hospital.setRegionprovinceid(regionprovinceid);
        hospital.setRegioncityid(regioncityid);
        hospital.setRegionareaid(regionareaid);
        return JsonUtils.toJson("请求成功",0,hospitalMapper.insert(hospital));
    }

    @Override
    public Map<String, Object> edit(Integer hid, String name, String regionprovinceid, String regioncityid, String regionareaid) {
        Hospital hospital = new Hospital();
        hospital.setName(name);
        hospital.setRegionprovinceid(regionprovinceid);
        hospital.setRegioncityid(regioncityid);
        hospital.setRegionareaid(regionareaid);
        hospital.setHid(hid);
        int count = 0;
        try {
             count = hospitalMapper.updateById(hospital);
        } catch (Exception e) {
            return JsonUtils.toJson("修改失败",-5);
        }
        return JsonUtils.toJson("请求成功",0,count);
    }

    @Override
    public Map<String, Object> delete(Integer hid) {
        hospitalMapper.delete(hid);
        return JsonUtils.toJson("请求成功",0, hospitalMapper.deleteById(hid));
    }
}
