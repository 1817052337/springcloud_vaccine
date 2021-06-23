package pro.hu.xm.hospital.service;

import pro.hu.xm.hospital.entity.Hospital;
import com.baomidou.mybatisplus.extension.service.IService;
import pro.hu.xm.hospital.entity.HospitalShow;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
public interface HOSPITALervice{

    public List<HospitalShow> queryAllPage(Integer page, Integer limit);

    public List<HospitalShow> queryByName(String name,String regionprovinceid,String regioncityid,String regionareaid,Integer page,Integer limit);

    public Map<String,Object> add(String name,String regionprovinceid,String regioncityid,String regionareaid);

    public Map<String,Object> edit(Integer hid,String name,String regionprovinceid,String regioncityid,String regionareaid);

    public Map<String,Object> delete(Integer hid);
}
