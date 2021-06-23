package pro.hu.xm.baseselect.service;

import java.util.Map;

/**
 * @author Littiger
 * @title: IBaseService
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/210:09
 */
public interface IBaseService {

    public Map<String,Object> queryAllProvince();

    public Map<String,Object> queryAllCity(String province_code);

    public Map<String,Object> queryAllArea(String city_code);

    public Map<String,Object> queryAllVaccine();

}
