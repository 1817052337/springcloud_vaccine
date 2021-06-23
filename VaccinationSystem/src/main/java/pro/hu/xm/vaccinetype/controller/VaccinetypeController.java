package pro.hu.xm.vaccinetype.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.admin.service.ADMINervice;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.hospital.entity.HospitalShow;
import pro.hu.xm.vaccinetype.entity.Vaccinetype;
import pro.hu.xm.vaccinetype.service.VACCINETYPEervice;

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
@RequestMapping("/admin/vaccine")
@CrossOrigin("*")
public class VaccinetypeController {

    @Autowired
    VACCINETYPEervice vaccinetypEervice;

    @Autowired
    ADMINervice adminService;

    /**
     * @desc  接口返回 疫苗信息 分页查询 需要 page、limit、token 参数
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
        List<Vaccinetype> list = vaccinetypEervice.queryAllPage(page,limit);

        PageInfo<Vaccinetype> reservationPageInfo = new PageInfo<Vaccinetype>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口完成 添加疫苗 需要 name、token 参数
     * @param name
     * @param token
     * @return
     */
    @RequestMapping("/add")
    public Map<String,Object> add(String name,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        return vaccinetypEervice.add(name);

    }

    /**
     * @desc  接口完成 修改疫苗 需要 vid、name、token 参数
     * @param vid
     * @param name
     * @param token
     * @return
     */
    @RequestMapping("/edit")
    public Map<String,Object> edit(Integer vid,String name,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        return vaccinetypEervice.edit(vid,name);

    }

}

