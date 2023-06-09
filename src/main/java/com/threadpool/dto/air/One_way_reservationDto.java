package com.threadpool.dto.air;

public class One_way_reservationDto {
	private int reservation_number; // 예약번호 (FK)
	private int flight_code; // 항공코드 (FK)

	public One_way_reservationDto() { super(); }
	
	public One_way_reservationDto(int reservation_number, int flight_code) {
		super(); this.reservation_number = reservation_number; this.flight_code = flight_code; }

	public int getReservation_number() { return reservation_number; }
	public void setReservation_number(int reservation_number) { this.reservation_number = reservation_number; }
	public int getFlight_code() { return flight_code; }
	public void setFlight_code(int flight_code) { this.flight_code = flight_code; }
	
	@Override public String toString() {
		return "One_way_reservationDto [reservation_number=" + reservation_number + ", flight_code=" + flight_code + "]"; }	
}
