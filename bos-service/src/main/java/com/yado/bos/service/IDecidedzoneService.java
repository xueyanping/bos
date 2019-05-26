package com.yado.bos.service;

import com.yado.bos.entity.Decidedzone;
import com.yado.bos.utils.PageBean;

public interface IDecidedzoneService {
	
	public void save(Decidedzone model,String[] subareaid);

	public void pageQuery(PageBean pageBean);

}
