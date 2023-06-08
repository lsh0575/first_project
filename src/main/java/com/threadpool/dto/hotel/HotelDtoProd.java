package com.threadpool.dto.hotel;

public class HotelDtoProd {
	private int hno;
	private String hname;
	private String htype;
	private int hprice;
	private int hgrade;
	private String hbed;
	private int hcnt;
	private String hcontent;
	private String hfix;
	private String hip;
	private String checkin;
	private String checkout;
	private String hnation;
	
	public HotelDtoProd() { }
	
	public HotelDtoProd(int hno, String hname, String htype, int hprice, int hgrade, String hbed, int hcnt, String hcontent,
			String hfix, String hip, String checkin, String checkout, String hnation) {
		super();
		this.hno = hno;
		this.hname = hname;
		this.htype = htype;
		this.hprice = hprice;
		this.hgrade = hgrade;
		this.hbed = hbed;
		this.hcnt = hcnt;
		this.hcontent = hcontent;
		this.hfix = hfix;
		this.hip = hip;
		this.checkin = checkin;
		this.checkout = checkout;
		this.hnation = hnation;
	}

	public HotelDtoProd(int hno, String hname, String htype) {
		this.hno = hno; this.hname = hname; this.htype = htype;
	}

	public HotelDtoProd(int hno) {
		this.hno = hno;
	}

	public int getHno() { return hno; } public void setHno(int hno) { this.hno = hno; }
	public String getHname() { return hname; } public void setHname(String hname) { this.hname = hname; }
	public String getHtype() { return htype; } public void setHtype(String htype) { this.htype = htype; }
	public int getHprice() { return hprice; } public void setHprice(int hprice) { this.hprice = hprice; }
	public int getHgrade() { return hgrade; } public void setHgrade(int hgrade) { this.hgrade = hgrade; }
	public String getHbed() { return hbed; } public void setHbed(String hbed) { this.hbed = hbed; }
	public int getHcnt() { return hcnt; } public void setHcnt(int hcnt) { this.hcnt = hcnt; }
	public String getHcontent() { return hcontent; } public void setHcontent(String hcontent) { this.hcontent = hcontent; }
	public String getHfix() { return hfix; } public void setHfix(String hfix) { this.hfix = hfix; }
	public String getHip() { return hip; } public void setHip(String hip) { this.hip = hip; }
	public String getCheckin() { return checkin; } public void setCheckin(String checkin) { this.checkin = checkin; }
	public String getCheckout() { return checkout; } public void setCheckout(String checkout) { this.checkout = checkout; }
	public String getHnation() { return hnation; } public void setHnation(String hnation) { this.hnation = hnation; }

	@Override
	public String toString() {
		return "HotelDtoProd [hno=" + hno + ", hname=" + hname + ", htype=" + htype + ", hprice=" + hprice + ", hgrade="
				+ hgrade + ", hbed=" + hbed + ", hcnt=" + hcnt + ", hcontent=" + hcontent + ", hfix=" + hfix + ", hip="
				+ hip + ", checkin=" + checkin + ", checkout=" + checkout + ", hnation=" + hnation + "]";
	}
}
