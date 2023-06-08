package com.threadpool.dto.air;

public class PassengerDto {
	private int passenger_number; // 승객 코드 (PK)
	private String passenger_korean_name; // 승객 한국 이름
	private String passenger_english_name; // 승객 영어 이름
	private String gender; // 성별
	private String birth_date; // 생년월일
	private int reservation_number; // 예약번호 (FK)

	public PassengerDto() { super(); }
		
	public PassengerDto(int passenger_number, String passenger_korean_name, String passenger_english_name,
			String gender, String birth_date, int reservation_number) {
		super(); this.passenger_number = passenger_number; this.passenger_korean_name = passenger_korean_name; this.passenger_english_name = passenger_english_name;
		this.gender = gender; this.birth_date = birth_date; this.reservation_number = reservation_number; }

	/********** 어드민_탑승객 특정 데이터 조회 필드 (get) **********/
	public PassengerDto(int passenger_number, String passenger_korean_name, String passenger_english_name,
			String gender, String birth_date) {
		super(); this.passenger_number = passenger_number; this.passenger_korean_name = passenger_korean_name;
		this.passenger_english_name = passenger_english_name; this.gender = gender; this.birth_date = birth_date; }
	
	/********** 관리자 - 데이터 수정 필드 **********/
	public PassengerDto(String passenger_korean_name, String passenger_english_name, String gender, String birth_date,
			int passenger_number) {
		super(); this.passenger_korean_name = passenger_korean_name; this.passenger_english_name = passenger_english_name;
		this.gender = gender; this.birth_date = birth_date; this.passenger_number = passenger_number; }


	public int getPassenger_number() { return passenger_number; }
	public void setPassenger_number(int passenger_number) { this.passenger_number = passenger_number; }
	public String getPassenger_korean_name() { return passenger_korean_name; }
	public void setPassenger_korean_name(String passenger_korean_name) { this.passenger_korean_name = passenger_korean_name; }
	public String getPassenger_english_name() { return passenger_english_name; }
	public void setPassenger_english_name(String passenger_english_name) { this.passenger_english_name = passenger_english_name; }
	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }
	public String getBirth_date() { return birth_date; }
	public void setBirth_date(String birth_date) { this.birth_date = birth_date; }
	public int getReservation_number() { return reservation_number; }
	public void setReservation_number(int reservation_number) { this.reservation_number = reservation_number; }
	
	@Override public String toString() {
		return "PassengerDto [passenger_number=" + passenger_number + ", passenger_korean_name=" + passenger_korean_name
				+ ", passenger_english_name=" + passenger_english_name + ", gender=" + gender + ", birth_date="
				+ birth_date + ", reservation_number=" + reservation_number + "]"; }	
}