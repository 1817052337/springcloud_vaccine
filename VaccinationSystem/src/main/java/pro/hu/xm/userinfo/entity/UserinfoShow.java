package pro.hu.xm.userinfo.entity;

import lombok.Data;

/**
 * @author Littiger
 * @title: UserinfoShow
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/119:12
 */
@Data
public class UserinfoShow {

    private Integer uid;

    private String name;

    private String number;

    private String regionprovinceid;

    private String regioncityid;

    private String regionareaid;

    private String provincename;

    private String cityname;

    private String areaname;

}
