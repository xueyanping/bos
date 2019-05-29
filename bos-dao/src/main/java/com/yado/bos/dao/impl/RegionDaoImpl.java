package com.yado.bos.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.yado.bos.dao.IRegionDao;
import com.yado.bos.entity.Region;
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		String Hql = "from Region r where r.shortcode like ? or r.citycode like ? or r.province like ? or r.district like ? or r.city like ? ";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(Hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}
	
	
	

	

}
