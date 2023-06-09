package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.UserReservationDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.ReservationDto;

public class TUser_UsUpdate implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ReservationDto dto = new ReservationDto();
		UserReservationDao dao = new UserReservationDao();
		
		String reservation_name = request.getParameter("reservation_name");
		String telephone_number = request.getParameter("telephone_number");
		String email = request.getParameter("email");
		AccountDto account = (AccountDto) request.getSession().getAttribute("account");
		String user_ID = account.getId();
		int reservation_number = Integer.parseInt(request.getParameter("reservation_number"));
		
		dto.setReservation_name(reservation_name);
		dto.setTelephone_number(telephone_number);
		dto.setEmail(email);
		dto.setUser_ID(user_ID);
		dto.setReservation_number(reservation_number);
		
		int result = dao.update(dto);
		
		if (result > 0) {
			out.println("<script>alert('예약이 수정 되었습니다.\\n마이 페이지로 이동합니다.'); location.href='" + request.getContextPath()
					+ "/air_mypage.air';</script>");
		} else {
			out.println("<script>alert('관리자 문의 바랍니다.\\n메인 페이지로 이동합니다.'); location.href='" + request.getContextPath()+"';</script>");
		}

	}
}