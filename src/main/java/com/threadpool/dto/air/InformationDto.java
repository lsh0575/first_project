package com.threadpool.dto.air;

public class InformationDto {
	private String id; // id (PK)
	private int password; // password
	
	public InformationDto() { super(); }

	/********** 어드민_예약자 데이터 삭제 필드 (set) **********/		
	public InformationDto(String id, int password) { super(); this.id = id; this.password = password; }
	
	public InformationDto(String id) { super(); this.id = id; }
	
	/********** 어드민_항공 데이터 삭제 필드 (set) **********/
	public InformationDto(int password) { super(); this.password = password; }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public int getPassword() { return password; }
	public void setPassword(int password) { this.password = password; }

	@Override public String toString() {
		return "InformationDto [id=" + id + ", password=" + password + "]"; }	
}