package com.yado.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IRoleDao;
import com.yado.bos.entity.Function;
import com.yado.bos.entity.Role;
import com.yado.bos.service.IRoleService;
import com.yado.bos.utils.PageBean;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	@Override
	public void save(Role model, String functionIds) {
		//此时model为持久态
		roleDao.save(model);
		if(StringUtils.isNotBlank(functionIds)) {
			String[] fIds = functionIds.split(",");
			for(String functionId:fIds) {
				//此时function为托管状态，因为有id，可以通过持久态关联托管态，但不可以通过持久态关联瞬时态
				Function function = new Function(functionId);
				//此处由于function与Role为多对多关系，function.hbm中 role放弃外键维护，所以由function维护外键
				model.getFunctions().add(function);
			}
		}
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}
	@Override
	public List<Role> findAll() {		
		return roleDao.findAll();
	}

}
