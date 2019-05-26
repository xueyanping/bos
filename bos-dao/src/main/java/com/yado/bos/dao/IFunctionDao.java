package com.yado.bos.dao;


import java.util.List;

import com.yado.bos.entity.Function;

public interface IFunctionDao extends IBaseDao<Function>{

	List<Function> findFunctionByUserId(String id);

	List<Function> findAllMenu();

	List<Function> findMenuByUserId(String id);
	
}
