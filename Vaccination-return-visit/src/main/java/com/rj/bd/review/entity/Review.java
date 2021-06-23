package com.rj.bd.review.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author wxy
 * @desc review 实体类
 * @time 2021-06-01-10:02
 *
 */
@Data
public class Review {
    private String sid;
    private String uname;
    private String hname;
    private String vname;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
}
