package com.threadpool.service.travel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.TravelDao;

public class TMain implements TravelService {


	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TList
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		TravelDao dao = new TravelDao();
		request.setAttribute("main", dao.img());
	}
}
