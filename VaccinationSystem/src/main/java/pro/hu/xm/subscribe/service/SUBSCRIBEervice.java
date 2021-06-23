package pro.hu.xm.subscribe.service;

import pro.hu.xm.subscribe.entity.Subscribe;
import com.baomidou.mybatisplus.extension.service.IService;
import pro.hu.xm.subscribe.entity.SubscribeShow;

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
public interface SUBSCRIBEervice {

    public List<SubscribeShow> queryAllPage(Integer page,Integer limit);

    public List<SubscribeShow> queryByName(String name,String hname,String vid,String regionprovinceid,String regioncityid,String regionareaid,Integer page,Integer limit);

    public Map<String,Object>  updateScode(Integer sid);

}
