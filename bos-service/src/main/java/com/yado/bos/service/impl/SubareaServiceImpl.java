package com.yado.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IRegionDao;
import com.yado.bos.dao.ISubareaDao;
import com.yado.bos.entity.Region;
import com.yado.bos.entity.Subarea;
import com.yado.bos.service.IRegionService;
import com.yado.bos.service.ISubareaService;
import com.yado.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Autowired
	ISubareaDao subareaDao;
	@Autowired
	IRegionService regionService;

	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(detachedCriteria);
	}

	@Override
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		DetachedCriteria add = criteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		List<Subarea> list = subareaDao.findByCriteria(add);
		return list;
	}

	@Override
	public List<Object> findSubareasGroupByProvince() {

		return subareaDao.findSubareasGroupByProvince();
	}

	@Override
	public void saveOrUpdate(List<Subarea> subareaList) {
		for (Subarea subarea : subareaList) {
			subareaDao.saveOrUpdate(subarea);
		}
	}

	@Override
	public Subarea findSubareaById(String subareaId) {
		Subarea subarea = subareaDao.findById(subareaId);
		return subarea;
	}

	@Override
	public void updateSubarea(Subarea model) {
//		List<Region> allRegion = regionService.findAllArea();
//		if(allRegion !=null && allRegion.size()>0) {
//			System.out.println(allRegion.size());
//		}
		String regionInfo = model.getRegion().getId();
		if (regionInfo.contains("市")) {
			String regionId = null;
			List<Region> allRegion = regionService.findAllArea();
			for (Region region : allRegion) {
				if ((region.getProvince() + "" + region.getCity() + "" + region.getDistrict()).equals(regionInfo)) {
					String id = region.getId();
					model.getRegion().setId(id);
					break;
				}
			}

		}

		subareaDao.saveOrUpdate(model);
	}

	@Override
	public void deleteSubarea(Subarea model) {
		subareaDao.delete(model);
	}

	@Override
	public List<Region> findListByQ(String q) {
		return null;
	}

}
