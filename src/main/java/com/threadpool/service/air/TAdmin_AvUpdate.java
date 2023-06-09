package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AdminAviationDao;
import com.threadpool.dto.air.AviationDto;

public class TAdmin_AvUpdate implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		AdminAviationDao dao = new AdminAviationDao();
		String airline = request.getParameter("airline");
		String departure_time = request.getParameter("departure_time");
		String arrival_time = request.getParameter("arrival_time");
		int freight_charge = Integer.parseInt(request.getParameter("freight_charge"));
		int flight_code = Integer.parseInt(request.getParameter("flight_code"));
		String flight = request.getParameter("flight");
		
		int result = dao.update(new AviationDto(
				airline, departure_time, arrival_time, freight_charge, flight_code, flight
				));
		
		if(result > 0) {
			out.println("<script>alert('항공 데이터 수정 완료'); location.href='"+request.getContextPath()+"/air_avlist.air';</script>");
		} else {
			out.println("<script>alert('항공 데이터 수정 실패'); history.go(-1);(</script>");
		}
	}
}