package com.yado.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IStaffDao;
import com.yado.bos.entity.Staff;
import com.yado.bos.service.IStaffService;
import com.yado.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	IStaffDao staffDao;
	
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
			staffDao.pageQuery(pageBean);		
	}

	@Override
	public void deleteBenth(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for (String id : staffIds) {
				staffDao.executeUpdate("staff-delete", id);
			}
		}
				
	}
	
	
	@Override
	public void doBack(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for (String id : staffIds) {
				staffDao.executeUpdate("staff-back", id);
			}
		}
		
	}


	@Override
	public Staff findById(String id) {		
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public List<Staff> listNotDelete() {
			
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.eqOrIsNull("deltag", "0"));
		List list = staffDao.findByCriteria(detachedCriteria);
		return list;
		
	}

	
	
}
