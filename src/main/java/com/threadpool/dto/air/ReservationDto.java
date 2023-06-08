package com.threadpool.dto.air;


public class ReservationDto {
	private int reservation_number; // 예약번호 (PK)
	private String user_ID; // 유저아이디 (FK)
	private String reservation_name; // 예약자명
	private String telephone_number; // 휴대폰 번호
	private String email; // 이메일
	private int adults; // 성인 수
	private int teenagers; // 청소년 수
	private String ticket_type; // 구분 (왕복/편도)
	private int total_payment; // 총 금액
	private String reservation_date; // 예약일자
	private String ip; // 아이피
	private PassengerDto passDto; // 승객
	private AviationDto aviDto; // 항공
	private InformationDto inDto; // 유저
	
	
	public ReservationDto() { super(); }

	/********** 어드민_예약자 데이터 조회 필드 (get) 및 어드민_예약자 데이터 상세 검색 필드 (get) **********/
	public ReservationDto(int reservation_number, String user_ID, String reservation_name, String telephone_number,
			String email, int adults, int teenagers, String ticket_type, int total_payment, String reservation_date,
			String ip) {
		super(); this.reservation_number = reservation_number; this.user_ID = user_ID;
		this.reservation_name = reservation_name; this.telephone_number = telephone_number; this.email = email;
		this.adults = adults; this.teenagers = teenagers; this.ticket_type = ticket_type;
		this.total_payment = total_payment; this.reservation_date = reservation_date; this.ip = ip;
	}
	
	/********** 어드민_예약자 데이터 상세 검색 필드 (set) **********/
	public ReservationDto(String reservation_name, int reservation_number, String ticket_type) {
		super(); this.reservation_name = reservation_name; this.reservation_number = reservation_number;
		this.ticket_type = ticket_type; }

	/********** 어드민_예약자 특정 데이터 조회 필드 (set) **********/
	public ReservationDto(int reservation_number) {
		super(); this.reservation_number = reservation_number; }
	
	/********** 어드민_예약자 특정 데이터 조회 필드 (get) **********/
	public ReservationDto(int reservation_number, String user_ID, String reservation_name, String telephone_number,
			String email, int adults, int teenagers, String ticket_type, int total_payment, String reservation_date,
			String ip, PassengerDto passDto) {
		super(); this.reservation_number = reservation_number; this.user_ID = user_ID;
		this.reservation_name = reservation_name; this.telephone_number = telephone_number; this.email = email;
		this.adults = adults; this.teenagers = teenagers; this.ticket_type = ticket_type;
		this.total_payment = total_payment; this.reservation_date = reservation_date; this.ip = ip;
		this.passDto = passDto; }

	/********** 어드민_예약지 데이터 수정 필드 (set) **********/	
	public ReservationDto(String reservation_name, String telephone_number, String email, int reservation_number) {
		super(); this.reservation_name = reservation_name; this.telephone_number = telephone_number;
		this.email = email; this.reservation_number = reservation_number; }
	
	/********** 어드민_예약자 데이터 삭제 필드 (set) **********/	
	public ReservationDto(int reservation_number, InformationDto inDto) {
		super(); this.reservation_number = reservation_number; this.inDto = inDto; }	

	/********** 유저_항공 데이터 조회 필드 (set) **********/	
	public ReservationDto(int adults, int teenagers) {
		super(); this.adults = adults; this.teenagers = teenagers; }

	/********** 유저_예약자 데이터 삽입 필드 (set) **********/	
	public ReservationDto(String user_ID, String reservation_name, String telephone_number, String email, int adults,
			int teenagers, String ticket_type, int total_payment, String ip) {
		super(); this.user_ID = user_ID; this.reservation_name = reservation_name;
		this.telephone_number = telephone_number; this.email = email; this.adults = adults;
		this.teenagers = teenagers; this.ticket_type = ticket_type; this.total_payment = total_payment;
		this.ip = ip; }
	
	public ReservationDto(int reservation_number, String user_ID, String reservation_name, String telephone_number,
			String email, int adults, int teenagers, String ticket_type, int total_payment, String reservation_date,
			String ip, PassengerDto passDto, AviationDto aviDto) {
		super(); this.reservation_number = reservation_number; this.user_ID = user_ID;
		this.reservation_name = reservation_name; this.telephone_number = telephone_number; this.email = email;
		this.adults = adults; this.teenagers = teenagers; this.ticket_type = ticket_type;
		this.total_payment = total_payment; this.reservation_date = reservation_date; this.ip = ip;
		this.passDto = passDto; this.aviDto = aviDto; }
	

	/********** 보류 **********/
	public ReservationDto(int reservation_number, int adults, int teenagers, int total_payment, String reservation_date,
			AviationDto aviDto) {
		super(); this.reservation_number = reservation_number; this.adults = adults;
		this.teenagers = teenagers; this.total_payment = total_payment; this.reservation_date = reservation_date;
		this.aviDto = aviDto; }

	/********** 보류 **********/	
	public ReservationDto(String reservation_name, String telephone_number, String email, PassengerDto passDto) {
		super(); this.reservation_name = reservation_name; this.telephone_number = telephone_number;
		this.email = email; this.passDto = passDto; }

	/********** 보류 **********/	
	public ReservationDto(int reservation_number, int adults, int teenagers, String reservation_date, int total_payment,
			AviationDto aviDto) {
		super(); this.reservation_number = reservation_number; this.adults = adults; this.teenagers = teenagers;
		this.reservation_date = reservation_date; this.total_payment = total_payment; this.aviDto = aviDto; }

	
	
	public ReservationDto(int reservation_number, String user_ID, String reservation_name, String telephone_number,
			String email, int adults, int teenagers, String ticket_type, int total_payment, String reservation_date,
			String ip, AviationDto aviDto) {
		super();
		this.reservation_number = reservation_number;
		this.user_ID = user_ID;
		this.reservation_name = reservation_name;
		this.telephone_number = telephone_number;
		this.email = email;
		this.adults = adults;
		this.teenagers = teenagers;
		this.ticket_type = ticket_type;
		this.total_payment = total_payment;
		this.reservation_date = reservation_date;
		this.ip = ip;
		this.aviDto = aviDto;
	}

	public int getReservation_number() { return reservation_number; }
	public void setReservation_number(int reservation_number) { this.reservation_number = reservation_number; }
	public String getUser_ID() { return user_ID; }
	public void setUser_ID(String user_ID) { this.user_ID = user_ID; }
	public String getReservation_name() { return reservation_name; }
	public void setReservation_name(String reservation_name) { this.reservation_name = reservation_name; }
	public String getTelephone_number() { return telephone_number; }
	public void setTelephone_number(String telephone_number) { this.telephone_number = telephone_number; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public int getAdults() { return adults; }
	public void setAdults(int adults) { this.adults = adults; }
	public int getTeenagers() { return teenagers; }
	public void setTeenagers(int teenagers) { this.teenagers = teenagers; }
	public String getTicket_type() { return ticket_type; }
	public void setTicket_type(String ticket_type) { this.ticket_type = ticket_type; }
	public int getTotal_payment() { return total_payment; }
	public void setTotal_payment(int total_payment) { this.total_payment = total_payment; }
	public String getReservation_date() { return reservation_date; }
	public void setReservation_date(String reservation_date) { this.reservation_date = reservation_date; }
	public String getIp() { return ip; }
	public void setIp(String ip) { this.ip = ip; }
	public PassengerDto getPassDto() { return passDto; }
	public void setPassDto(PassengerDto passDto) { this.passDto = passDto; }
	public AviationDto getAviDto() { return aviDto; }
	public void setAviDto(AviationDto aviDto) { this.aviDto = aviDto; }
	public InformationDto getInDto() { return inDto; }
	public void setInDto(InformationDto inDto) { this.inDto = inDto; }

	@Override public String toString() {
		return "ReservationDto [reservation_number=" + reservation_number + ", user_ID=" + user_ID
				+ ", reservation_name=" + reservation_name + ", telephone_number=" + telephone_number + ", email="
				+ email + ", adults=" + adults + ", teenagers=" + teenagers + ", ticket_type=" + ticket_type
				+ ", total_payment=" + total_payment + ", reservation_date=" + reservation_date + ", ip=" + ip
				+ ", passDto=" + passDto + ", aviDto=" + aviDto + ", inDto=" + inDto + "]"; }
}