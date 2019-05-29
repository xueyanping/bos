package com.yado.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IRegionDao;
import com.yado.bos.entity.Region;
import com.yado.bos.entity.Subarea;
import com.yado.bos.service.IRegionService;
import com.yado.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;
	@Override
	public void save(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.save(region);

		}
			}
	@Override
	public void saveOrUpdate(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
		
		
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
		 
	}
	@Override
	public List<Region> findAllArea() {
		
		 List<Region> list = regionDao.findAll();
		 return list;
	}
	@Override
	public List<Region> findListByQ(String q) {
		
		return regionDao.findListByQ(q);
	}

}
