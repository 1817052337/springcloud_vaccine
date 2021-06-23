package pro.hu.xm.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.hu.xm.home.service.IHomeService;

import java.util.Map;

/**
 * @author Littiger
 * @title: HomeController
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/218:39
 */
@RestController
@RequestMapping
@CrossOrigin("*")
public class HomeController {

    @Autowired
    IHomeService homeService;

    /**
     * @desc  接口返回今日预约总数、疫苗接种点、疫苗种类、今日完成接种 信息
     * @return
     */
    @RequestMapping("/queryhomemess")
    public Map<String,Object> queryHomeMess(){

        return homeService.queryHomeMess();

    }

    /**
     * @desc  接口返回 各省份完成接种数量 需要疫苗id
     * @param vid
     * @return
     */
    @RequestMapping("/queryvaccinecomplete")
    public Map<String,Object> queryVaccineComplete(Integer vid){

        return homeService.queryVaccineComplete(vid);

    }

    /**
     * @desc  接口返回 各疫苗不良反应率
     * @return
     */
    @RequestMapping("/queryunhealth")
    public Map<String,Object> queryUnhealth(){

        return homeService.queryHealth();

    }

    /**
     * @desc  接口返回 疫苗接种排行榜
     * @return
     */
    @RequestMapping("/queryvaccinerank")
    public Map<String,Object> test(){

        return homeService.queryVaccineRank();

    }

}
