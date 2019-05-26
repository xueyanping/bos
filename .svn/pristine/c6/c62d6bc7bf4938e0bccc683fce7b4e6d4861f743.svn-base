package com.yado.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.yado.bos.entity.User;

public class BOSUtils {

	//获取session对象
	public static HttpSession getSessiion() {
		return ServletActionContext.getRequest().getSession();
		}
	
	//获取用户对象
	public static User getLoginUser() {
		return (User) BOSUtils.getSessiion().getAttribute("user");
	}
}
