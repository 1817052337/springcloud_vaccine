package pro.hu.xm.subscribe.mapper;

import org.apache.ibatis.annotations.*;
import pro.hu.xm.subscribe.entity.Subscribe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pro.hu.xm.subscribe.entity.SubscribeShow;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@Mapper
public interface SubscribeMapper extends BaseMapper<Subscribe> {

    @Select("select sid,s.uid,s.hvid,s.time,s.scode,hv.vid,u.name as name,z.name as hname,v.name as vname,provincename,cityname,areaname \n" +
            "from subscribe s,vaccinetype v,userinfo u,hospitalvaccine hv,\n" +
            "(select hid,h.name as name,regionprovinceid,regioncityid,regionareaid,p.name as provincename, c.name as cityname,a.name as areaname from hospital h,regionprovince p,regioncity c,regionarea a where h.regionprovinceid = p.id and h.regioncityid = c.id and h.regionareaid = a.id) z\n" +
            "where \n" +
            "s.hvid = hv.hvid AND\n" +
            "s.uid = u.uid AND\n" +
            "hv.hid = z.hid AND\n" +
            "hv.vid = v. vid ")
    public List<SubscribeShow> queryAll();

    @Select("select sid,s.uid,s.hvid,s.time,s.scode,hv.vid,u.name as name,z.name as hname,v.name as vname,provincename,cityname,areaname,hv.vid \n" +
            "from subscribe s,vaccinetype v,userinfo u,hospitalvaccine hv,\n" +
            "(select hid,h.name as name,regionprovinceid,regioncityid,regionareaid,p.name as provincename, c.name as cityname,a.name as areaname from hospital h,regionprovince p,regioncity c,regionarea a where h.regionprovinceid = p.id and h.regioncityid = c.id and h.regionareaid = a.id) z\n" +
            "where \n" +
            "u.name like #{name} AND\n" +
            "z.name like #{hname} AND\n" +
            "CAST(hv.vid as char) LIKE #{vid} AND\n" +
            "z.regionprovinceid LIKE #{regionprovinceid} AND\n" +
            "z.regioncityid LIKE #{regioncityid} AND\n" +
            "z.regionareaid LIKE #{regionareaid} AND\n" +
            "s.hvid = hv.hvid AND\n" +
            "s.uid = u.uid AND\n" +
            "hv.hid = z.hid AND\n" +
            "hv.vid = v. vid\n")
    public List<SubscribeShow> queryByName(@Param("name") String name,@Param("hname") String hname,@Param("vid") String vid,@Param("regionprovinceid") String regionprovinceid,@Param("regioncityid") String regioncityid,@Param("regionareaid") String regionareaid);

    @Delete("UPDATE subscribe set scode=IF(scode=1,0,1) WHERE sid = #{sid}")
    public int updateScode(@Param("sid") Integer sid);

    @Select(" SELECT DISTINCT u.phone,s.sid,s.scode from subscribe s, user u where s.sid = #{sid} and  s.uid = u.uid ")
    public Map<String,Object> queryOnePhone(@Param("sid") Integer sid);

}
