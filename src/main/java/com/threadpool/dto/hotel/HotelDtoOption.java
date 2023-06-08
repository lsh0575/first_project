package com.threadpool.dto.hotel;

public class HotelDtoOption {
	private int hno;
	private boolean smoke;
	private boolean ref;
	private boolean wifi;
	private boolean tv;
	private boolean tub;
	private boolean airc;
	private boolean wash;
	public HotelDtoOption() { super(); }
	public HotelDtoOption(int hno, boolean smoke, boolean ref, boolean wifi, boolean tv, boolean tub, boolean airc,
			boolean wash) {
		super();
		this.hno = hno;
		this.smoke = smoke;
		this.ref = ref;
		this.wifi = wifi;
		this.tv = tv;
		this.tub = tub;
		this.airc = airc;
		this.wash = wash;
	}
	@Override
	public String toString() {
		return "HotelDtoOption [hno=" + hno + ", smoke=" + smoke + ", ref=" + ref + ", wifi=" + wifi + ", tv=" + tv
				+ ", tub=" + tub + ", airc=" + airc + ", wash=" + wash + "]";
	}
	public int getHno() { return hno; } public void setHno(int hno) { this.hno = hno; }
	public boolean isSmoke() { return smoke; } public void setSmoke(boolean smoke) { this.smoke = smoke; }
	public boolean isRef() { return ref; } public void setRef(boolean ref) { this.ref = ref; }
	public boolean isWifi() { return wifi; } public void setWifi(boolean wifi) { this.wifi = wifi; }
	public boolean isTv() { return tv; } public void setTv(boolean tv) { this.tv = tv; }
	public boolean isTub() { return tub; } public void setTub(boolean tub) { this.tub = tub; }
	public boolean isAirc() { return airc; } public void setAirc(boolean airc) { this.airc = airc; }
	public boolean isWash() { return wash; } public void setWash(boolean wash) { this.wash = wash; }
}
