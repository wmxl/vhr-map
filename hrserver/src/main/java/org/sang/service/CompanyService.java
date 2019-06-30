package org.sang.service;

import java.util.List;

import org.sang.bean.Company;
import org.sang.bean.Employee;
import org.sang.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService {
	
	@Autowired
	CompanyMapper companyMapper;
	//公司列表
	public List <Company> Companies(){
		return companyMapper.Companies();
	}
	//公司列表
	public List <Company> getCompanyByPage(Integer page, Integer size, String keywords){
		int start = (page - 1) * size;
		return companyMapper.getCompanyByPage(start,size,keywords);
	}
	
	//添加
	public int addCompany(Company company) {
		return companyMapper.addCompany(company);
	}
	//删除
	public boolean deleteEmpById(String ids) {
        String[] split = ids.split(",");
        return companyMapper.deleteCompanyById(split) == split.length;
	}
	//更新
	public int updateCompany(Company company) {
		return companyMapper.updateCompany(company);
	}
	
	public int addCompanies(List<Company> coms) {
		return companyMapper.addCompanies(coms);
	}
	
	public Long getCountByKeywords(String keywords) {
		return companyMapper.getCountByKeywords(keywords);
	}
	
	public List<Company> getCompanyByPageShort(Integer page, Integer size) {
        int start = (page - 1) * size;
        return companyMapper.getCompanyByPageShort(start,size);
    }
}
