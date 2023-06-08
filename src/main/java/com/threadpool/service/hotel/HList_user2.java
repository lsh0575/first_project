package com.threadpool.service.hotel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.hotel.HotelPaging_user2;

public class HList_user2 implements HotelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int pstartno = request.getParameter("pstartno") != null? Integer.parseInt(request.getParameter("pstartno")) : 0;
		
		request.setAttribute("paging", new HotelPaging_user2(pstartno));
	}
}
