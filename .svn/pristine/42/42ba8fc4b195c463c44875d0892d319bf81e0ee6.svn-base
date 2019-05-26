package com.yado.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yado.bos.entity.Decidedzone;
import com.yado.bos.service.IDecidedzoneService;
import com.yado.cxf.Customer;
import com.yado.cxf.ICustomerService;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	    //属性驱动接收多个分区Id
		private String[] subareaid;

		
		public void setSubareaid(String[] subareaid) {
			this.subareaid = subareaid;
		}


		public String add() {
			decidedzoneService.save(model, subareaid);
			return LIST;
		}
		
		
		/**
		 * 分页查询
		 * @return
		 */
		public String pageQuery() {

			decidedzoneService.pageQuery(pageBean);
			java2Json(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
			return NONE;
		}
		
		//注入crm代理对象
		@Autowired
		private ICustomerService proxy;
		
		public String findAllNotAssociation() {
			List<Customer> notAssociation = proxy.findAllNotAssociation();
			java2Json(notAssociation, new String[] {});
			return NONE;
		}
		
		public String findAllHadAssociation() {
			String id = model.getId();
			List<Customer> allHadAssociation = proxy.findAllHadAssociation(id);
			java2Json(allHadAssociation, new String[] {});
			return NONE;
		}
		
		private List<Integer> customerIds;
		
		public String assigncustomerstodecidedzone() {
			proxy.assigncustomerstodecidedzone(model.getId(), customerIds);
			return LIST;
		}


		public List<Integer> getCustomerIds() {
			return customerIds;
		}


		public void setCustomerIds(List<Integer> customerIds) {
			this.customerIds = customerIds;
		}
		
		

}

