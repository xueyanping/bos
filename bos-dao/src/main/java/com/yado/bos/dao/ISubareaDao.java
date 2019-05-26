package com.yado.bos.dao;

import java.util.List;

import com.yado.bos.entity.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea>{

	List<Object> findSubareasGroupByProvince();
	

}
