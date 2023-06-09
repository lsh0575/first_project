package com.threadpool.dto.hotel;

public class HotelReserveList {
	private int hno;
	private String name;
	private String id;
	public HotelReserveList() {
	}
	public HotelReserveList(int hno, String name, String id) {
		this.hno = hno; this.name = name; this.id = id;
	}
	@Override
	public String toString() {
		return "HotelReserveList [hno=" + hno + ", name=" + name + ", id=" + id + "]";
	}
	public int getHno() {
		return hno;
	}
	public void setHno(int hno) {
		this.hno = hno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
