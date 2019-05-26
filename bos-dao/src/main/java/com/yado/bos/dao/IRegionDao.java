package com.yado.bos.dao;

import java.util.List;

import com.yado.bos.entity.Region;

public interface IRegionDao extends IBaseDao<Region> {

	List<Region> findListByQ(String q);

	

}
