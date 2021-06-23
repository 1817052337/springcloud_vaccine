package com.rj.bd.Vaccine.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.rmi.server.UID;

/**
 * @author ZC
 * @desc
 * @time 2021--06--02 8:49
 */
@Data
public class Userinfo {
   @TableId
   private String uid;
   private String name;
   private  String number;
   private  String regionprovinceid;
   private  String regioncityid;
   private  String regionareaid;


}
