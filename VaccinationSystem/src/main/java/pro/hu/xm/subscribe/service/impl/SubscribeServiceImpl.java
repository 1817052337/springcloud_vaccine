package pro.hu.xm.subscribe.service.impl;

import com.github.pagehelper.PageHelper;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Autowired;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.framework.SendCodeUtils;
import pro.hu.xm.subscribe.entity.SubscribeShow;
import pro.hu.xm.subscribe.mapper.SubscribeMapper;
import pro.hu.xm.subscribe.service.SUBSCRIBEervice;
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
public class SubscribeServiceImpl implements SUBSCRIBEervice {

    @Autowired
    SubscribeMapper subscribeMapper;

    @Override
    public List<SubscribeShow> queryAllPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        return subscribeMapper.queryAll();
    }

    @Override
    public List<SubscribeShow> queryByName(String name, String hname, String vid, String regionprovinceid, String regioncityid, String regionareaid, Integer page, Integer limit) {

        String real_name = "%"+name+"%";
        String real_hname = "%"+hname+"%";

        if ("".equals(vid) || vid == null){
            vid = "%";
        }
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
        return subscribeMapper.queryByName(real_name,real_hname,vid,regionprovinceid,regioncityid,regionareaid);

    }

    @Override
    public Map<String, Object> updateScode(Integer sid) {

        int updatecount = subscribeMapper.updateScode(sid);
        if (updatecount == 1){
            Map<String,Object> map = subscribeMapper.queryOnePhone(sid);
            if ((int)map.get("scode") == 1){
                String phone = map.get("phone").toString();
                String code = map.get("sid").toString();
                try {
                    SendCodeUtils.sendSurveyId(phone,code);
                } catch (TencentCloudSDKException e) {
                    return JsonUtils.toJson("发送短信异常",-4);
                }
                return JsonUtils.toJson("请求成功",0);
            }
            else if((int)map.get("scode") == 0){
                return JsonUtils.toJson("请求成功",0);
            }

        }

        return JsonUtils.toJson("请求失败",-1);
    }

}
