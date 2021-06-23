package pro.hu.xm.hospital.entity;

import lombok.Data;

/**
 * @author Littiger
 * @title: HospitalShow
 * @projectName VaccinationSystem
 * @description: TODO
 * @date 2021/6/114:45
 */
@Data
public class HospitalShow {

    private Integer hid;

    private String name;

    private String regionprovinceid;

    private String regioncityid;

    private String regionareaid;

    private String provincename;

    private String cityname;

    private String areaname;

}
