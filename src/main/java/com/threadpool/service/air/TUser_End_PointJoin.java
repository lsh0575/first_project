package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.threadpool.dao.air.UserAviationDao;
import com.threadpool.dto.air.AviationDto;


public class TUser_End_PointJoin implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserAviationDao dao = new UserAviationDao();
		AviationDto dto = new AviationDto();
		
		String end_point = request.getParameter("end_point");
		dto.setEnd_point(end_point);

		out.println(new Gson().toJson(dao.end_pointjoin(dto)));
	}
}