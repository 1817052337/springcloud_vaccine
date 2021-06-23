package pro.hu.xm.subscribe.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.admin.service.ADMINervice;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.hospital.entity.HospitalShow;
import pro.hu.xm.subscribe.entity.SubscribeShow;
import pro.hu.xm.subscribe.service.SUBSCRIBEervice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@RestController
@RequestMapping("/admin/subscribe")
@CrossOrigin("*")
public class SubscribeController {

    @Autowired
    SUBSCRIBEervice subscribEervice;

    @Autowired
    ADMINervice adminService;

    /**
     * @desc  接口返回 预约接种信息 分页查询 需要 page、limit、token 参数
     * @param page
     * @param limit
     * @param token
     * @return
     */
    @RequestMapping("/queryallpage")
    public Map<String,Object> queryAllPage(Integer page,Integer limit,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        Map<String,Object> map = new HashMap<String,Object>();
        List<SubscribeShow> list = subscribEervice.queryAllPage(page,limit);

        PageInfo<SubscribeShow> reservationPageInfo = new PageInfo<SubscribeShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口返回 预约接种信息 多条件分页查询 可选 name、hname、vid、regionprovinceid、regioncityid、regionareaid 参数
     *        需要 page、limit、token 参数
     * @param name
     * @param hname
     * @param vid
     * @param regionprovinceid
     * @param regioncityid
     * @param regionareaid
     * @param page
     * @param limit
     * @param token
     * @return
     */
    @RequestMapping("/querybyname")
    public Map<String,Object> queryByName(String name,String hname,String vid,String regionprovinceid,String regioncityid,String regionareaid,Integer page,Integer limit,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        Map<String,Object> map = new HashMap<String,Object>();
        List<SubscribeShow> list = subscribEervice.queryByName(name,hname,vid,regionprovinceid,regioncityid,regionareaid,page,limit);

        PageInfo<SubscribeShow> reservationPageInfo = new PageInfo<SubscribeShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口完成 更新用户接种状态 需要 sid、token 参数
     * @param sid
     * @param token
     * @return
     */
    @RequestMapping("/updatescode")
    public Map<String,Object> updateScode(Integer sid,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        return subscribEervice.updateScode(sid);

    }
}

