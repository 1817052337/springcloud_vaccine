package com.rj.bd.Vaccine.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rj.bd.Vaccine.entity.Hospitalvaccine;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZC
 * @desc
 * @time 2021--06--02 10:13
 */
@Mapper
public interface hospitalvaccineMapper  extends BaseMapper<Hospitalvaccine> {
}
