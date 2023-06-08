package com.threadpool.dto.hotel;

public class HotelDtoImg {
	private int hno;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String ifix;
	public HotelDtoImg() { super(); }
	public HotelDtoImg(int hno, String img1, String img2, String img3, String img4, String ifix) {
		super();
		this.hno = hno;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.ifix = ifix;
	}
	public HotelDtoImg(String img1) {
		this.img1 = img1;
	}
	@Override
	public String toString() {
		return "HotelDtoImg [hno=" + hno + ", img1=" + img1 + ", img2=" + img2 + ", img3=" + img3 + ", img4=" + img4
				+ ", ifix=" + ifix + "]";
	}
	public int getHno() { return hno; } public void setHno(int hno) { this.hno = hno; }
	public String getImg1() { return img1; } public void setImg1(String img1) { this.img1 = img1; }
	public String getImg2() { return img2; } public void setImg2(String img2) { this.img2 = img2; }
	public String getImg3() { return img3; } public void setImg3(String img3) { this.img3 = img3; }
	public String getImg4() { return img4; } public void setImg4(String img4) { this.img4 = img4; }
	public String getIfix() { return ifix; } public void setIfix(String ifix) { this.ifix = ifix; }
}
