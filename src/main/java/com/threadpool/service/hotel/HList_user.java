package com.threadpool.service.hotel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.AccountDto;
import com.threadpool.dto.hotel.HotelPaging_user;

public class HList_user implements HotelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		if (request.getSession().getAttribute("account")==null) {
			response.getWriter().print("<script>alert('로그인 후 이용 가능합니다.'); history.go(-1);</script>");
		} else {
		int pstartno = request.getParameter("pstartno") != null? Integer.parseInt(request.getParameter("pstartno")) : 0;
		AccountDto dto = (AccountDto)request.getSession().getAttribute("account");
		request.setAttribute("paging", new HotelPaging_user(pstartno,dto));

		request.getRequestDispatcher("/hotel/user_list.jsp").forward(request, response);
		}
	}
}
