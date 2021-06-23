package pro.hu.xm.userinfo.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.hospital.entity.Hospital;
import pro.hu.xm.userinfo.entity.Userinfo;
import pro.hu.xm.userinfo.entity.UserinfoShow;
import pro.hu.xm.userinfo.mapper.UserinfoMapper;
import pro.hu.xm.userinfo.service.USERINFOervice;
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
public class UserinfoServiceImpl implements USERINFOervice {

    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public List<UserinfoShow> queryAllPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        return userinfoMapper.queryAll();
    }

    @Override
    public List<UserinfoShow> queryByName(String name, String regionprovinceid, String regioncityid, String regionareaid,Integer page,Integer limit) {

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
        return userinfoMapper.queryByName(real_name,regionprovinceid,regioncityid,regionareaid);
    }

    @Override
    public Map<String, Object> delete(Integer uid) {

        return JsonUtils.toJson("请求成功",0,userinfoMapper.delete(uid));

    }
}
