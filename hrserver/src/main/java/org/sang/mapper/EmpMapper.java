package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Employee;

import java.util.List;

/**
 * Created by sang on 2018/1/12.
 */
public interface EmpMapper {

    int addEmp(Employee employee);

    List<Employee> getEmployeeByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords, @Param("posId") Long posId, @Param("propertyValue")Long propertyValue, @Param("propertyValue2")Long propertyValue2);

    Long getCountByKeywords(@Param("keywords") String keywords, @Param("posId") Long posId, @Param("propertyValue") Long propertyValu, @Param("propertyValue2") Long propertyValue2);

    int updateEmp(@Param("emp") Employee employee);

    int deleteEmpById(@Param("ids") String[] ids);

    int addEmps(@Param("emps") List<Employee> emps);

//    List<Employee> getEmployeeByPageShort(@Param("start") int start, @Param("size") Integer size);
}
