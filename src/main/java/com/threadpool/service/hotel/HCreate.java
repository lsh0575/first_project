package com.threadpool.service.hotel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.threadpool.dao.HotelDao;
import com.threadpool.dto.hotel.HotelDtoImg;
import com.threadpool.dto.hotel.HotelDtoOption;
import com.threadpool.dto.hotel.HotelDtoProd;

public class HCreate implements HotelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HotelDao dao = new HotelDao();
		HotelDtoProd dtop = new HotelDtoProd();
		HotelDtoOption dtoo = new HotelDtoOption();
		HotelDtoImg	dtoi = new HotelDtoImg();
		String msg = "관리자에게 문의바랍니다.";
		// 경로 설정
		String path = "/upload/hotel/";
		path = request.getServletContext().getRealPath(path);
		// 파일 업로드
		try {
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5,"UTF-8"  ,new DefaultFileRenamePolicy());
		String file1 = multi.getFilesystemName("img1");
		String file2 = multi.getFilesystemName("img2");
		String file3 = multi.getFilesystemName("img3");
		String file4 = multi.getFilesystemName("img4");
		dtop.setHname( multi.getParameter("hname"));
		dtop.setHtype(multi.getParameter("htype"));
		dtop.setHprice( Integer.parseInt(multi.getParameter("hprice")) );
		dtop.setHgrade(Integer.parseInt(multi.getParameter("hgrade")) );
		dtop.setHbed(multi.getParameter("hbed"));
		dtop.setHcnt(Integer.parseInt(multi.getParameter("hcnt")) );
		dtop.setHcontent(multi.getParameter("hcontent"));
		dtop.setCheckin(multi.getParameter("checkin"));
		dtop.setCheckout(multi.getParameter("checkout"));
		dtop.setHnation( multi.getParameter("hnation"));
		
		
		dtoo.setSmoke((multi.getParameter("smoke") == null )? false : true);   //뷰단에서 체크박스 유무에 따라 true or false 로 값을 받고싶은데..multi.getparameter 
		dtoo.setRef(  multi.getParameter("ref") == null? false : true);
		dtoo.setWifi( multi.getParameter("wifi") == null? false : true);
		dtoo.setTv(   multi.getParameter("tv") == null? false : true);
		dtoo.setTub(  multi.getParameter("tub") == null? false : true);
		dtoo.setAirc( multi.getParameter("airc") == null? false : true);
		dtoo.setWash( multi.getParameter("wash") == null? false : true);
		
		dtoi.setImg1(file1);
		dtoi.setImg2(file2);
		dtoi.setImg3(file3);
		dtoi.setImg4(file4);
		if(file1 == null) {   dtoi.setImg1("/no.jpg");}		
		if(file2 == null) {   dtoi.setImg2("/no.jpg");}	
		if(file3 == null) {   dtoi.setImg3("/no.jpg");}	
		if(file4 == null) {   dtoi.setImg4("/no.jpg");}
		}catch(Exception e) { e.printStackTrace(); }
		
		
		int result1 = dao.createProd(dtop);
		int result2;
		int result3;
		
		
		if(result1 > 0) {
			result2 = dao.createOption(dtoo);
			result3 = dao.createImg(dtoi);
			msg = "글쓰기성공!";
			if(result1>0 &&  result2>0 && result3>0) { out.println("<script>alert('"+msg+"');</script>"); }
		}
		
		
	}

}

