package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.air.avListPage;

public class TAdmin_AvList implements TAction {
	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int pageStartNum = 0;
		String where="";
		String flight = request.getParameter("flight") != null ? request.getParameter("flight") : "";
		String airline = request.getParameter("airline") !=null ? request.getParameter("airline") : "";
		String classification_of_flights = request.getParameter("classification_of_flights") != null ? request.getParameter("classification_of_flights") : "";
		String start_date = request.getParameter("start_date") != null ?  request.getParameter("start_date") : "";
		String end_date = request.getParameter("end_date") != null ? request.getParameter("end_date") + " 23:59:59" : "";
		

		if (request.getParameter("pageStartNum") != null) {
			pageStartNum = Integer.parseInt(request.getParameter("pageStartNum"));
		}
		if (request.getParameter("where")!=null) {
			where = "where flight in ('" + flight + "') and "
				    + "airline in ('" + airline + "') and classification_of_flights"
				    + " in ('" + classification_of_flights + "') and "
				    + "departure_time between '" + start_date + "' and '" + end_date + "'";
		}
		
		System.out.println(flight);
		System.out.println(airline);
		System.out.println(classification_of_flights);
		System.out.println(start_date);
		System.out.println(end_date);
		System.out.println(pageStartNum);
		System.out.println(where);
		
		request.setAttribute("page_count", new avListPage(pageStartNum, where));
	}
}