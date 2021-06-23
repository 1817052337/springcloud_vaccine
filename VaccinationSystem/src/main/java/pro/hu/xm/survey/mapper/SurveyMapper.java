package pro.hu.xm.survey.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pro.hu.xm.survey.entity.Survey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pro.hu.xm.survey.entity.SurveyShow;

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
public interface SurveyMapper extends BaseMapper<Survey> {

    @Select("SELECT sid,vid,vname,l.name,health,time,text, GROUP_CONCAT(s.name) as symptom\n" +
            "from \n" +
            "(SELECT s.sid,hv.vid,u.name as name,v.name as vname,health,s.time,text, replace(REPLACE(symptom,\"[\",\"\"),\"]\",\"\") as symptom\n" +
            "from survey s,subscribe su,hospitalvaccine hv,vaccinetype v ,userinfo u\n" +
            "where\n" +
            "s.sid = su.sid AND\n" +
            "su.hvid = hv.hvid AND\n" +
            "hv.vid = v.vid AND\n" +
            "su.uid = u.uid) l,\n" +
            "symptom s\n" +
            "WHERE\n" +
            "symptom = \"\"\n" +
            "or\n" +
            "SUBSTRING_INDEX(SUBSTRING_INDEX(symptom,',',syid),',',-1) = s.syid\n" +
            "GROUP BY sid\n")
    public List<SurveyShow> queryAll();

    @Select("SELECT sid,vid,vname,l.name,health,time,text, GROUP_CONCAT(s.name) as symptom\n" +
            "from \n" +
            "(SELECT s.sid,hv.vid,u.name as name,v.name as vname,health,s.time,text, replace(REPLACE(symptom,\"[\",\"\"),\"]\",\"\") as symptom\n" +
            "from survey s,subscribe su,hospitalvaccine hv,vaccinetype v ,userinfo u\n" +
            "where\n" +
            "u.name like #{name} AND\n" +
            "cast(v.vid as char) like #{vid} AND\n" +
            "s.sid = su.sid AND\n" +
            "su.hvid = hv.hvid AND\n" +
            "hv.vid = v.vid AND\n" +
            "su.uid = u.uid) l,\n" +
            "symptom s\n" +
            "WHERE\n" +
            "symptom = \"\"\n" +
            "or\n" +
            "SUBSTRING_INDEX(SUBSTRING_INDEX(symptom,',',syid),',',-1) = s.syid\n" +
            "GROUP BY sid")
    public List<SurveyShow> queryByName(@Param("name") String name,@Param("vid") String vid);

}
