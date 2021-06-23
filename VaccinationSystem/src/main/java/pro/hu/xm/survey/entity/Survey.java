package pro.hu.xm.survey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Survey implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    private String text;

    private String symptom;

    private String health;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;


}
