package org.sang.controller;

import org.sang.bean.Company;
import org.sang.bean.RespBean;
import org.sang.common.LuenceUtils;
import org.sang.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;



	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public void searchResults(@RequestParam(defaultValue = "汽车") String keyword) throws Exception {
		System.err.println("enter GET: search");
		System.out.println(keyword);
		LuenceUtils.TermQueryStrings(keyword);


	}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> companies(){
		Map<String, Object> map = new HashMap<>();
		map.put("com", companyService.Companies());
		return map;
	}
	
	@RequestMapping(value = "/com", method = RequestMethod.GET)
	public Map<String, Object> getCompanyByPage(@RequestParam(defaultValue = "1") Integer page,
	        @RequestParam(defaultValue = "10") Integer size,
	        @RequestParam(defaultValue = "") String keywords){
		Map<String, Object> map = new HashMap<>();
		Long count = companyService.getCountByKeywords(keywords);
		map.put("com", companyService.getCompanyByPage(page,size,keywords));
		map.put("count", count);
		return map;
	}
	
	@RequestMapping(value = "/addcom", method = RequestMethod.POST)
	public RespBean addCompany(Company company) {
		if(companyService.addCompany(company)==1) {
			return RespBean.ok("添加成功!");
		}
		return RespBean.error("添加失败!");
	}
	
	@RequestMapping(value = "/com/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteCompanyById(@PathVariable String ids) {
		if(companyService.deleteEmpById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
	}
	
	@RequestMapping(value = "/com", method = RequestMethod.PUT)
    public RespBean updateCompany(Company company) {
		if(companyService.updateCompany(company)==1) {
			return RespBean.ok("添加成功!");
		}
		return RespBean.error("添加失败!");
	}
//	//导入
//	@RequestMapping(value = "/importCompany", method = RequestMethod.POST)
//    public RespBean importCompany(MultipartFile file) {
//		List<Company> company = PoiUtils.importCom2List(file);
//		if(companyService.addCompanies(company)==company.size()) {
//			return RespBean.ok("添加成功!");
//		}
//		return RespBean.error("添加失败!");
//	}
//	 @RequestMapping(value = "/exportEmp", method = RequestMethod.GET)
//	    public ResponseEntity<byte[]> exportEmp() {
//	        return PoiUtils.exportCom2Excel(companyService.Companies());
//	    }
}
