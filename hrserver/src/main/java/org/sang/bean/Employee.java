package org.sang.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Employee {
    private Long id;
    private String name;
    private String highSea;
    private String province;
    private String city;
    private String county;

    private String website;
    private String remark;
    private String businessReceipt;
    private String businessScope;
    private String industry;

    private String propertyValue;
    private String employeeNum;
    private String products;
    private String propertyType;

    private String nativePlace;
    private String politicName;
    private Long politicId;
    private String email;
    private String phone;
    private String address;
    private Long departmentId;
    private String departmentName;
    private Long jobLevelId;
    private String jobLevelName;
    private Long posId;
    private String posName;
    private String specialty;
    private String school;
    private String workState;
    private String workID;
    private Integer workAge;
    private Department department;
    private JobLevel jobLevel;
    private Position position;
    private PoliticsStatus politicsStatus;
    private Salary salary;

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JobLevel getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(JobLevel jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PoliticsStatus getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(PoliticsStatus politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

//    @JsonIgnore
//    public String getPoliticName() {
//        return politicName;
//    }
//
//    public void setPoliticName(String politicName) {
//        this.politicName = politicName;
//    }

    @JsonIgnore
    public Long getPoliticId() {
        return politicId;
    }

    public void setPoliticId(Long politicId) {
        this.politicId = politicId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @JsonIgnore
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonIgnore
    public Long getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Long jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    @JsonIgnore
    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    @JsonIgnore
    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }


    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }


    public void setHighSea(String highSea) {
        this.highSea = highSea;
    }

    public String getHighSea() {
        return highSea;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusinessReceipt() {
        return businessReceipt;
    }

    public void setBusinessReceipt(String businessReceipt) {
        this.businessReceipt = businessReceipt;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }
    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", highSea='" + highSea + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", website='" + website + '\'' +
                ", remark='" + remark + '\'' +
                ", businessReceipt='" + businessReceipt + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", industry='" + industry + '\'' +
                ", propertyValue='" + propertyValue + '\'' +
                ", employeeNum='" + employeeNum + '\'' +
                ", products='" + products + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", politicName='" + politicName + '\'' +
                ", politicId=" + politicId +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", jobLevelId=" + jobLevelId +
                ", jobLevelName='" + jobLevelName + '\'' +
                ", posId=" + posId +
                ", posName='" + posName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", school='" + school + '\'' +
                ", workState='" + workState + '\'' +
                ", workID='" + workID + '\'' +
                ", workAge=" + workAge +
                ", department=" + department +
                ", jobLevel=" + jobLevel +
                ", position=" + position +
                ", politicsStatus=" + politicsStatus +
                ", salary=" + salary +
                '}';
    }
}