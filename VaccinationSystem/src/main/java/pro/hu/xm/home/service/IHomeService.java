package pro.hu.xm.home.service;

import java.util.Map;

/**
 * @author Littiger
 * @title: IHomeService
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/218:34
 */
public interface IHomeService {

    public Map<String,Object> queryHomeMess();

    public Map<String,Object> queryVaccineComplete(Integer vid);

    public Map<String,Object> queryHealth();

    public void updateRedisHotMess();

    public Map<String,Object> queryVaccineRank();


}
