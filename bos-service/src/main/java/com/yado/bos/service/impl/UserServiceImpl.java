package com.yado.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yado.bos.dao.IUserDao;
import com.yado.bos.entity.Role;
import com.yado.bos.entity.User;
import com.yado.bos.service.IUserService;
import com.yado.bos.utils.MD5Utils;
import com.yado.bos.utils.PageBean;


@Service
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.REPEATABLE_READ,readOnly=false,timeout=30)
//以上三个值均为默认值另外timeout=30(默认情况下)
//@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;
	/***
	 * 用户登录
	 */
	public User login(User user) {
		//使用MD5加密密码
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByUsernameAndPassword(user.getUsername(),password);
	}
	
	@Override
	public void editPassword(String id, String password) {
		password = MD5Utils.md5(password);
		userDao.executeUpdate("user.editpassword", password,id);
		
	}

	
	/**
	 * 添加一个用户并关联角色
	 */
	@Override
	public void save(User model, String[] roleIds) {
		 String pass = MD5Utils.md5(model.getPassword());
		 model.setPassword(pass);
		 userDao.save(model);
		if(roleIds!=null && roleIds.length>0) {
			for (String id : roleIds) {
				Role role = new Role(id);
				model.getRoles().add(role);
			}
			
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
			userDao.pageQuery(pageBean);
	}
}
