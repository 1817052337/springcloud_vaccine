package pro.hu.xm.home.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pro.hu.xm.home.entity.Health;
import pro.hu.xm.home.entity.HomeMess;
import pro.hu.xm.home.entity.HotMess;
import pro.hu.xm.home.entity.VaccineComplete;

import java.util.List;

/**
 * @author Littiger
 * @title: HomeMapper
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/210:27
 */
@Mapper
public interface HomeMapper {

    @Select("SELECT hospitalcount,vaccinecount,subusercount,completecount \n" +
            "from\n" +
            "(SELECT count(*) as hospitalcount from hospital) a,\n" +
            "(SELECT count(*) as vaccinecount from vaccinetype) b,\n" +
            "(SELECT count(*) as subusercount from subscribe where REPLACE(time,'-','') =  DATE_FORMAT(NOW(),'%Y%m%d')) c,\n" +
            "(SELECT count(*) as completecount from subscribe where REPLACE(time,'-','') =  DATE_FORMAT(NOW(),'%Y%m%d') AND scode = 1) d")
    public HomeMess queryHomeMess();

    @Select("SELECT  p.name, count(sid) as value from subscribe s ,hospitalvaccine hv ,hospital h,regionprovince p where hv.vid = #{vid} AND s.hvid = hv.hvid AND hv.hid = h.hid AND h.regionprovinceid = p.id\n" +
            "GROUP BY regionprovinceid  ")
    public List<VaccineComplete> queryVaccineComplete(@Param("vid") Integer vid);

    @Select("SELECT a.name,  ROUND(unhealth/alls,4) as value,unhealth as count\n" +
            "from \n" +
            "(SELECT count(s.sid) as alls ,v.name as name,v.vid\n" +
            "from survey s,subscribe su,hospitalvaccine hv,vaccinetype v \n" +
            "where\n" +
            "s.sid = su.sid AND\n" +
            "su.hvid = hv.hvid AND\n" +
            "hv.vid = v.vid\n" +
            "GROUP BY\n" +
            "v.vid) a\n" +
            "LEFT JOIN\n" +
            "(SELECT count(s.sid) as unhealth ,v.name as name,v.vid\n" +
            "from survey s,subscribe su,hospitalvaccine hv,vaccinetype v \n" +
            "WHERE\n" +
            "s.health = 1 AND\n" +
            "s.sid = su.sid AND\n" +
            "su.hvid = hv.hvid AND\n" +
            "hv.vid = v.vid\n" +
            "GROUP BY\n" +
            "v.vid) b\n" +
            "ON\n" +
            "a.vid = b.vid")
    public List<Health> queryHealth();

    @Select("select name from vaccinetype where vid = #{vid}")
    public String queryOneName(@Param("vid") Integer vid);

    @Select("SELECT\n" +
            "v.vid as vid,count as value,v.name as name\n" +
            "from\n" +
            "vaccinetype v\n" +
            "LEFT JOIN\n" +
            "(SELECT\n" +
            "count(s.sid) as count , v.vid\n" +
            "FROM\n" +
            "subscribe s,hospitalvaccine hv,vaccinetype v\n" +
            "WHERE\n" +
            "s.scode = 1 AND\n" +
            "s.hvid = hv.hvid AND\n" +
            "hv.vid = v.vid\n" +
            "GROUP BY\n" +
            "hv.vid\n" +
            "ORDER BY\n" +
            "count\n" +
            "desc) a\n" +
            "ON\n" +
            "a.vid = v.vid\n" +
            "ORDER BY\n" +
            "count \n" +
            "DESC")
    public List<HotMess> queryHotMess();
}
