package com.yado.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yado.bos.entity.Region;
import com.yado.bos.utils.PageBean;

/**
 * 持久层通用接口
 * @author zhaoqx
 *
 * @param <T>
 */
public interface IBaseDao<T> {
    public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	public void executeUpdate(String queryName,Object... objects);
	public void pageQuery(PageBean pageBean);
	void saveOrUpdate(T entity);
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
