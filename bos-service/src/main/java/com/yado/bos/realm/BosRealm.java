package com.yado.bos.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.yado.bos.dao.IFunctionDao;
import com.yado.bos.dao.IUserDao;
import com.yado.bos.entity.Function;
import com.yado.bos.entity.User;
import com.yado.bos.utils.BOSUtils;

/**
 * 
 * @author Administrator
 *
 */
public class BosRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IFunctionDao functionDao;

	
	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//info.addStringPermission("staff-list");
		//TODO 后期需要根据登录用户查询数据库，获取实际对应的权限
		//此方法中获取不到session，故不可用BOSUils获取登录用户
		//User user = BOSUtils.getLoginUser();
		User user =  (User) SecurityUtils.getSubject().getPrincipal(); 
		List<Function> list = null;
		
		//超级管理员为系统内置用户拥有所有操作权限
		if(user.getUsername().equals("xue")) {
			DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
			list = functionDao.findByCriteria(criteria);
		}else {
			list = functionDao.findFunctionByUserId(user.getId());
		}
		
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}
	
	
	
	
	//认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证方法");
		UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
		//根据用户名查询数据库中的密码
		String username = passwordToken.getUsername();
		//框架负责比对数据库中的密码与页面输入的密码是否一致		
		User user = userDao.findUserByUsername(username);
		if(user == null) {
			//页面输入的用户名经查询后不存在
			return null;
		}
		//简单认证信息对象（user.getPassword() 与 传来的token中的密码比对若不匹配则抛出异常在action中捕获）
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

}
