package pro.hu.xm.baseselect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.baseselect.service.IBaseService;

import java.util.Map;

/**
 * @author Littiger
 * @title: BaseController
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/210:12
 */
@RestController
@RequestMapping
@CrossOrigin("*")
public class BaseController {

    @Autowired
    IBaseService baseService;

    /**
     * @desc  查询全部省份 无参
     * @return
     */
    @RequestMapping("/queryallprovince")
    public Map<String,Object> queryAllProvince(){

        return baseService.queryAllProvince();

    }

    /**
     * @desc  查询全部市区 需要省份id
     * @param id
     * @return
     */
    @RequestMapping("/queryallcity")
    public Map<String,Object> queryAllCity(String id){

        return baseService.queryAllCity(id);

    }

    /**
     * @desc  查询全部县区 需要市区id
     * @param id
     * @return
     */
    @RequestMapping("/queryallarea")
    public Map<String,Object> queryAllArea(String id){

        return baseService.queryAllArea(id);

    }

    /**
     * @desc  查询全部疫苗 无参
     * @return
     */
    @RequestMapping("/queryallvaccine")
    public Map<String,Object> queryAllVaccine(){

        return baseService.queryAllVaccine();

    }
}
