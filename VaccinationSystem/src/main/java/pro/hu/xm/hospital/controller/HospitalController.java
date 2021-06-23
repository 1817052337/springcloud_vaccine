package pro.hu.xm.hospital.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.admin.service.ADMINervice;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.hospital.entity.HospitalShow;
import pro.hu.xm.hospital.service.HOSPITALervice;

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
@RequestMapping("/admin/hospital")
@CrossOrigin("*")
public class HospitalController {

    @Autowired
    HOSPITALervice hospitaLervice;

    @Autowired
    ADMINervice adminService;

    /**
     * @desc  接口返回 医院信息 分页查询 需要page、limit、token参数
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
        List<HospitalShow> list = hospitaLervice.queryAllPage(page,limit);

        PageInfo<HospitalShow> reservationPageInfo = new PageInfo<HospitalShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口返回 医院信息 分页多条件查询 可选name、regionprovinceid、regioncityid、regionareaid 参数
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
        List<HospitalShow> list = hospitaLervice.queryByName(name,regionprovinceid,regioncityid,regionareaid,page,limit);
        System.out.println(list.toString());
        PageInfo<HospitalShow> reservationPageInfo = new PageInfo<HospitalShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口完成 添加医院 需要 name、regionprovinceid、regioncityid、regioncityid、token 参数
     * @param name
     * @param regionprovinceid
     * @param regioncityid
     * @param regioncityid
     * @param token
     * @return
     */
    @RequestMapping("/add")
    public Map<String,Object> add(String name,String regionprovinceid,String regioncityid,String regionareaid,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        if (("".equals(name) || name == null) || ("".equals(regionprovinceid) || regionprovinceid == null) ||("".equals(regioncityid) || regioncityid == null) ||("".equals(regionareaid) || regionareaid == null)){
            return JsonUtils.toJson("请求失败",-1,"");
        }

        return hospitaLervice.add(name,regionprovinceid,regioncityid,regionareaid);

    }

    /**
     * @desc  接口完成 修改医院 需要 hid、name、regionprovinceid、regioncityid、regionareaid、token 参数
     * @param hid
     * @param name
     * @param regionprovinceid
     * @param regioncityid
     * @param regionareaid
     * @param token
     * @return
     */
    @RequestMapping("/edit")
    public Map<String,Object> edit(Integer hid ,String name,String regionprovinceid,String regioncityid,String regionareaid,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        if (("".equals(name) || name == null) || ("".equals(regionprovinceid) || regionprovinceid == null) ||("".equals(regioncityid) || regioncityid == null) ||("".equals(regionareaid) || regionareaid == null)){
            return JsonUtils.toJson("请求失败",-1,"");
        }

        return hospitaLervice.edit(hid,name,regionprovinceid,regioncityid,regionareaid);

    }

    /**
     * @desc  接口完成 删除医院 需要 hid、token 参数
     * @param hid
     * @param token
     * @return
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer hid,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        return hospitaLervice.delete(hid);

    }
}

