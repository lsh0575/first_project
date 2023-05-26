package com.threadpool.dto;

public class AccountBusinessDto {
	private String id;
	private String company_num;
	
	//////// Constructor
	public AccountBusinessDto() {
		super();
	}
	
	public AccountBusinessDto(String id, String company_num) {
		super();
		this.id = id;
		this.company_num = company_num;
	}
	//////// Getter, setter
	public String getId() { return id; } public void setId(String id) { this.id = id; }
	public String getCompany_num() { return company_num; } public void setCompany_num(String company_num) { this.company_num = company_num; }

	
	
	@Override
	public String toString() {
		return "AccountBusinessDto [id=" + id + ", company_num=" + company_num + "]";
	}
	
	
	
	
}
