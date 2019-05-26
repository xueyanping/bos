package com.yado.bos.service;

import com.yado.bos.entity.User;
import com.yado.bos.utils.PageBean;

public interface IUserService {

	public User login(User model);
	
	public void editPassword(String id,String password);

	public void save(User model, String[] roleIds);

	public void pageQuery(PageBean pageBean);

}
