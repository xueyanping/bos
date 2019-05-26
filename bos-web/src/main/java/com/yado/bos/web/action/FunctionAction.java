package com.yado.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yado.bos.entity.Function;
import com.yado.bos.service.IFunctionService;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IFunctionService functionService;
	
	/**
	 * 查询所有权限返回json数据
	 * @return
	 */
	public String listajax() {
		List<Function> list = functionService.findAll();
		this.java2Json(list, new String[]{"parentFunction","roles"});
		return NONE;
	}
	
	/**
	 * 根据当前登录用户查询所对应的菜单数据，返回json
	 * @return
	 */
	public String findMenu() {
		List<Function> list = functionService.findMenu();
		this.java2Json(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String add() {
		Function parentFunction = model.getParentFunction();
		if(parentFunction!= null && parentFunction.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionService.save(model);
		return LIST;
	}
	
	public String pageQuery() {
		//此处存在一个BUG由于Function对象与分页时页面传来的均有 page 属性 spring有限为model即Function中的page属性注入值导致
		//本该分页时的page值注给了Function的page导致分页失败
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		 functionService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[] {"parentFunction","roles","children"});
		return NONE;
	}

	
	
}
