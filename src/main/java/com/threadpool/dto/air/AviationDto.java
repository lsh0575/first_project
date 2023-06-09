package com.threadpool.dto.air;

import java.sql.Timestamp;

import com.threadpool.dto.AccountDto;

public class AviationDto {
	private int flight_code; // 항공코드 (PK)
	private String flight; // 항공편명
	private String airline; // 항공사
	private String classification_of_flights; // 항공편 분류 (국내선/국제선)
	private String departure_time; // 출발시간
	private Timestamp dt; // 출발시간 (gson)
	private String arrival_time; // 도착시간
	private Timestamp ar; // 도착시간 (gson)
	private int number_of_seats; // 좌석 수
	private String seat_class; // 좌석 등급
	private int freight_charge; // 운임료
	private String start_point; // 출발지
	private String end_point; // 도착지
	private String administrator_ID; // 관리자 아이디 (FK)
	private String creation_date; // 생성일자
	private Timestamp cr; // 생성일자 (gson)
	private String ip; // 아이피
	private ReservationDto reDto; // 예약자
	private InformationDto inDto; // 정보
	private AccountDto accDto; // 정보 (도윤님 데이터)
	
	public AviationDto() { super(); }
	
	/********** 어드민_항공 데이터 조회 필드 (get) 및 어드민_항공 데이터 상세 검색 필드 (get) 및 어드민_항공 특정 데이터 조회 필드 (get) **********/
	/********** 유저_항공 데이터 조회 필드 (get) 및 유저_항공 특정 데이터 조회 필드 (get) **********/		
	public AviationDto(int flight_code, String flight, String airline, String classification_of_flights,
			String departure_time, String arrival_time, int number_of_seats, String seat_class, int freight_charge,
			String start_point, String end_point, String administrator_ID, String creation_date, String ip) {
		super(); this.flight_code = flight_code; this.flight = flight; this.airline = airline;
		this.classification_of_flights = classification_of_flights; this.departure_time = departure_time; this.arrival_time = arrival_time;
		this.number_of_seats = number_of_seats; this.seat_class = seat_class; this.freight_charge = freight_charge;
		this.start_point = start_point; this.end_point = end_point; this.administrator_ID = administrator_ID;
		this.creation_date = creation_date; this.ip = ip; }
	
	/********** 어드민_항공 데이터 삽입 필드 (set)  **********/
	public AviationDto(String flight, String airline, String classification_of_flights, String departure_time,
			String arrival_time, int number_of_seats, String seat_class, int freight_charge, String start_point,
			String end_point, String administrator_ID, String ip) {
		super(); this.flight = flight; this.airline = airline;
		this.classification_of_flights = classification_of_flights; this.departure_time = departure_time; this.arrival_time = arrival_time;
		this.number_of_seats = number_of_seats; this.seat_class = seat_class; this.freight_charge = freight_charge;
		this.start_point = start_point; this.end_point = end_point; this.administrator_ID = administrator_ID;
		this.ip = ip; }
	
	/********** 어드민_항공 데이터 상세 검색 필드 (set) **********/
	public AviationDto(String flight, String airline, String classification_of_flights) {
		super(); this.flight = flight; this.airline = airline;
		this.classification_of_flights = classification_of_flights; }
	
	/********** 어드민_항공 특정 데이터 조회 필드 (set) **********/
	/********** 유저_항공 특정 데이터 조회 필드 (set) **********/
	public AviationDto(int flight_code) {
		super(); this.flight_code = flight_code; }

	/********** 어드민_항공 데이터 수정 필드 (set) **********/
	public AviationDto(String airline, String departure_time, String arrival_time, int freight_charge, int flight_code,
			String flight) {
		super(); this.airline = airline; this.departure_time = departure_time; this.arrival_time = arrival_time;
		this.freight_charge = freight_charge; this.flight_code = flight_code; this.flight = flight; }

	/********** 어드민_항공 데이터 삭제 필드 (set) **********/
	public AviationDto(int flight_code, InformationDto inDto) {
		super(); this.flight_code = flight_code; this.inDto = inDto; }
	public AviationDto(int flight_code, AccountDto accDto) {
		super(); this.flight_code = flight_code; this.accDto = accDto; }

	/********** 유저_항공 데이터 조회 필드 (set) **********/
	public AviationDto(ReservationDto reDto, String start_point, String end_point, String seat_class) {
		super(); this.reDto = reDto; this.start_point = start_point;
		this.end_point = end_point; this.seat_class = seat_class; }

	/********** 유저_데이터 검색 필드 **********/
	public AviationDto(String start_point, String end_point) {
		super(); this.start_point = start_point; this.end_point = end_point; }

	/********** 보류 **********/
	public AviationDto(int flight_code, String departure_time, String arrival_time) {
		super(); this.flight_code = flight_code; this.departure_time = departure_time; this.arrival_time = arrival_time; }


	public int getFlight_code() { return flight_code; }
	public void setFlight_code(int flight_code) { this.flight_code = flight_code; }
	public String getFlight() { return flight; }
	public void setFlight(String flight) { this.flight = flight; }
	public String getAirline() { return airline; }
	public void setAirline(String airline) { this.airline = airline; }
	public String getClassification_of_flights() { return classification_of_flights; }
	public void setClassification_of_flights(String classification_of_flights) { this.classification_of_flights = classification_of_flights; }
	public String getDeparture_time() { return departure_time; }
	public void setDeparture_time(String departure_time) { this.departure_time = departure_time; }
	public String getArrival_time() { return arrival_time; }
	public void setArrival_time(String arrival_time) { this.arrival_time = arrival_time; }
	public int getNumber_of_seats() { return number_of_seats; }
	public void setNumber_of_seats(int number_of_seats) { this.number_of_seats = number_of_seats; }
	public String getSeat_class() { return seat_class; }
	public void setSeat_class(String seat_class) { this.seat_class = seat_class; }
	public int getFreight_charge() { return freight_charge; }
	public void setFreight_charge(int freight_charge) { this.freight_charge = freight_charge; }
	public String getStart_point() { return start_point; }
	public void setStart_point(String start_point) { this.start_point = start_point; }
	public String getEnd_point() { return end_point; }
	public void setEnd_point(String end_point) { this.end_point = end_point; }
	public String getAdministrator_ID() { return administrator_ID; }
	public void setAdministrator_ID(String administrator_ID) { this.administrator_ID = administrator_ID; }
	public String getCreation_date() { return creation_date; }
	public void setCreation_date(String creation_date) { this.creation_date = creation_date; }
	public String getIp() { return ip; }
	public void setIp(String ip) { this.ip = ip; }
	public ReservationDto getReDto() { return reDto; }
	public void setReDto(ReservationDto reDto) { this.reDto = reDto; }
	public InformationDto getInDto() { return inDto; }
	public void setInDto(InformationDto inDto) { this.inDto = inDto; }
	public Timestamp getDt() { return dt; }
	public void setDt(Timestamp dt) { this.dt = dt; }
	public Timestamp getAr() { return ar; } 
	public void setAr(Timestamp ar) { this.ar = ar; }
	public Timestamp getCr() { return cr; }
	public void setCr(Timestamp cr) { this.cr = cr; }
	public AccountDto getAccDto() { return accDto; } public void setAccDto(AccountDto accDto) { this.accDto = accDto; } 
	@Override public String toString() {
	    return "{\r\n"
	    		+ " \"flight_code\":\"" + flight_code + "\", \r\n"
	    		+ " \"flight\":\"" + flight + "\", \r\n"
	    		+ " \"airline\":\"" + airline + "\", \r\n"
	    		+ " \"classification_of_flights\":\"" + classification_of_flights + "\", \r\n"
	    		+ " \"departure_time\":\"" + departure_time + "\", \r\n"
	    		+ " \"arrival_time\":\"" + arrival_time + "\", \r\n"
	    		+ " \"number_of_seats\":\"" + number_of_seats + "\", \r\n"
	    		+ " \"seat_class\":\"" + seat_class + "\", \r\n"
	    		+ " \"freight_charge\":\"" + freight_charge + "\", \r\n"
	    		+ " \"start_point\":\"" + start_point + "\", \r\n"
	    		+ " \"end_point\":\"" + end_point + "\", \r\n"
	    		+ " \"administrator_ID\":\"" + administrator_ID + "\", \r\n"
	    		+ " \"creation_date\":\"" + creation_date + "\", \r\n"
	    		+ " \"ip\":\"" + ip + "\", \r\n"
	    		+ "}\r\n";
	}
}