package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.air.*;
import com.threadpool.dao.air.*;

public class TAdmin_ReUpdate implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		AdminReservationDao dao = new AdminReservationDao();
		
		String reservation_name = request.getParameter("reservation_name");
		String telephone_number = request.getParameter("telephone_number");
		String email = request.getParameter("email");
		int reservation_number = Integer.parseInt(request.getParameter("reservation_number"));
		
		int result = dao.update(new ReservationDto(reservation_name, telephone_number, email,reservation_number));
		
		if(result > 0) {
			out.println("<script>alert('예약자 데이터 수정 완료'); location.href='"+request.getContextPath()+"/air_relist.air';</script>");
		} else {
			out.println("<script>alert('예약자 데이터 수정 실패'); history.go(-1);(</script>");
		}
	}
}