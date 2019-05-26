package com.yado.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yado.bos.dao.IFunctionDao;
import com.yado.bos.entity.Function;
import com.yado.bos.entity.User;
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao  {
	

	@Override
	public List<Function> findAll() {
//		String hql = "from Function f where f.parentFunction is null";
		String hql = "FROM Function f WHERE f.parentFunction IS NULL";
		List<Function> list =  (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findFunctionByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r"
				+ " LEFT OUTER JOIN r.users u WHERE u.id = ?";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

	@Override
	public List<Function> findAllMenu() {
		String hql = "FROM Function f WHERE f.generatemenu = 1 ORDER BY f.zindex DESC";
		List<Function> list =  (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Function> findMenuByUserId(String id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r"
				+ " LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = 1 ORDER BY f.zindex DESC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}
	
}
