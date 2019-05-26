package com.yado.bos.service;

import java.util.List;

import com.yado.bos.entity.Role;
import com.yado.bos.utils.PageBean;

public interface IRoleService {

	public void save(Role model, String functionIds);

	public void pageQuery(PageBean pageBean);

	public List<Role> findAll();
}
