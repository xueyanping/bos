package com.yado.bos.web.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yado.bos.entity.Region;
import com.yado.bos.entity.Subarea;
import com.yado.bos.service.IRegionService;
import com.yado.bos.service.ISubareaService;
import com.yado.bos.utils.FileUtils;
import com.yado.bos.utils.PinYin4jUtils;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File subareaFile;
	
	@Autowired
	private ISubareaService subareaService;
	
	private String subareaId;
	
	
	
	public void setSubareaFile(File subareaFile) {
		this.subareaFile = subareaFile;
	}
	
	
	public void setSubareaId(String subareaId) {
		this.subareaId = subareaId;
	}	


	public String add() {		
		subareaService.save(model);
		return LIST;
	}
	
	public String findSubareaById() {
	  Subarea subarea = subareaService.findSubareaById(subareaId);
	  this.java2Json(subarea, new String[] {"decidedzone","subareas"});
		return NONE;
	}
	
	public String edit() {
		subareaService.updateSubarea(model);
			return LIST;
		}
	
	public String delete() {
		Subarea subarea = subareaService.findSubareaById(subareaId);
		subareaService.deleteSubarea(subarea);
			return LIST;
		}
	
	/*
	 * 按条件分页查询
	 */
	public String pageQuery() {
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//
		String addresskey = model.getAddresskey();
		if(StringUtils.isNoneBlank(addresskey)) {
			detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		Region region = model.getRegion();
		
		if(region != null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			detachedCriteria.createAlias("region", "r");
			if(StringUtils.isNoneBlank(province)) {
				//通过多表的关联查询
				//agrs1 关联表的属性名称
				//args2 别名名称（随意）				
				detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			
			if(StringUtils.isNoneBlank(city)) {
				//通过多表的关联查询
				//agrs1 关联表的属性名称
				//args2 别名名称（随意）				
				detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			
			if(StringUtils.isNoneBlank(district)) {
				//通过多表的关联查询
				//agrs1 关联表的属性名称
				//args2 别名名称（随意）				
				detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
			}
			
		}
		
		 subareaService.pageQuery(pageBean);
		 java2Json(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","subareas","decidedzone"});
		 return NONE;
	}
	
		//分区导入
		public String importXls() {
			HSSFWorkbook hsw;
			List<Subarea> subareaList = new ArrayList<>();		
			try {
				
				hsw = new HSSFWorkbook(new FileInputStream(subareaFile));
				HSSFSheet sheet = hsw.getSheet("sheet1");
				for (Row row : sheet) {
					int rowNum = row.getRowNum();
					if(rowNum == 0) {
						continue;
					}
					
					String subareaId = row.getCell(0).getStringCellValue();
					String regionId = row.getCell(1).getStringCellValue();
					Region region = new Region(regionId);
					String addresskey = row.getCell(2).getStringCellValue();
					String startnum = row.getCell(3).getStringCellValue();
					String endnum = row.getCell(4).getStringCellValue();
					String single = row.getCell(5).getStringCellValue();
					String position = row.getCell(6).getStringCellValue();
					Subarea subarea = new Subarea(subareaId, null, region, addresskey,
							                      startnum, endnum, single, position);
					if(subareaId!=null&&!subareaId.equals("")) {
						subareaList.add(subarea);		
					}								
				}
				subareaService.saveOrUpdate(subareaList);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
			return LIST;
		}
	
	
	/**
	 * 导出区域信息为excel表格
	 */
	public String eportXls() {
		//1、查询所有数据
		List<Subarea> list = subareaService.findAll();
		//创建一个标签页
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建一标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getAddresskey());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		
		//文件下载
		ServletOutputStream out;
		String fileName = "分区数据.xls";
		try {
			String contentType = ServletActionContext.getServletContext().getMimeType(fileName);
			out = ServletActionContext.getResponse().getOutputStream();			
			ServletActionContext.getResponse().setContentType(contentType);
			//获取客户端浏览器类型
			String useragent = ServletActionContext.getRequest().getHeader("User-Agent");
			fileName = FileUtils.encodeDownloadFilename(fileName,useragent );
			ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+fileName);
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return NONE;
	}
	
	/**
	 * 查询所有未关联到分区的定区
	 * @return
	 */
	public String listajax() {
		
		List<Subarea> list = subareaService.findListNotAssociation();
		this.java2Json(list, new String[] {"decidedzone","region"});
		return NONE;		
	}
	
	/**
	 * 根据定区id查询关联的分区
	 * @return
	 */
	
	private String decidedzoneId;
	public String findListByDecidedzoneId() {
		List<Subarea> list = subareaService.findListByDecidedzoneId(decidedzoneId);
		this.java2Json(list, new String[] {"decidedzone","subareas"});
		return NONE;
	}

	public String getDecidedzoneId() {
		return decidedzoneId;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
	
	/**
	 * 页面发送ajax请求，展示highchart
	 * findSubareasGroupByProvince
	 * @return
	 */
	public String showHighchart() {
		
		List<Object> list = subareaService.findSubareasGroupByProvince();
		this.java2Json(list, new String[] {});
		return NONE;	
	}
	
	
	
	

}
