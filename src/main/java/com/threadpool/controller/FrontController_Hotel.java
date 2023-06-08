package com.threadpool.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.service.hotel.*;

@WebServlet("*.hotel")
public class FrontController_Hotel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController_Hotel() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionHotel(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionHotel(request, response);
	}
	
	protected void actionHotel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); out.print("");
		String path = request.getServletPath();
		HotelService service = null;
		
		
		if(path.equals("/user_view.hotel")) { //유저의 맨 처음 뷰단
			service = new HVeiw_user(); service.exec(request, response); 
			request.getRequestDispatcher("/hotel/user_view.jsp").forward(request, response);
			}
		if(path.equals("/Ajax_list.hotel")) { //유저의 맨 처음 뷰단의 아작스
			service = new Ajax_list(); service.exec(request, response); 
			}
		else if(path.equals("/user_detail.hotel")) { //유저의 상세보기
			service = new HDetail_user(); service.exec(request, response); 
			request.getRequestDispatcher("/hotel/user_detail.jsp").forward(request, response);
			}
		else if(path.equals("/user_reserve.hotel")) { //유저의 예약하기
			service = new HReserve_user(); service.exec(request, response); 
			out.println("<script>location.href='user_view.hotel';</script>");
			}
		else if(path.equals("/user_delete.hotel")) { //유저의 예약취소
			service = new HDelete_user(); service.exec(request, response); 
			out.println("<script>location.href='user_view.hotel';</script>");
			}
		else if(path.equals("/user_deleteView.hotel")) { //유저의 상세보기 (삭제폼)
			service = new HDetail_user(); service.exec(request, response); 
			request.getRequestDispatcher("/hotel/user_delete.jsp").forward(request, response);
			}
		if(path.equals("/user_reservelist.hotel")) { //유저의 마이페이지 예약조회
			service = new HList_user(); service.exec(request, response); 
			request.getRequestDispatcher("/hotel/user_list.jsp").forward(request, response);
			}
		if(path.equals("/user_hotellist.hotel")) { //유저의 상품전체보기
			service = new HList_user2(); service.exec(request, response); 
			request.getRequestDispatcher("/hotel/user_totalList.jsp").forward(request, response);
			}
		
		else if(path.equals("/manager_list.hotel")) { //관리자의 상품목록
			service = new HList(); service.exec(request, response); 
			request.getRequestDispatcher("/hotel/manager_list.jsp").forward(request, response);
			}
		else if(path.equals("/manager_create.hotel")) { //관리자의 상품등록
			service = new HCreate(); service.exec(request, response); 
			out.println("<script>location.href='manager_list.hotel';</script>");
			}
		else if(path.equals("/manager_detail.hotel")) { //관리자의 상품 상세보기
			service = new HDetail(); service.exec(request, response); 
			request.getRequestDispatcher("/hotel/manager_detail.jsp").forward(request, response);
			}
		else if(path.equals("/manager_edit_view.hotel")) { //관리자의 상품 수정
			service = new HEditView(); service.exec(request, response);
			request.getRequestDispatcher("/hotel/manager_edit.jsp").forward(request, response);
			}
		else if(path.equals("/manager_edit.hotel")) { //관리자의 상품수정 액션
			service = new HEdit(); service.exec(request, response);
			out.println("<script>location.href='manager_detail.hotel?hno="+request.getParameter("hno")+"';</script>");
			}
		else if(path.equals("/manager_delete.hotel")) { //관리자의 상품삭제
			service = new HDelete(); service.exec(request, response);
			out.println("<script>location.href='manager_list.hotel';</script>");
			}
	}
	
}