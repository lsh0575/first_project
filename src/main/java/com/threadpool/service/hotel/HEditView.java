package com.threadpool.service.hotel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.HotelDao;
import com.threadpool.dto.hotel.HotelDtoProd;

public class HEditView implements HotelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HotelDao dao = new HotelDao();
		HotelDtoProd dto = new HotelDtoProd();
		dto.setHno( Integer.parseInt(request.getParameter("hno")));
		request.setAttribute("dto", dao.detailHotel(dto));
	}

}
