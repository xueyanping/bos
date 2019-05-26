package com.yado.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yado.bos.entity.Workordermanage;
import com.yado.bos.service.IWorkordermanagerService;
@Controller
public class WorkOrderManagerAction extends BaseAction<Workordermanage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IWorkordermanagerService workordermanagerService;
	
	public String add() {
		int i = 0;
		try {
			workordermanagerService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			i = -1;
		}
		
		try {
			ServletActionContext.getResponse().getWriter().println(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}

}
