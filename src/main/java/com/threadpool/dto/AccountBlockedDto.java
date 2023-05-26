package com.threadpool.dto;

public class AccountBlockedDto {
	private String id;
	private String out_reason;
	private int status_id;
	////////constructor
	public AccountBlockedDto() { super(); }
	public AccountBlockedDto(String id, String out_reason, int status_id) { //전체 정보
		super();
		this.id = id;
		this.out_reason = out_reason;
		this.status_id = status_id;
	}
	public AccountBlockedDto(String id) { //조회할 때 받을 정보
		super();
		this.id = id;
	}
	
	////////Getter, Setter
	public String getId() { return id; } public void setId(String id) { this.id = id; }
	public String getOut_reason() { return out_reason; } public void setOut_reason(String out_reason) { this.out_reason = out_reason; }
	public int getStatus_id() { return status_id; } public void setStatus_id(int status_id) { this.status_id = status_id; }
	
	@Override
	public String toString() {
		return "AccountBlockedDto [id=" + id + ", out_reason=" + out_reason + "]";
	}
	
	
}
