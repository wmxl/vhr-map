package org.sang.bean;

import java.util.List;

public class Company {
	private Long id;
    private String name;
    private String status;
    private String companyId;
    private String province;
    private String city;
    private String area;
    private String industry;
    private String address;
    private String phone;
    private String email;
    private String web;
    private String note;
    private double income;
    private String limit;
    private double registeredCapital;
    private String nature;
    private double totalAsset;
    private String formerName;
    private double numEmployees;
    private String products;
    private String type;
    private double output;
    private String founder;
    private String relevant;
    private String relevantAccount;
    private String founderAccount;
    private String headAccount;
    private String head;
    private String longitude;
    private String latitude;

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public double getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public double getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(double totalAsset) {
		this.totalAsset = totalAsset;
	}
	public String getFormerName() {
		return formerName;
	}
	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}
	
	public double getNumEmployees() {
		return numEmployees;
	}
	public void setNumEmployees(double numEmployees) {
		this.numEmployees = numEmployees;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getOutput() {
		return output;
	}
	public void setOutput(double output) {
		this.output = output;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public String getRelevant() {
		return relevant;
	}
	public void setRelevant(String relevant) {
		this.relevant = relevant;
	}
	public String getRelevantAccount() {
		return relevantAccount;
	}
	public void setRelevantAccount(String relevantAccount) {
		this.relevantAccount = relevantAccount;
	}
	public String getFounderAccount() {
		return founderAccount;
	}
	public void setFounderAccount(String founderAccount) {
		this.founderAccount = founderAccount;
	}
	public String getHeadAccount() {
		return headAccount;
	}
	public void setHeadAccount(String headAccount) {
		this.headAccount = headAccount;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Company{" +

				", name='" + name + '\'' +

				", address='" + address + '\'' +

				", longitude='" + longitude + '\'' +
				", latitude='" + latitude + '\'' +
				'}';
	}
}
