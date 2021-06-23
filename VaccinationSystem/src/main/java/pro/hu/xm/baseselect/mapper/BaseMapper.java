package pro.hu.xm.baseselect.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pro.hu.xm.baseselect.entity.Area;
import pro.hu.xm.baseselect.entity.City;
import pro.hu.xm.baseselect.entity.Province;
import pro.hu.xm.baseselect.entity.VaccineBase;

import java.util.List;

/**
 * @author Littiger
 * @title: BaseMapper
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/210:06
 */
@Mapper
public interface BaseMapper {

    @Select(" select * from regionprovince")
    public List<Province> queryAllProvince();

    @Select(" SELECT * from regioncity WHERE province_code = (select province_code from regionprovince where id = #{id})")
    public List<City> queryAllCity(@Param("id") String id);

    @Select(" SELECT * from regionarea WHERE city_code = (select city_code from regioncity where id = #{id})")
    public List<Area> queryAllArea(@Param("id") String id);

    @Select(" select * from vaccinetype")
    public List<VaccineBase> queryAllVaccine();
}
