package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AdminAviationDao;
import com.threadpool.dto.air.AviationDto;


public class TAdmin_AvInsert implements TAction {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		AdminAviationDao dao = new AdminAviationDao();

		String flight = request.getParameter("flight");
		String airline = request.getParameter("airline");
		String classification_of_flights = request.getParameter("classification_of_flights");
		String departure_time = request.getParameter("departure_time");
		String arrival_time = request.getParameter("arrival_time");
		int number_of_seats = Integer.parseInt(request.getParameter("number_of_seats"));
		String seat_class = request.getParameter("seat_class");
		int freight_charge = Integer.parseInt(request.getParameter("freight_charge"));
		String start_point = request.getParameter("start_point");
		String end_point = request.getParameter("end_point");
		String administrator_ID = "admin";
		String ip = InetAddress.getLocalHost().getHostAddress();

		int result = dao
				.insert(new AviationDto(flight, airline, classification_of_flights, departure_time, arrival_time,
						number_of_seats, seat_class, freight_charge, start_point, end_point, administrator_ID, ip));
		
		if(result > 0) {
			out.println("<script>alert('항공 데이터 등록 완료'); location.href='"+request.getContextPath()+"/air_avlist.air';</script>");
		} else {
			out.println("<script>alert('항공 데이터 등록 실패'); history.go(-1);(</script>");
		}
	}
}