package com.threadpool.dto.hotel;

public class HotelDtoReserve {
	private int hno;
	private String id;
	private String rip;
	private String reserveTime;
	private String hname;
	private String hnation;
	public HotelDtoReserve() {
		super();
	}
	public HotelDtoReserve(int hno, String id, String rip, String reserveTime , String hname , String hnation) {
		super();
		this.hno = hno;
		this.id = id;
		this.rip = rip;
		this.reserveTime = reserveTime;
		this.hname = hname;
		this.hnation = hnation;
	}
	public HotelDtoReserve(int hno, String id, String rip, String reserveTime , String hname) {
		super();
		this.hno = hno;
		this.id = id;
		this.rip = rip;
		this.reserveTime = reserveTime;
		this.hname = hname;
	}
	@Override
	public String toString() {
		return "HotelDtoReserve [hno=" + hno + ", id=" + id + ", rip=" + rip + ", reserveTime=" + reserveTime + "]";
	}
	public int getHno() {
		return hno;
	}
	public void setHno(int hno) {
		this.hno = hno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return rip;
	}
	public void setIp(String rip) {
		this.rip = rip;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getHnation() {
		return hnation;
	}
	public void setHnation(String hnation) {
		this.hnation = hnation;
	}
	
}
