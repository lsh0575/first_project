package com.threadpool.service.travel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.TravelDao;
import com.threadpool.dto.TravelDto;

public class TEdit implements TravelService {
	
	

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String msg = "관리자에게 문의 바랍니다.";
		
		
		TravelDto dto = new TravelDto();
		TravelDao dao = new TravelDao();
		
		dto.setTtitle(request.getParameter("ttitle"));
		dto.setTcontent(request.getParameter("tcontent"));
		dto.setTcost(request.getParameter("tcost"));
		dto.setTstart_date(request.getParameter("tstart_date"));
		dto.setTend_date(request.getParameter("tend_date"));
		dto.setTpass(request.getParameter("tpass"));
		dto.setTno(Integer.parseInt(request.getParameter("tno")));
		
		int result = dao.update(dto);
//		System.out.println("[TEdit result]===========>" + result);
		
		if(result > 0) { msg="업데이트에 성공했습니다."; }
		out.println("<script>alert('"+ msg +"')</script>");
	}
}
