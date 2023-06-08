package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.air.reListPage;

public class TAdmin_ReList implements TAction {
	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int pageStartNum = 0;
		String where="";
		String reservation_name = request.getParameter("reservation_name") != null ? request.getParameter("reservation_name") : "";
		int reservation_number = request.getParameter("reservation_number") != null ? Integer.parseInt(request.getParameter("reservation_number")) : 0;
		String ticket_type = request.getParameter("ticket_type") != null ? request.getParameter("ticket_type") : "";
		String start_date = request.getParameter("start_date") != null ? request.getParameter("start_date") : "";
		String end_date = request.getParameter("end_date") != null ? request.getParameter("end_date") + " 23:59:59" : "";
				
		if (request.getParameter("pageStartNum") != null) {
			pageStartNum = Integer.parseInt(request.getParameter("pageStartNum"));
		}
		if (request.getParameter("where")!=null) {
			where = "where reservation_name in ('" + reservation_name + "') and "
				    + "reservation_number in ('" + reservation_number + "') and ticket_type"
				    + " in ('" + ticket_type + "') and "
				    + "reservation_date between '" + start_date + "' and '" + end_date + "'";
		}
		request.setAttribute("page_count", new reListPage(pageStartNum, where));
	}
}