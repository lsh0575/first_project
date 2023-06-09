package com.threadpool.service.air;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.UserReservationDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.ReservationDto;

public class TUser_UsDelete implements TAction {

	@Override public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserReservationDao dao = new UserReservationDao();
		AccountDto dto = new AccountDto();
		
		int reservation_number = Integer.parseInt(request.getParameter("reservation_number"));
		AccountDto account = (AccountDto) request.getSession().getAttribute("account");
		String id = account.getId();
		String pass = request.getParameter("pass");
		dto.setId(id); dto.setPass(pass);
		
		int result = dao.delete(new ReservationDto(reservation_number), dto);
		
		if (result > 0) {
			out.println("<script>alert('예약이 삭제 되었습니다.\\n마이 페이지로 이동합니다.'); location.href='" + request.getContextPath()
					+ "/air_mypage.air';</script>");
		} else {
			out.println("<script>alert('관리자 문의 바랍니다.\\n메인 페이지로 이동합니다.'); location.href='" + request.getContextPath()+"';</script>");
		}
	}
}