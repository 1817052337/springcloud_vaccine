package pro.hu.xm.survey.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import pro.hu.xm.survey.entity.Survey;
import pro.hu.xm.survey.entity.SurveyShow;
import pro.hu.xm.survey.mapper.SurveyMapper;
import pro.hu.xm.survey.service.SURVEYervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@Service
public class SurveyServiceImpl implements SURVEYervice {

    @Autowired
    SurveyMapper surveyMapper;

    @Override
    public List<SurveyShow> queryAllPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        return surveyMapper.queryAll();
    }

    @Override
    public List<SurveyShow> queryByName(String name, String vid, Integer page, Integer limit) {
        String real_name = "%"+name+"%";

        if ("".equals(vid) || vid == null){
            vid = "%";
        }

        PageHelper.startPage(page,limit);
        return surveyMapper.queryByName(real_name,vid);

    }
}
