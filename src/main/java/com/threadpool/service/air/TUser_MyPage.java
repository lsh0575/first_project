package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.air.UserReservationDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.ReservationDto;

public class TUser_MyPage implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		UserReservationDao dao = new UserReservationDao();
		ReservationDto dto = new ReservationDto();
		
		AccountDto account = (AccountDto) request.getSession().getAttribute("account");
		dto.setUser_ID(account.getId());

		
		request.setAttribute("list", dao.read(dto));
	}
}