package com.threadpool.service.hotel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.HotelDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.hotel.HotelDtoReserve;

public class HReserve_user implements HotelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HotelDao dao = new HotelDao();
		HotelDtoReserve dto = new HotelDtoReserve();
		AccountDto user = (AccountDto) request.getSession().getAttribute("account");
		dto.setHno(Integer.parseInt(request.getParameter("hno")));
		dto.setId(user.getId());
		
		String msg = "관리자에게 문의바랍니다.";

		int result1 = dao.createReserve(dto);
		
		if(result1 > 0) {
			msg = "예약 되었습니다.!";
		}
		out.println("<script>alert('"+msg+"');</script>");
	}
}