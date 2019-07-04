package org.sang.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee {
    private Long id;
    private String name;
    private String highSea;
    private String customerId;
    private String province;
    private String city;
    private String county;
    private String industry;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String remark;
    private String businessReceipt;
    private String businessScope;
    private String registerCapital;

    private String corporateNature;

    private double propertyValue;
    private String oldName;
    private String employeeNum;
    private String products;
    private String propertyType;

    private String giov; //工业总产值 gross industrial output value
    private String creator;
    private String creatorAccount;
    private String relevantP;
    private String relevantPAccount;
    private String chargeP;
    private String chargePAccount;

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
    public double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(double propertyValue) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(String registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getGiov() {
        return giov;
    }

    public void setGiov(String giov) {
        this.giov = giov;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorAccount() {
        return creatorAccount;
    }

    public void setCreatorAccount(String creatorAccount) {
        this.creatorAccount = creatorAccount;
    }

    public String getRelevantP() {
        return relevantP;
    }

    public void setRelevantP(String relevantP) {
        this.relevantP = relevantP;
    }

    public String getRelevantPAccount() {
        return relevantPAccount;
    }

    public void setRelevantPAccount(String relevantPAccount) {
        this.relevantPAccount = relevantPAccount;
    }

    public String getChargeP() {
        return chargeP;
    }

    public void setChargeP(String chargeP) {
        this.chargeP = chargeP;
    }

    public String getChargePAccount() {
        return chargePAccount;
    }

    public void setChargePAccount(String chargePAccount) {
        this.chargePAccount = chargePAccount;
    }

    public String getCorporateNature() {
        return corporateNature;
    }

    public void setCorporateNature(String corporateNature) {
        this.corporateNature = corporateNature;
    }

    //toString


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", highSea='" + highSea + '\'' +
                ", customerId='" + customerId + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", industry='" + industry + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", remark='" + remark + '\'' +
                ", businessReceipt='" + businessReceipt + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", registerCapital='" + registerCapital + '\'' +
                ", corporateNature='" + corporateNature + '\'' +
                ", propertyValue=" + propertyValue +
                ", oldName='" + oldName + '\'' +
                ", employeeNum='" + employeeNum + '\'' +
                ", products='" + products + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", giov='" + giov + '\'' +
                ", creator='" + creator + '\'' +
                ", creatorAccount='" + creatorAccount + '\'' +
                ", relevantP='" + relevantP + '\'' +
                ", relevantPAccount='" + relevantPAccount + '\'' +
                ", chargeP='" + chargeP + '\'' +
                ", chargePAccount='" + chargePAccount + '\'' +
                '}';
    }
}