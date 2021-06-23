package pro.hu.xm.survey.service;

import pro.hu.xm.survey.entity.Survey;
import com.baomidou.mybatisplus.extension.service.IService;
import pro.hu.xm.survey.entity.SurveyShow;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
public interface SURVEYervice {

    public List<SurveyShow> queryAllPage(Integer page,Integer limit);

    public List<SurveyShow> queryByName(String name, String vid, Integer page,Integer limit);

}
