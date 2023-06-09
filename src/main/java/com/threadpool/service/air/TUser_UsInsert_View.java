package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.UserAviationDao;

public class TUser_UsInsert_View implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		UserAviationDao dao = new UserAviationDao();
		
		String where = "";
		String ticket_type = (String) request.getParameter("ticket_type");
		int adults = Integer.parseInt(request.getParameter("adults"));
		int teenagers = Integer.parseInt(request.getParameter("teenagers"));
		String[] flight_codeStrings = request.getParameterValues("flight_code");
		int[] flight_code = new int[flight_codeStrings.length];
		
		
		for (int i = 0; i < flight_codeStrings.length; i++) {
		    flight_code[i] = Integer.parseInt(flight_codeStrings[i]);
		}
		
		if(ticket_type.equals("왕복")) {
			if(flight_code[0] > flight_code[1]) {
				where = "where flight_code in ( "+ flight_code[0] + ", " + flight_code[1] + " )"
						+ " order by flight_code desc";	
			} else {
				where = "where flight_code in ( "+ flight_code[0] + ", " + flight_code[1] + " )"
						+ " order by flight_code asc";					
			}
		} else if (ticket_type.equals("편도")) {
			where = "where flight_code = " + flight_code[0];			
		}
		
		request.setAttribute("list", dao.detail(where));
	}	
}