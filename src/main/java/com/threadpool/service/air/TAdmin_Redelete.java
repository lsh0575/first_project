package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AdminReservationDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.ReservationDto;

public class TAdmin_Redelete implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		AdminReservationDao dao = new AdminReservationDao();
		AccountDto dto = new AccountDto();
		
		int reservation_number = Integer.parseInt(request.getParameter("reservation_number"));
		AccountDto account = (AccountDto) request.getSession().getAttribute("account");
		String id = account.getId();
		String pass = request.getParameter("pass");
		dto.setId(id); dto.setPass(pass);
		
		int result = dao.delete(new ReservationDto(reservation_number), dto);
		
		if(result > 0) {
			out.println("<script>alert('예약자 및 승객자 데이터 삭제 완료'); location.href='"+request.getContextPath()+"/air_relist.air';</script>");
		} else {
			out.println("<script>alert('예약자 및 승객자 데이터 삭제 실패'); history.go(-1);</script>");
		}
	}
}