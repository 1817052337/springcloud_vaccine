package pro.hu.xm.userinfo.service;

import pro.hu.xm.userinfo.entity.Userinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import pro.hu.xm.userinfo.entity.UserinfoShow;

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
public interface USERINFOervice {

    public List<UserinfoShow> queryAllPage(Integer page, Integer limit);

    public List<UserinfoShow> queryByName(String name,String regionprovinceid,String regioncityid,String regionareaid,Integer page,Integer limit);

    public Map<String,Object> delete(Integer uid);
}
