package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AdminAviationDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.AviationDto;

public class TAdmin_AvDelete implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		AdminAviationDao dao = new AdminAviationDao();
		AccountDto dto = new AccountDto();
		
		int flight_code = Integer.parseInt(request.getParameter("flight_code"));
		AccountDto account = (AccountDto) request.getSession().getAttribute("account");
		String id = account.getId();
		String pass = request.getParameter("pass");
		dto.setId(id); dto.setPass(pass);
		int result = dao.delete(new AviationDto(flight_code), dto);
		
		if(result > 0) {
			out.println("<script>alert('항공 데이터 삭제 성공'); location.href='"+request.getContextPath()+"/air_avlist.air';</script>");
		} else {
			out.println("<script>alert('항공 데이터 삭제 실패'); history.go(-1);</script>");
		}
	}
}