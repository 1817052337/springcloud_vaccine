package pro.hu.xm.vaccinetype.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pro.hu.xm.vaccinetype.entity.Vaccinetype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface VaccinetypeMapper extends BaseMapper<Vaccinetype> {

    @Select("select * from vaccinetype")
    public List<Vaccinetype> queryAll();

}
