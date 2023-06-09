package com.threadpool.service.hotel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.HotelDao;
import com.threadpool.dto.hotel.HotelDtoProd;


public class HDelete implements HotelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String msg = "관리자에게 문의바랍니다";
		HotelDtoProd dto = new HotelDtoProd();
		HotelDao dao = new HotelDao();
		
		dto.setHno( Integer.parseInt(request.getParameter("hno")));
		
		int result = dao.deleteHotel(dto); 
		if(result > 0) { msg = "글삭제에 성공했습니다."; }
		out.println("<script>alert('"+msg+"');</script>");
	
	}

}
