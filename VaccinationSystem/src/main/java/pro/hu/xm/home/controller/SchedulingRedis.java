package pro.hu.xm.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.hu.xm.home.service.IHomeService;

import javax.annotation.PostConstruct;

/**
 * @author Littiger
 * @title: SchedulingRedis
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/316:14
 */
@Component
@Configuration
@EnableScheduling
public class SchedulingRedis {

    @Autowired
    IHomeService homeService;

    /**
     * @desc  启动执行更新、定期更新 疫苗接种排行榜 redis库
     */
    @PostConstruct
    @Scheduled(cron = "15 * * * * ? ")
    public void queryHotMess(){

        System.out.println("更新redis库，疫苗接种排行榜");
        homeService.updateRedisHotMess();

    }

}
