package com.threadpool.dto;

import java.util.List;

public class MessageDto {
	//thrdp_message
	private int msg_no;
	private String msg_title;
	private String msg_context;
	private String msg_sender;
	private String msg_send_time;
	
	//thrdp_recieve
	private int msg_recieve_no;
	private String msg_reciever;
	
	//thrdp_msg_read
	private boolean msg_read;
	private String msg_read_time;
	
	//(no db) 수신자그룹 -- 받을 사용자 그룹.
	private List<Integer> recieveGroup;
	
	////////Constructor
	public MessageDto() {
		super();
	}
	
	////////getter, setter
	public int getMsg_no() { return msg_no; } public void setMsg_no(int msg_no) { this.msg_no = msg_no; } 
	public String getMsg_title() { return msg_title; } public void setMsg_title(String msg_title) { this.msg_title = msg_title; } 
	public String getMsg_context() { return msg_context; } public void setMsg_context(String msg_context) { this.msg_context = msg_context; } 
	public String getMsg_sender() { return msg_sender; } public void setMsg_sender(String msg_sender) { this.msg_sender = msg_sender; } 
	public String getMsg_send_time() { return msg_send_time; } public void setMsg_send_time(String msg_send_time) { this.msg_send_time = msg_send_time; } 
	public int getMsg_recieve_no() { return msg_recieve_no; } public void setMsg_recieve_no(int msg_recieve_no) { this.msg_recieve_no = msg_recieve_no; } 
	public String getMsg_reciever() { return msg_reciever; } public void setMsg_reciever(String msg_reciever) { this.msg_reciever = msg_reciever; } 
	public boolean isMsg_read() { return msg_read; } public void setMsg_read(boolean msg_read) { this.msg_read = msg_read; } 
	public String getMsg_read_time() { return msg_read_time; } public void setMsg_read_time(String msg_read_time) { this.msg_read_time = msg_read_time; }
	public List<Integer> getRecieveGroup() { return recieveGroup; }	public void setRecieveGroup(List<Integer> recieveGroup) { this.recieveGroup = recieveGroup; }

	@Override
	public String toString() {
		return "MessageDto [msg_no=" + msg_no + ", msg_title=" + msg_title + ", msg_context=" + msg_context
				+ ", msg_sender=" + msg_sender + ", msg_send_time=" + msg_send_time + ", msg_recieve_no="
				+ msg_recieve_no + ", msg_reciever=" + msg_reciever + ", msg_read=" + msg_read + ", msg_read_time="
				+ msg_read_time + ", recieveGroup=" + recieveGroup + "]";
	} 

	
	
	
}
