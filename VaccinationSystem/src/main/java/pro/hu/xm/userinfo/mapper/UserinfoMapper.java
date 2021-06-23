package pro.hu.xm.userinfo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pro.hu.xm.userinfo.entity.Userinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pro.hu.xm.userinfo.entity.UserinfoShow;

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
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    @Select("select uid,u.name as name,number,regionprovinceid,regioncityid,regionareaid,p.name as provincename, c.name as cityname,a.name as areaname from userinfo u,regionprovince p,regioncity c,regionarea a where u.regionprovinceid = p.id and u.regioncityid = c.id and u.regionareaid = a.id")
    public List<UserinfoShow> queryAll();

    @Select("select uid,u.name as name,number,regionprovinceid,regioncityid,regionareaid,p.name as provincename, c.name as cityname,a.name as areaname from userinfo u,regionprovince p,regioncity c,regionarea a where u.name like #{name} and u.regionprovinceid like #{regionprovinceid} and u.regioncityid like #{regioncityid} and u.regionareaid like #{regionareaid} and u.regionprovinceid = p.id and u.regioncityid = c.id and u.regionareaid = a.id")
    public List<UserinfoShow> queryByName(@Param("name") String name, @Param("regionprovinceid") String regionprovinceid, @Param("regioncityid") String regioncityid, @Param("regionareaid") String regionareaid);

    @Delete("delete subscribe,survey,userinfo from subscribe,survey,userinfo where subscribe.uid = #{uid} AND subscribe.sid = survey.sid AND userinfo.uid = subscribe.uid")
    public int delete(@Param("uid") Integer uid);

}
