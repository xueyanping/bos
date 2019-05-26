package com.yado.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.yado.bos.entity.Staff;
import com.yado.bos.service.IStaffService;

@Controller
public class StaffAction extends BaseAction<Staff>{

	/**
	 * 取派员管理
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IStaffService staffService;

	public String addStaff() {
		staffService.save(model);
		return LIST;
	}
	
	
//	private int page;
//	private int rows;


	/**
	 * 分页查询员工
	 * @return
	 */
	public String pageQuery() {
//		PageBean pageBean = new PageBean();
//		pageBean.setCurrentPage(page);
//		pageBean.setPageSize(rows);
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
//		pageBean.setDetachedCriteria(detachedCriteria);
		staffService.pageQuery(pageBean);
		java2Json(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","decidedzones"});
		return NONE;
	}
	
	//属性驱动，接收页面传来的ids
	private String ids;
	private String rowId;


	public String getRowId() {
		return rowId;
	}


	public void setRowId(String rowId) {
		this.rowId = rowId;
	}


	/*
	 * 取派员批量删除
	 */
	@RequiresPermissions("staff-delete")//执行该方法需要登录用户具有 "staff-delete" 权限
	public String deleteBanch() {
		
		staffService.deleteBenth(ids);
		 
		return LIST;
	}
	
	public String view() throws IOException {
		Staff staff = staffService.findById(rowId);
		this.java2Json(staff, new String[] {"decidedzones"});		
		return NONE;
	}
	
	//还原取派员状态
	public String doBack() throws IOException {
	staffService.doBack(ids);
		return LIST;
	}
	
	/*
	 * 取派员信息更新
	 */
	public String edit() {
		Staff staff = staffService.findById(model.getId());
		//使用页面提交的数据进行覆盖
		 staff.setName(model.getName());
		 staff.setTelephone(model.getTelephone());
		 staff.setDeltag(model.getDeltag());
		 staff.setStandard(model.getStandard());
		 staff.setHaspda(model.getHaspda());
		 staff.setStation(model.getStation());
		 staffService.update(staff);	 
		return LIST;
	}
	
	/**
	 * 查询所有未删除的数据并返回json
	 * @return
	 */
	
	public String listajax() {
		List<Staff> listNotDelete = staffService.listNotDelete();
		this.java2Json(listNotDelete, new String[] {"decidedzones"});
		return NONE;
	}
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
