package com.yado.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yado.bos.entity.Noticebill;
import com.yado.bos.entity.Staff;
import com.yado.bos.entity.User;
import com.yado.bos.service.IDecidedzoneService;
import com.yado.bos.service.INoticebillService;
import com.yado.bos.utils.BOSUtils;
import com.yado.cxf.Customer;
import com.yado.cxf.ICustomerService;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ICustomerService customerService;

	public String findCustomerByTelephone() {
		String telephone = model.getTelephone();
		Customer customer = customerService.findCustomerByTelephone(telephone);
		this.java2Json(customer, new String[] {});
		return NONE;
	}
	
	@Autowired
	private INoticebillService noticebillService;
	
	/**
	 * 保存一个业务通知单并尝试自动分单
	 */
	
	public String add() {		
		noticebillService.save(model);			
		return "noticebill_add";
	}
	
}
