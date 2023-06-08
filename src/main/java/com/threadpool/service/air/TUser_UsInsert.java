package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.air.One_way_reservationDao;
import com.threadpool.dao.air.PassengerDao;
import com.threadpool.dao.air.UserReservationDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.PassengerDto;
import com.threadpool.dto.air.ReservationDto;

public class TUser_UsInsert implements TAction {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		AccountDto account = (AccountDto) request.getSession().getAttribute("account");
		ReservationDto dto2 = new ReservationDto();
		PassengerDto dto3 = new PassengerDto();

		String values = "";
		String[] flight_codeStrings = request.getParameterValues("flight_code");
		int[] flight_code = new int[flight_codeStrings.length];
		for (int i = 0; i < flight_codeStrings.length; i++) {
			flight_code[i] = Integer.parseInt(flight_codeStrings[i]);
		}

		String user_ID = account.getId();
		String reservation_name = request.getParameter("reservation_name");
		String telephone_number = request.getParameter("telephone_number");
		String email = request.getParameter("email");
		int adults = Integer.parseInt(request.getParameter("adults"));
		int teenagers = Integer.parseInt(request.getParameter("teenagers"));
		String ticket_type = request.getParameter("ticket_type");
		int total = Integer.parseInt(request.getParameter("total"));
		String ip = InetAddress.getLocalHost().getHostAddress();

		dto2.setUser_ID(user_ID);
		dto2.setReservation_name(reservation_name);
		dto2.setTelephone_number(telephone_number);
		dto2.setEmail(email);
		dto2.setAdults(adults);
		dto2.setTeenagers(teenagers);
		dto2.setTicket_type(ticket_type);
		dto2.setTotal_payment(total);
		dto2.setIp(ip);
		int primarykey = new UserReservationDao().insert(dto2);

		if (ticket_type.equals("왕복")) {
			values = "values ( " + primarykey + ", " + flight_code[0] + " ), ( " + primarykey + ", " + flight_code[1]
					+ " )";
		} else if (ticket_type.equals("편도")) {
			values = "values ( " + primarykey + ", " + flight_code[0] + ")";
		}

		int one = new One_way_reservationDao().insert(values);

		String[] passenger_korean_name = request.getParameterValues("passenger_korean_name");
		String[] passenger_english_name = request.getParameterValues("passenger_english_name");
		String[] birth_date = request.getParameterValues("birth_date");
		int size = birth_date.length;

		int result = 0;
		for (int i = 0; i < size; i++) {
			dto3.setPassenger_korean_name(passenger_korean_name[i]);
			dto3.setPassenger_english_name(passenger_english_name[i]);
			dto3.setGender(request.getParameter("gender" + (i + 1)));
			dto3.setBirth_date(birth_date[i]);
			dto3.setReservation_number(primarykey);
			result += new PassengerDao().insert(dto3);
		}
		if (result > 0) {
			out.println("<script>alert('예약이 등록 되었습니다.\\n마이 페이지로 이동합니다.'); location.href='" + request.getContextPath()
					+ "/air_mypage.air';</script>");
		} else {
			out.println("<script>alert('관리자 문의 바랍니다.\\n메인 페이지로 이동합니다.'); location.href='" + request.getContextPath()+"';</script>");
		}
	}
}