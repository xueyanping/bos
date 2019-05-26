package com.yado.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.yado.bos.dao.IDecidedzoneDao;
import com.yado.bos.dao.INoticebillDao;
import com.yado.bos.dao.IWorkbillDao;
import com.yado.bos.entity.Decidedzone;
import com.yado.bos.entity.Noticebill;
import com.yado.bos.entity.Staff;
import com.yado.bos.entity.User;
import com.yado.bos.entity.Workbill;
import com.yado.bos.service.INoticebillService;
import com.yado.bos.utils.BOSUtils;
import com.yado.cxf.ICustomerService;
@Transactional
@Service
public class NoticebillServiceImpl implements INoticebillService {

	@Autowired
	private INoticebillDao noticebillDao;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao; 
	
	@Autowired
	private IWorkbillDao WorkbillDao;
	
	@Override
	public void save(Noticebill model) {
		User user = BOSUtils.getLoginUser();
		model.setUser(user);
		noticebillDao.save(model);		
		//获取客户的取件地址
		String pickaddress = model.getPickaddress();
		//远程调用crm服务，根据取件地址查询定区id
		String decidedzoneId = customerService.findDecidedzoneIdbyAddress(pickaddress);
		
		if(decidedzoneId!=null) {
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			//获取取派员(在Decidedzone中配置了立即加载，因此会将Staff对象同时查出来，在后面的getStaff（）中就不会查询Staff所以不会发送sql语句)
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);
			//设置分单类型为：自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			Workbill workbill = new Workbill();
			workbill.setNoticebill(model);//工单关联页面通知单
			workbill.setStaff(staff);//工单关联取派员
			workbill.setType(Workbill.TYPE_1);//工单类型
			workbill.setPickstate(Workbill.PICKSTATE_NO);
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间，当前系统时间				
			workbill.setAttachbilltimes(0);//追单次数			
			workbill.setRemark(model.getRemark());//备注信息
			WorkbillDao.save(workbill);
		}else {
			//没有查询到定区id，不能完成自动分单(人工分单)
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}

}
