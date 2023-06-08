package com.threadpool.service.air;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.threadpool.dao.UserAviationDao;
import com.threadpool.dto.AccountDto;

public class TUser_UsListAjax implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String where = "";
		String ticket_type = (String) request.getParameter("ticket_type");
		String start_point = (String) request.getParameter("start_point");
		String end_point = (String) request.getParameter("end_point");
		String start_date = (String) request.getParameter("start_date");
		String str_date = "";
		String end_date = (String) request.getParameter("end_date");
		String ed_date = "";
		int adults = Integer.parseInt( request.getParameter("adults"));
		int teenagers = Integer.parseInt(request.getParameter("teenagers"));		

		// ajax - 검색
		String price = request.getParameter("price") != null ? request.getParameter("price") : "";
		String by_price = request.getParameter("by_price") != null ? request.getParameter("by_price") : "";
		String departure = request.getParameter("departure") != null ? request.getParameter("departure") : "";
		String coming = request.getParameter("coming") != null ? request.getParameter("coming") : "";		
		// group
		String group = "group by aviation.flight_code "
				+ "having aviation.number_of_seats > count(passenger.passenger_number) + " + (adults +teenagers); 
		String order = " order by freight_charge desc ";
		
		// ajax - 가격순 (최저가/최고가순)
        if(price.equals("low")) {
    		order = " order by freight_charge desc ";
        } else if (price.equals("high")) {
        	order = " order by freight_charge asc ";
        }
        
	
        // ajax - 출국일
        if (departure.equals("departure_all")) {
    		str_date = start_date + " 23:59:59"; // 출국일 조건
        } else if (departure.equals("departure_6")) {
    		str_date = start_date + " 06:00:00"; // 출국일 조건        	
        } else if (departure.equals("departure_12")) {
        	str_date = start_date + " 12:00:00"; // 출국일 조건        	
        	start_date += " 06:00:00";
        } else if (departure.equals("departure_18")) {
        	str_date = start_date + " 18:00:00"; // 출국일 조건        	
        	start_date += " 12:00:00";
        } else if (departure.equals("departure_24")) {
        	str_date = start_date + " 23:59:59"; // 출국일 조건        	
        	start_date += " 18:00:00";
        }

        // ajax - 귀국일
        if (coming.equals("coming_all")) {
    		ed_date = end_date + " 23:59:59"; // 귀국일 조건
        } else if (coming.equals("coming_6")) {
        	ed_date = end_date + " 06:00:00"; // 귀국일 조건        	
        } else if (coming.equals("coming_12")) {
        	ed_date = end_date + " 12:00:00"; // 귀국일 조건        	
        	end_date += " 06:00:00";
        } else if (coming.equals("coming_18")) {
        	ed_date = end_date + " 18:00:00"; // 귀국일 조건        	
        	end_date += " 12:00:00";
        } else if (coming.equals("coming_24")) {
        	ed_date = end_date + " 23:59:59"; // 귀국일 조건        	
        	end_date += " 18:00:00";
        }
        
        String and = "";

        if (by_price.equals("by_price_all")) {
        	and = "";
        } else if (by_price.equals("30")) {
        	and = "and aviation.freight_charge<300000 ";
        } else if (by_price.equals("60")) {
        	and = "and aviation.freight_charge>=300000 and aviation.freight_charge<=600000 ";
        } else if (by_price.equals("90")) {
        	and = "and aviation.freight_charge>=600000 and aviation.freight_charge<=900000  ";
        } else if (by_price.equals("91")) {
        	and = "and aviation.freight_charge>=900000 ";
        }

        if (ticket_type.equals("왕복")) { // 왕복
            where += "where (aviation.departure_time >= '" + start_date + "' and aviation.departure_time <= '" + str_date + "') or " // 출국일 날짜
                    + "(aviation.departure_time >= '" + end_date + "' and aviation.departure_time <= '" + ed_date + "') and " // 귀국일 날짜
                    + "(aviation.start_point = '" + start_point + "' and aviation.end_point = '" + end_point + "' or " // 출발지 -> 도착지
                    + "aviation.start_point = '" + end_point + "' and aviation.end_point = '" + start_point + "') "
                    		+ and + group + order;
            } else { // 편도
            where += "where (aviation.departure_time >= '" + start_date + "' and aviation.departure_time <= '" + str_date + "') and " // 출국일 날짜
                    + "(aviation.start_point = '" + start_point + "' and aviation.end_point = '" + end_point + "' ) "
                    		+ and + group + order;
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
		
		request.setAttribute("list", new UserAviationDao().read(where));
	}
}