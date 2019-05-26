package com.yado.bos.utils;

import java.util.List;
/**
 * 分页属性
 * @author Administrator
 *
 */

import org.hibernate.criterion.DetachedCriteria;
public class PageBean {

	 private int currentPage;
	 private int pageSize;	 
	 private int total;
	 private List rows;  //当前页需要展示的记录数   
     private DetachedCriteria detachedCriteria;//离线查询条件 
     
     
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", total=" + total + ", rows=" + rows
				+ ", detachedCriteria=" + detachedCriteria + "]";
	}
	
	
     
     
}