package com.yado.bos.service;

import java.util.List;

import com.yado.bos.entity.Function;
import com.yado.bos.utils.PageBean;

public interface IFunctionService {

	

	List<Function> findAll();

	void save(Function model);

	List<Function> pageQuery(PageBean pageBean);

	List<Function> findMenu();

}
