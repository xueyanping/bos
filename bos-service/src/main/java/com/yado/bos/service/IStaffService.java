package com.yado.bos.service;

import java.util.List;

import com.yado.bos.entity.Staff;
import com.yado.bos.utils.PageBean;

public interface IStaffService {

	public void save(Staff model) ;

	public void pageQuery(PageBean pageBean);

	public void deleteBenth(String ids);

	public Staff findById(String id);

	public void update(Staff staff);

	public List<Staff> listNotDelete();

	public void doBack(String ids);
		

	
}
