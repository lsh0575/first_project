package com.threadpool.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.threadpool.service.air.*;

@WebServlet("*.air")
public class FrontController_air extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController_air() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response); }

	protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String path = request.getServletPath();
		TAction tAction = null;

		if (path.equals("/air_avlist.air")) { // 관리자_항공편 관리 메인 뷰
			tAction = new TAdmin_AvList();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_avlist.jsp").forward(request, response);
		} else if (path.equals("/air_avinsert_view.air")) { // 관리자_항공편 데이터 생성 뷰
			request.getRequestDispatcher("air/air_avinsert.jsp").forward(request, response);
		} else if (path.equals("/air_avinsert.air")) { // 관리자_항공 데이터 생성 action
			tAction = new TAdmin_AvInsert();
			tAction.exec(request, response); }
		else if (path.equals("/air_avdetail.air")) { // 관리자_항공 특정 데이터 뷰
			tAction = new TAdmin_AvDetail();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_avdetail.jsp").forward(request, response);
		} else if (path.equals("/air_avedit_view.air")) { // 관리자_항공 특정 데이터 수정 뷰
			tAction = new TAdmin_AvDetail();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_avedit.jsp").forward(request, response);
		} else if (path.equals("/air_avedit.air")) { // 관리자_항공 특정 데이터 수정 action
			tAction = new TAdmin_AvUpdate();
			tAction.exec(request, response);
		} else if (path.equals("/air_avdelete_view.air")) { // 관리자_항공 특정 데이터 삭제 뷰
			request.getRequestDispatcher("air/air_avdelete.jsp").forward(request, response);
		} else if (path.equals("/air_avdelete.air")) { // 관리자_항공 특정 데이터 action
			tAction = new TAdmin_AvDelete();
			tAction.exec(request, response);
		} else if (path.equals("/air_relist.air")) { // 관리자_예약자 관리 메인 뷰
			tAction = new TAdmin_ReList();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_relist.jsp").forward(request, response);
		} else if (path.equals("/air_redetail.air")) { // 관리자_예약자 특정 데이터 
			tAction = new TAdmin_ReDetail();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_redetail.jsp").forward(request, response);
		} else if (path.equals("/air_reedit_view.air")) { // 관리자_예약자 데이터 수정 뷰
			tAction = new TAdmin_ReDetail();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_reedit.jsp").forward(request, response);
		} else if (path.equals("/air_reedit.air")) { // 관리자_예약자 데이터 수정 action
			tAction = new TAdmin_ReUpdate();
			tAction.exec(request, response);
		} else if (path.equals("/air_redelete_view.air")) { // 관리자_예약자 데이터 삭제 뷰
			request.getRequestDispatcher("air/air_redelete.jsp").forward(request, response);
		} else if (path.equals("/air_redelete.air")) { // 관리자_예약자 데이터 삭제 action
			tAction = new TAdmin_Redelete();
			tAction.exec(request, response);
		} else if (path.equals("/air.Avmain_start_pointjoin.air")) { // 메인 페이지 (AJAX) - 출발지 입력 시, 출발지 데이터 받아옴
			tAction = new TUser_Start_PointJoin();
			tAction.exec(request, response);
		} else if (path.equals("/air.Avmain_end_pointjoin.air")) { // 메인 페이지 (AJAX) - 출발지 입력 시, 출발지 데이터 받아옴
			tAction = new TUser_End_PointJoin();
			tAction.exec(request, response);
		} else if (path.equals("/air_uslist.air")) { // 유저_항공 검색 뷰
			tAction = new TUser_UsList();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_uslist.jsp").forward(request, response);
		} else if (path.equals("/air_uslistAjax.air")) { // 유저_항공 조건 검색 뷰
			tAction = new TUser_UsListAjax();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_uslist.jsp").forward(request, response);
		} else if (path.equals("/air_usinsert_view.air")) { // 유저_항공 예약 등록 뷰
			tAction = new TUser_UsInsert_View();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_usinsert_view.jsp").forward(request, response);
		} else if (path.equals("/air_usinsert.air")) { // 유저_항공 예약 action
			tAction = new TUser_UsInsert();
			tAction.exec(request, response);
		} else if (path.equals("/air_mypage.air")) { // 유저_항공 마이 페이지 뷰
			tAction = new TUser_MyPage();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_mypage.jsp").forward(request, response);
		} else if (path.equals("/air_usdetail.air")) { // 유저_항공 예약 특정 데이터 뷰
			tAction = new TUser_UsDetail();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_usdetail.jsp").forward(request, response);
		} else if (path.equals("/air_usupdate_view.air")) { // 유저_항공 예약 특정 데이터 수정 뷰
			tAction = new TUser_UsDetail();
			tAction.exec(request, response);
			request.getRequestDispatcher("air/air_usedit.jsp").forward(request, response);
		} else if (path.equals("/air_usupdate.air")) { // 유저_항공 예약 특정 데이터 수정 action
			tAction = new TUser_UsUpdate();
			tAction.exec(request, response);
		} else if (path.equals("/air_usdelete_view.air")) { // 유저_항공 예약 특정 데이터 삭제 뷰
			request.getRequestDispatcher("air/air_usdelete.jsp").forward(request, response);
		} else if (path.equals("/air_usdelete.air")) { // 유저_항공 예약 특정 데이터 삭제 action
			tAction = new TUser_UsDelete();
			tAction.exec(request, response);
		}
	}
}