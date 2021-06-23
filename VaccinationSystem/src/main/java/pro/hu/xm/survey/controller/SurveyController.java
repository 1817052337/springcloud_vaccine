package pro.hu.xm.survey.controller;


import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.admin.service.ADMINervice;
import pro.hu.xm.framework.JsonUtils;
import pro.hu.xm.subscribe.entity.SubscribeShow;
import pro.hu.xm.survey.entity.SurveyShow;
import pro.hu.xm.survey.service.SURVEYervice;

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
@RequestMapping("/admin/survey")
@CrossOrigin("*")
public class SurveyController {

    @Autowired
    SURVEYervice surveYervice;

    @Autowired
    ADMINervice adminService;

    /**
     * @desc  接口返回 回访信息 分页查询 需要 page、limit、token 参数
     * @param token
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryallpage")
    public Map<String,Object> queryAllPage(String token,Integer page,Integer limit){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        Map<String,Object> map = new HashMap<String,Object>();
        List<SurveyShow> list = surveYervice.queryAllPage(page,limit);

        PageInfo<SurveyShow> reservationPageInfo = new PageInfo<SurveyShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

    /**
     * @desc  接口返回 回访信息 多条件分页查询 可选 name、vid 参数 需要 page、limit、token 参数
     * @param name
     * @param vid
     * @param page
     * @param limit
     * @param token
     * @return
     */
    @RequestMapping("/querybyname")
    public Map<String,Object> queryByName(String name,String vid,Integer page,Integer limit,String token){

        if (adminService.verification(token) == false){
            return JsonUtils.toJson("未登录",-2,"");
        }

        Map<String,Object> map = new HashMap<String,Object>();
        List<SurveyShow> list = surveYervice.queryByName(name,vid,page,limit);

        PageInfo<SurveyShow> reservationPageInfo = new PageInfo<SurveyShow>(list);
        map.put("code",0);
        map.put("msg","请求成功");
        map.put("count",reservationPageInfo.getTotal());
        map.put("data",reservationPageInfo.getList());

        return map;

    }

}

