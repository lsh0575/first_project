package com.threadpool.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TPagingServiceImpl implements TravelService{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chracter=UTF-8");
		int pstartno = request.getParameter("pstartno") != null?
				Integer.parseInt(request.getParameter("pstartno")):0;
		
		request.setAttribute("travel", new TPaging(pstartno));
	}
}
