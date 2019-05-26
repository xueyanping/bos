package com.yado.bos.web.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yado.bos.entity.User;
import com.yado.bos.utils.BOSUtils;

public class LoginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
//		ActionProxy proxy = invocation.getProxy();
//		String actionname = proxy.getActionName();
//		String nameSpace = proxy.getNamespace();
//		String url = nameSpace+actionname;
			
		User user = BOSUtils.getLoginUser(); 
			if(user!=null) {				
				return invocation.invoke();
			}else {
				return "login";
			}
		
	}

}
