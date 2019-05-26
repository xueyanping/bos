package com.yado.bos.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体
 */

public class Function implements java.io.Serializable {

	// Fields

	private String id;
	private Function parentFunction;//当前权限的上级权限
	private String name;
	private String code;
	private String description;
	private String page;
	private String generatemenu;//是否生成菜单，1：是 0：否
	private Integer zindex;
	private Set roles = new HashSet(0);//当前权限对应的多个角色
	private Set children = new HashSet(0);//当前权限的下级权限
	
	
	public Function() {}
	
	
	public Function(String functionId) {
		this.id = functionId;
	}
	
	//easyui-combotree中回显权限名称需要'text' 字段但返回的json中无 'text'属性，只有 'name'属性 
	public String getText(){
		return name;
	}
	
	//由于左侧的菜单栏需要查询数据库进行动态展示，返回的json不可以是简单json（简单json中无 'pid'属性）原先的menu.json中有 'pid'属性
	public String getpId() {
		
		if(this.parentFunction!=null) {
			return this.parentFunction.getId();
		}else {
			return "0";
		}
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Function getParentFunction() {
		return parentFunction;
	}
	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public Set getRoles() {
		return roles;
	}
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
}