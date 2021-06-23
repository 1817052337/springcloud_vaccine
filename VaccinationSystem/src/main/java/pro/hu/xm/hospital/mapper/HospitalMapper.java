package pro.hu.xm.hospital.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pro.hu.xm.hospital.entity.Hospital;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pro.hu.xm.hospital.entity.HospitalShow;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@Mapper
public interface HospitalMapper extends BaseMapper<Hospital> {

    @Select(" select hid,h.name as name,regionprovinceid,regioncityid,regionareaid,p.name as provincename, c.name as cityname,a.name as areaname from hospital h,regionprovince p,regioncity c,regionarea a where h.regionprovinceid = p.id and h.regioncityid = c.id and h.regionareaid = a.id")
    public List<HospitalShow> queryAll();

    @Select("select hid,h.name as name,regionprovinceid,regioncityid,regionareaid,p.name as provincename, c.name as cityname,a.name as areaname from hospital h,regionprovince p,regioncity c,regionarea a where h.name like #{real_name} and h.regionprovinceid like #{regionprovinceid} and h.regioncityid like #{regioncityid} and h.regionareaid like #{regionareaid} and h.regionprovinceid = p.id and h.regioncityid = c.id and h.regionareaid = a.id")
    public List<HospitalShow> queryByName(@Param("real_name") String real_name,@Param("regionprovinceid") String regionprovinceid,@Param("regioncityid") String regioncityid,@Param("regionareaid") String regionareaid);

    @Select("select hid,h.name as name,regionprovinceid,regioncityid,regionareaid,p.name as provincename, c.name as cityname,a.name as areaname from hospital h,regionprovince p,regioncity c,regionarea a where h.name like #{name} and h.regionprovinceid like #{regionprovinceid} and h.regioncityid like #{regioncityid} and h.regionareaid like #{regioncityid} and h.regionprovinceid = p.id and h.regioncityid = c.id and h.regionareaid = a.id")
    public List<HospitalShow> queryByName1(Hospital hospital);

    @Delete("delete subscribe,survey,hospitalvaccine from subscribe,hospitalvaccine,survey where hospitalvaccine.hid = #{hid} AND subscribe.hvid = hospitalvaccine.hvid AND subscribe.sid = survey.sid ")
    public int delete(@Param("hid") Integer hid);

}
