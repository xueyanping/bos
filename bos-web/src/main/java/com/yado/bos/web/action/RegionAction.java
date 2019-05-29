package com.yado.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yado.bos.entity.Region;
import com.yado.bos.service.IRegionService;
import com.yado.bos.utils.PinYin4jUtils;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//属性驱动接收上传的文件
	private File regionFile;

	@Autowired
	private IRegionService regionService;
	
	
	
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}


	public void setRegionService(IRegionService regionService) {
		this.regionService = regionService;
	}

	//区域导入
	public String importXls() {
		HSSFWorkbook hsw;
		List<Region> regionList = new ArrayList<>();		
		try {
			hsw = new HSSFWorkbook(new FileInputStream(regionFile));
			HSSFSheet sheet = hsw.getSheet("sheet1");
			for (Row row : sheet) {
				int rowNum = row.getRowNum();
				if(rowNum == 0) {
					continue;
				}
				
				String ZoneId = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postCode = row.getCell(4).getStringCellValue();
				Region region = new Region(ZoneId, province, city, district, postCode, null, null, null);
				
				//获取省市区
				province = province.substring(0, province.length()-1);
				city = city.substring(0, city.length()-1);
				district = district.substring(0, district.length()-1);
				//拼接
				String info = province + city + district;
				//获得省市区首字母大写
				String[] headString = PinYin4jUtils.getHeadByString(info);
				//将数组连接
				String shortcode = StringUtils.join(headString,"");
				//城市汉字转拼音
				String citycode = PinYin4jUtils.hanziToPinyin(city);
				region.setShortcode(shortcode);
				region.setCitycode(citycode);				
				regionList.add(region);				
			}
			regionService.saveOrUpdate(regionList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return LIST;
	}
	
	

	/**
	 * 分页查询地区
	 * @return
	 */
	public String pageQuery() {
	
		regionService.pageQuery(pageBean);
		java2Json(pageBean, new String[] {"currentPage","detachedCriteria","pageSize","subareas"});
		
		return NONE;
	}
	
	
	/**
	 *查询所有区域
	 * @return
	 */
	private String q;
	
	public String getQ() {
		return q;
	}


	public void setQ(String q) {
		this.q = q;
	}


	/**
	 * 查询所有区域，写回json数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String listajax() throws UnsupportedEncodingException {
//		if(q!=null) {
//			q = new String(q.getBytes("ISO8859-1"),"utf-8");
//		}
		
		List<Region> list = null;
		if(StringUtils.isBlank(q)) {
			list = regionService.findAllArea();
		}else {
			list = regionService.findListByQ(q);
		}
		if(list!=null&&list.size()>0) {
			this.java2Json(list, new String[]{"subareas"});
		}
		
		return NONE;
	}
	
	
}
