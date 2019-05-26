package com.yado.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IFunctionDao;
import com.yado.bos.entity.Function;
import com.yado.bos.entity.User;
import com.yado.bos.service.IFunctionService;
import com.yado.bos.utils.BOSUtils;
import com.yado.bos.utils.PageBean;
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
	@Autowired
	private IFunctionDao functionDao;

	@Override
	public List<Function> findAll() {
		
		return functionDao.findAll();
	}

	@Override
	public void save(Function model) {
			functionDao.save(model);
	}

	@Override
	public List<Function> pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
		return null;
	}

	@Override
	public List<Function> findMenu() {
		User loginUser = BOSUtils.getLoginUser();
		List<Function> list = null;
		if(loginUser.getUsername().equals("xue")) {
			//如果是超级管理员则查询所有权限
			list = functionDao.findAllMenu();
		}else {
			//其他用户需要根据登录用户id查询数据库
			if(loginUser.getId()!=null) {
				list = functionDao.findMenuByUserId(loginUser.getId());
			}
			
		}
		return list;
	}
	

}
