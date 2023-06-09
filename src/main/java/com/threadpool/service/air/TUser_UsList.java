package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.UserAviationDao;

public class TUser_UsList implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		UserAviationDao dao = new UserAviationDao();
		
		// page
		String where = "";
		String ticket_type = request.getParameter("ticket_type") != null ? request.getParameter("ticket_type") : ""; // 티켓종류
		String start_point = request.getParameter("start_point") != null ? request.getParameter("start_point") : ""; // 출발지
		String end_point = request.getParameter("end_point") != null ? request.getParameter("end_point") : ""; // 도착지
		String start_date = request.getParameter("start_date") != null ? request.getParameter("start_date") : ""; // 출국일
		String str_date = start_date + " 23:59:59"; // 출국일 조건
		String end_date = request.getParameter("end_date") != null ? request.getParameter("end_date") : ""; // 귀국일
		String ed_date = end_date + " 23:59:59"; // 귀국일 조건
		int adults = request.getParameter("adults") != null ? Integer.parseInt(request.getParameter("adults")): 0; // 성인수
		int teenagers = request.getParameter("teenagers") != null ? Integer.parseInt(request.getParameter("teenagers")):0; // 청소년수
		
		// group
		String group = "group by aviation.flight_code "
				+ "having aviation.number_of_seats > count(passenger.passenger_number) + " + (adults +teenagers); 
		String order = " order by freight_charge asc ";

        if (ticket_type.equals("왕복")) { // 왕복
            where += "where (aviation.departure_time >= '" + start_date + "' and aviation.departure_time <= '" + str_date + "') or " // 출국일 날짜
                    + "(aviation.departure_time >= '" + end_date + "' and aviation.departure_time <= '" + ed_date + "') and " // 귀국일 날짜
                    + "(aviation.start_point = '" + start_point + "' and aviation.end_point = '" + end_point + "' or " // 출발지 -> 도착지
                    + "aviation.start_point = '" + end_point + "' and aviation.end_point = '" + start_point + "') "
                    		+ group + order;
            } else { // 편도
            where += "where (aviation.departure_time >= '" + start_date + "' and aviation.departure_time <= '" + str_date + "') and " // 출국일 날짜
                    + "(aviation.start_point = '" + start_point + "' and aviation.end_point = '" + end_point + "' ) "
                    		+ group + order;
        }
        
		request.setAttribute("ticket_type", ticket_type);
		request.setAttribute("start_point", start_point);
		request.setAttribute("end_point", end_point);
		request.setAttribute("start_date", start_date);
		request.setAttribute("str_date", str_date);
		request.setAttribute("end_date", end_date);
		request.setAttribute("ed_date", ed_date);
		request.setAttribute("adults", adults);
		request.setAttribute("teenagers", teenagers);
        request.setAttribute("list", dao.read(where));
	}
}