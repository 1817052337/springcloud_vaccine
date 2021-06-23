package pro.hu.xm.admin.service;

import pro.hu.xm.admin.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
public interface ADMINervice {

    public Map<String,Object> login(Admin admin);

    public boolean verification(String token);

}
