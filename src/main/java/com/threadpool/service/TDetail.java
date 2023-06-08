package com.threadpool.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.TravelDao;
import com.threadpool.dto.TravelDto;

public class TDetail implements TravelService{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TDetail
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String msg = "관리자에게 문의 바랍니다.";
		
		TravelDto dto = new TravelDto();
		TravelDao dao = new TravelDao();
		
//		// VER - 2
		String tno = "";
		String tname = "";

		String searchValue = request.getParameter("search-select");
		if("tno".equals(searchValue)) {
			tno = request.getParameter("searchValue");
		} else if("tname".equals(searchValue)){
			tname = request.getParameter("searchValue");
		} else {
			tno = request.getParameter("tno");
		}
	
		
		// 입력된 값이 tno인지 tname 인지 확인하는 구문
		if (tno != null && !"".equals(tno)) {
		    if (!tno.equals("tno")) {
		        try {
		            dto.setTno(Integer.parseInt(tno));
		            // update_hit 조회수 증가
		            int result = dao.update_hit(dto);

		            if (result <= 0) {
		                out.println("<script>alert('" + msg + "'); history.go(-1);</script>");
		            } else {
		                request.setAttribute("dto", dao.detail(dto));
		            }
		        } catch (NumberFormatException e) { e.printStackTrace(); }
		    } else {
		    	// 원래 ajax 로 해서 button 으로 만들었었는데 하다가 실패 했다 ㅋㅋㅋㅋ
		        out.println("<script>alert('" + msg + "'); history.go(-1);</script>");
		    }
		} else if (tname != null && !"".equals(tname)) {
		    dto.setTname(tname);
		    			
		    
//		    리스트 먼저 조회하고 조회한뒤에 게기슬 번호 값을 가지고 업데이트, 
//		    tname 값 가지고 리스트 조회하고 조회해서 리스트가 조회되면 카운트 올려주고 조회안되면 카운트 안올리기
		    TravelDto result = dao.search_tname(dto);
		    
		    if (result != null) {
		    	dto.setTno(result.getTno());
		    	dao.update_hit(dto);
		    	request.setAttribute("dto", result);
		    } else {
		    	out.println("<script>alert('" + msg + "'); history.go(-1);</script>");
		    }
		    
		} else if (tno != null || !"".equals(tno) || tname != null || !"".equals(tname)){
		    out.println("<script>alert('" + msg + "'); history.go(-1);</script>");
		    request.getRequestDispatcher("/travel/list.jsp").forward(request, response);
		}
	}
}
