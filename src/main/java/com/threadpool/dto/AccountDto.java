package com.threadpool.dto;

public class AccountDto {
	
	//table thrdp_account
	private String id;
	private String pass;
	private String name;
	private String birth;
	private String email;
	private String phonenum;
	private String postnum;
	private String address;
	private String detail_address;
	private int role_id;
	private int status_id;
	private String create_date;
	private String create_ip;
	private String pic;

	//table thrdp_role
	private String role_name;
	
	//table user_status
	private String status_name;
	
	//table thrdp_company_info
	private String company_num;
	
	//table out_user
	private String out_reason;
	private String out_date;

	//table thrdp_account_external_auth
	private boolean kakao_auth;
	private String kakao_account;
	private boolean naver_auth;
	private String naver_account;
	
	//////// Constructor
	public AccountDto() { super(); }



	////////Getter , Setter
	public String getId() { return id; } public void setId(String id) { this.id = id; }
	public String getPass() { return pass; } public void setPass(String pass) { this.pass = pass; }
	public String getName() { return name; } public void setName(String name) { this.name = name; }
	public String getBirth() { return birth; } public void setBirth(String birth) { this.birth = birth; }
	public String getPhonenum() { return phonenum; } public void setPhonenum(String phonenum) { this.phonenum = phonenum; }
	public String getPostnum() { return postnum; } public void setPostnum(String postnum) { this.postnum = postnum; }
	public String getDetail_address() { return detail_address; } public void setDetail_address(String detail_address) { this.detail_address = detail_address; }
	public int getRole_id() { return role_id; } public void setRole_id(int role_id) { this.role_id = role_id; }
	public int getStatus_id() { return status_id; } public void setStatus_id(int status_id) { this.status_id= status_id; }
	public String getCreate_date() { return create_date; } public void setCreate_date(String create_date) { this.create_date = create_date; }
	public String getCreate_ip() { return create_ip; } public void setCreate_ip(String create_ip) { this.create_ip = create_ip; }
	public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
	public String getPic() { return pic; } public void setPic(String pic) { this.pic = pic; }
	public String getRole_name() { return role_name; } public void setRole_name(String role_name) { this.role_name = role_name; }
	public String getStatus_name() { return status_name; } public void setStatus_name(String status_name) { this.status_name = status_name; }
	public String getCompany_num() {return company_num; } public void setCompany_num(String company_num) {this.company_num = company_num;}
	public String getAddress() { return address; } public void setAddress(String address) { this.address = address; }
	public String getOut_reason() { return out_reason; } public void setOut_reason(String out_reason) { this.out_reason = out_reason; }
	public String getOut_date() { return out_date; } public void setOut_date(String out_date) { this.out_date = out_date; }
	public boolean isKakao_auth() { return kakao_auth; } public void setKakao_auth(boolean kakao_auth) { this.kakao_auth = kakao_auth; } 
	public String getKakao_account() { return kakao_account; } public void setKakao_account(String kakao_account) { this.kakao_account = kakao_account; } 
	public boolean isNaver_auth() { return naver_auth; } public void setNaver_auth(boolean naver_auth) { this.naver_auth = naver_auth; } 
	public String getNaver_account() { return naver_account; } public void setNaver_account(String naver_account) { this.naver_account = naver_account; }



	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", pass=" + pass + ", name=" + name + ", birth=" + birth + ", email=" + email
				+ ", phonenum=" + phonenum + ", postnum=" + postnum + ", address=" + address + ", detail_address="
				+ detail_address + ", role_id=" + role_id + ", status_id=" + status_id + ", create_date=" + create_date
				+ ", create_ip=" + create_ip + ", pic=" + pic + ", role_name=" + role_name + ", status_name="
				+ status_name + ", company_num=" + company_num + ", out_reason=" + out_reason + ", out_date=" + out_date
				+ ", kakao_auth=" + kakao_auth + ", kakao_account=" + kakao_account + ", naver_auth=" + naver_auth
				+ ", naver_account=" + naver_account + "]";
	} 
	
	
}
