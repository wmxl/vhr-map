package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Company;
import java.util.List;

public interface CompanyMapper {
	
	List <Company> Companies(); 
	
	List<Company> getCompanyByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords);
	
	int addCompany(Company company);
	
	int updateCompany(@Param("com") Company company);
	
	int addCompanies(@Param("coms") List<Company> coms);
	
	int deleteCompanyById(@Param("ids") String[] ids);
	
	Long getCountByKeywords(@Param("keywords") String keywords);
	
	List<Company> getCompanyByPageShort(@Param("start") int start, @Param("size") Integer size);
}
