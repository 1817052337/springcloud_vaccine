package com.rj.bd.Vaccine.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.bd.Vaccine.dao.VaccineTypeMapper;
import com.rj.bd.Vaccine.entity.Vaccinetype;
import com.rj.bd.utils.JsonUtil;
import com.rj.bd.utils.Putdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ZC
 * @desc
 * @time 2021--06--01 20:22
 */
@Service("vaccinetypeservice")
public class VaccineTypeService {
   @Autowired
   private VaccineTypeMapper vaccineTypeMapper;

   public Map<String, Object> queryym(String like, String token, Integer page, Integer limit) {
  /*    Map<String, Object> power = TokenService.getUserPower(token);
      if (power != null) {
         return power;
      }*/
      LambdaQueryWrapper<Vaccinetype> LambdaQueryWrapper = Wrappers.lambdaQuery();
      LambdaQueryWrapper.like(Vaccinetype::getName , like);
      Page<Vaccinetype> pages=new Page<>(page,limit);
      IPage<Vaccinetype> ipages=vaccineTypeMapper.selectPage(pages,LambdaQueryWrapper);
         return Putdata.printfByPage(0,"获取成功",ipages.getRecords(),ipages.getTotal());
   }
}
