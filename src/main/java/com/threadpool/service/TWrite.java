package com.threadpool.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.threadpool.dao.TravelDao;
import com.threadpool.dto.TravelDto;

public class TWrite implements TravelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if (request.getSession().getAttribute("account")==null) {
//			response.getWriter().println("<script>alert('로그인이 필요합니다!'); history.go(-1);</script>");
//		}
		// 1. 경로 설정
		String path = "/upload";
		
		System.out.println("경로 : " + path);
		
		MultipartRequest multi = 
				new MultipartRequest(request, path, 1024*1024*5, "UTF-8",
						new DefaultFileRenamePolicy());
		
		//TWrite - 글삽입
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String msg = "관리자에게 문의 바랍니다.";
		String link = "history.go(-1);";
		
		
		TravelDto dto = new TravelDto();
		TravelDao dao = new TravelDao();
		
		// Write
//		dto.setThit(Integer.parseInt(multi.getParameter("thit")));
		dto.setTname(multi.getParameter("tname"));
		dto.setTpass(multi.getParameter("tpass"));
		dto.setTcategory(Integer.parseInt(multi.getParameter("tcategory")));
		dto.setTtitle(multi.getParameter("ttitle"));
		dto.setTcontent(multi.getParameter("tcontent"));
		dto.setTscore(Integer.parseInt(multi.getParameter("tscore")));
		dto.setTcost(multi.getParameter("tcost"));
		dto.setTstart_date(multi.getParameter("tstart_date"));
		dto.setTend_date(multi.getParameter("tend_date"));
		
		// ImageWrite
		dto.setTimages_1(multi.getFilesystemName("timages_1"));
		dto.setTimages_2(multi.getFilesystemName("timages_2"));
		dto.setTimages_3(multi.getFilesystemName("timages_3"));
		
		// 2. 파일 업로드
		try {
			String file = multi.getFilesystemName("timages_1");
			if(file==null) {
				System.out.println("이미지가 업로드 되지 않았습니다."); dto.setTimages_1("default.jpg");
			}else {dto.setTimages_1(file);}
			file = multi.getFilesystemName("timages_2");
			dto.setTimages_2(file);
			file = multi.getFilesystemName("timages_3");
			dto.setTimages_3(file);
		}catch (Exception e) { e.printStackTrace(); }
//		
		int result = dao.insert(dto);
		int result2 = dao.insert_img(dto);
		
		if(result > 0 && result2 > 0) {
			msg = "글쓰기에 성공했습니다."; 
			link = "location.href='"+request.getContextPath()+"/paging.travel';";
			}
		out.println("<script>alert('"+msg+"');"+link+"</script>");
	}

}
