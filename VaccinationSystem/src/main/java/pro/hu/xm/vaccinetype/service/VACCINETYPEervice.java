package pro.hu.xm.vaccinetype.service;

import pro.hu.xm.vaccinetype.entity.Vaccinetype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
public interface VACCINETYPEervice {

    public List<Vaccinetype> queryAllPage(Integer page, Integer limit);

    public Map<String,Object> add(String name);

    public Map<String,Object> edit(Integer vid,String name);

}
