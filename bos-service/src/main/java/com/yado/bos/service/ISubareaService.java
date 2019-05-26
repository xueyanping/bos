package com.yado.bos.service;

import java.util.List;

import com.yado.bos.entity.Region;
import com.yado.bos.entity.Subarea;
import com.yado.bos.utils.PageBean;

public interface ISubareaService {

	void save(Subarea model);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findListNotAssociation();

	//根据定区id查询关联的分区
	List<Subarea> findListByDecidedzoneId(String decidedzoneId);

	List<Object> findSubareasGroupByProvince();

	void saveOrUpdate(List<Subarea> regionList);

	Subarea findSubareaById(String subareaId);

	void updateSubarea(Subarea model);

	void deleteSubarea(Subarea model);

	List<Region> findListByQ(String q);

}
