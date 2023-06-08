package com.threadpool.service.travel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.TravelDao;
import com.threadpool.dto.TravelDto;

public class TEditView implements TravelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		TravelDto dto = new TravelDto();
		TravelDao dao = new TravelDao();
		
		dto.setTno(Integer.parseInt(request.getParameter("tno")));


		request.setAttribute("dto", dao.detail(dto));
		
	}
}
