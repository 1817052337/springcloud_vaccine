package pro.hu.xm.userinfo.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.admin.service.ADMINervice;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.hospital.entity.HospitalShow;
import pro.hu.xm.userinfo.entity.Userinfo;
import pro.hu.xm.userinfo.entity.UserinfoShow;
import pro.hu.xm.userinfo.service.USERINFOervice;

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
@RequestMapping("/admin/user")
@CrossOrigin("*")
public class UserinfoController {

    @Autowired
    USERINFOervice userinfOervice;

    @Autowired
    ADMINervice adminService;

    /**
     * @desc  接口返回 用户信息 分页查询 需要 page、limit、token 参数
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
        List<UserinfoShow> list = userinfOervice.queryAllPage(page,limit);

        PageInfo<UserinfoShow> reservationPageInfo = new PageInfo<UserinfoShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口返回 用户信息 多条件分页查询 可选 name、regionprovinceid、regioncityid、regionareaid 参数
     *        需要 page、limit、token 参数
     * @param name
     * @param regionprovinceid
     * @param regioncityid
     * @param regionareaid
     * @param page
     * @param limit
     * @param token
     * @return
     */
    @RequestMapping("/querybyname")
    public Map<String,Object> queryByName(String name, String regionprovinceid, String regioncityid, String regionareaid,Integer page,Integer limit,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        Map<String,Object> map = new HashMap<String,Object>();
        List<UserinfoShow> list = userinfOervice.queryByName(name,regionprovinceid,regioncityid,regionareaid,page,limit);
        System.out.println(list.toString());
        PageInfo<UserinfoShow> reservationPageInfo = new PageInfo<UserinfoShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口完成 删除用户信息 需要 uid、token 参数
     * @param uid
     * @param token
     * @return
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer uid,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        return userinfOervice.delete(uid);

    }



}

