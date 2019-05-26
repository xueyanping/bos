package com.yado.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IWorkordermanagerDao;
import com.yado.bos.entity.Workordermanage;
import com.yado.bos.service.IWorkordermanagerService;
@Transactional
@Service
public class WorkordermanagerServiceImpl implements IWorkordermanagerService {

	@Autowired
	private IWorkordermanagerDao workordermanagerDao;
	@Override
	public void save(Workordermanage model) {
		workordermanagerDao.save(model);
	}

	
	
}
