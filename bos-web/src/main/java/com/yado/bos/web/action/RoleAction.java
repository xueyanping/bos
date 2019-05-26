package com.yado.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yado.bos.entity.Role;
import com.yado.bos.service.IRoleService;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String functionIds;
	
	@Autowired
	private IRoleService roleService;
	
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}


	public String add() {
		roleService.save(model,functionIds);
		return LIST;
	}
	
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"functions","users"});
		return NONE;
	}
	
	public String listajax() {
		List<Role> list = roleService.findAll();
		this.java2Json(list, new String[] {"functions","users"});
		return NONE;
	}

}
