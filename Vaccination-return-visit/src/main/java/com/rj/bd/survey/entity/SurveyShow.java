package com.rj.bd.survey.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author mxj
 * @desc SurveyShow实体类
 * @since 2021-06-01
 */

public class SurveyShow {

    private Integer sid;

    private Integer vid;

    private String text;

    private List<String> symptom;

    private Integer health;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;

    private String name;

    private String vname;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {

        if (this.health == 0) {
            this.symptom = new ArrayList<>();
        } else if (this.health == 1) {
            this.symptom = Arrays.asList(symptom.split(","));
        }

    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }
}
