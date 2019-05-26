package com.yado.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IDecidedzoneDao;
import com.yado.bos.dao.ISubareaDao;
import com.yado.bos.entity.Decidedzone;
import com.yado.bos.entity.Subarea;
import com.yado.bos.service.IDecidedzoneService;
import com.yado.bos.utils.PageBean;
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private ISubareaDao subareaDao;
	@Override
	public void save(Decidedzone model, String[] subareaid) {
		decidedzoneDao.save( model);
		for (String id : subareaid) {
			Subarea subarea = subareaDao.findById(id);
			subarea.setDecidedzone(model);
		}
	}
	@Override
	public void pageQuery(PageBean pageBean) {
			decidedzoneDao.pageQuery(pageBean);
	}

}
