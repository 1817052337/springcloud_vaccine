package pro.hu.xm.vaccinetype.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Echo
 * @since 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Vaccinetype implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    private String name;


}
