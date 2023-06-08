package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.air.UserReservationDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.ReservationDto;

public class TUser_UsDetail implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AccountDto dto1 = new AccountDto();
		ReservationDto dto2 = new ReservationDto();
		UserReservationDao dao = new UserReservationDao();
		
		int reservation_number = Integer.parseInt(request.getParameter("reservation_number"));
		AccountDto account = (AccountDto) request.getSession().getAttribute("account");
		String user_ID = account.getId();
		
		dto1.setId(user_ID);
		dto2.setReservation_number(reservation_number);
		
		request.setAttribute("list", dao.detail(dto1, dto2));
	}	
}