package com.threadpool.service.travel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.TravelDao;
import com.threadpool.dto.TravelDto;

public class TDeleteAdmin implements TravelService{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TDelete
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String msg = "관리자에게 문의 바랍니다.";
		
		TravelDto dto = new TravelDto();
		TravelDao dao = new TravelDao();
		
		dto.setTno(Integer.parseInt(request.getParameter("tno")));
		
		int result = dao.delete_admin(dto);
		
		if(result > 0) { msg = "삭제에 성공했습니다."; }
		
		out.println("<script>alert('"+ msg +"'); location.href='paging.travel'; </script>");
		
	}

}
