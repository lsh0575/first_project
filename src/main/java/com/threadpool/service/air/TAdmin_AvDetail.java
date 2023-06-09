package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AdminAviationDao;
import com.threadpool.dto.air.AviationDto;


public class TAdmin_AvDetail implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AdminAviationDao dao = new AdminAviationDao();
		int flight_code = Integer.parseInt(request.getParameter("flight_code"));
		
		request.setAttribute("dto", dao.detail(new AviationDto(flight_code)));
	}
}