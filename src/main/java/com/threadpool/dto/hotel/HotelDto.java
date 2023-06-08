package com.threadpool.dto.hotel;

import com.threadpool.dto.AccountDto;

public class HotelDto {
	private HotelDtoProd hprod;
	private HotelDtoOption hoption;
	private HotelDtoImg himg;
	private HotelDtoReserve reserve;
	private AccountDto account;
	public HotelDto() { super(); }
	public HotelDto(HotelDtoProd hprod, HotelDtoOption hoption, HotelDtoImg himg) {
		this.hprod = hprod; this.hoption = hoption; this.himg = himg;
	}
	
	public HotelDto(HotelDtoProd hprod, HotelDtoOption hoption, HotelDtoImg himg, HotelDtoReserve reserve,
			AccountDto account) {
		this.hprod = hprod;
		this.hoption = hoption;
		this.himg = himg;
		this.reserve = reserve;
		this.account = account;
	}
	public HotelDto(HotelDtoProd prod, HotelDtoImg img) {
		this.hprod = prod; this.himg = img;
	}
	@Override
	public String toString() {
		return "HotelDto [hprod=" + hprod + ", hoption=" + hoption + ", himg=" + himg + ", reserve=" + reserve
				+ ", account=" + account + "]";
	}
	public HotelDtoProd getHprod() {
		return hprod;
	}
	public void setHprod(HotelDtoProd hprod) {
		this.hprod = hprod;
	}
	public HotelDtoOption getHoption() {
		return hoption;
	}
	public void setHoption(HotelDtoOption hoption) {
		this.hoption = hoption;
	}
	public HotelDtoImg getHimg() {
		return himg;
	}
	public void setHimg(HotelDtoImg himg) {
		this.himg = himg;
	}
	public HotelDtoReserve getReserve() {
		return reserve;
	}
	public void setReserve(HotelDtoReserve reserve) {
		this.reserve = reserve;
	}
	public AccountDto getAccount() {
		return account;
	}
	public void setAccount(AccountDto account) {
		this.account = account;
	}
}