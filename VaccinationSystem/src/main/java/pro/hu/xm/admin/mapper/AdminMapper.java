package pro.hu.xm.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pro.hu.xm.admin.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
