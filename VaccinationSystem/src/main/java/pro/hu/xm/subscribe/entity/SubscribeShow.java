package pro.hu.xm.subscribe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Littiger
 * @title: SubscribeShow
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/120:05
 */
@Data
public class SubscribeShow {

    private Integer sid;

    private Integer uid;

    private Integer hvid;

    private Integer scode;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    private Integer vid;

    private String hname;

    private String name;

    private String vname;

    private String provincename;

    private String cityname;

    private String areaname;
}
