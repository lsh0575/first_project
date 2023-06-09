package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AdminReservationDao;
import com.threadpool.dto.air.ReservationDto;
public class TAdmin_ReDetail implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AdminReservationDao dao = new AdminReservationDao();
		int reservation_number = Integer.parseInt(request.getParameter("reservation_number"));
				
		request.setAttribute("list", dao.detail(new ReservationDto(reservation_number)));
	}
}