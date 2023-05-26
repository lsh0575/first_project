package com.threadpool.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.service.account.*;

@WebServlet("*.acc")
public class FrontController_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController_Account() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}
	protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String path = request.getServletPath();
		AccountAction accAction = null;
		
		if (path.equals("/login.acc")) {
			//로그인 확인하기
			accAction = new AccountLogin(); accAction.exec(request, response);
			//내부에서 sendRedirect / history.go(-1) 찍어줌.
		} else if (path.equals("/logout.acc")) {
			accAction = new AccountLogout(); accAction.exec(request, response);
			response.sendRedirect(request.getContextPath());
		} else if (path.equals("/join.acc")) {
			//회원가입 선택창으로 보내기
			request.getRequestDispatcher("/acc/join_select.jsp").forward(request,response);
		} else if (path.equals("/user_join_agree.acc")) {
			//일반사용자 회원가입 이용약관 동의창
			request.getRequestDispatcher("/acc/user_join_accept.jsp").forward(request, response);
		} else if (path.equals("/user_join.acc")) {
			//일반사용자 회원가입 정보 기입창
			request.getRequestDispatcher("/acc/user_join.jsp").forward(request,response);
		} else if (path.equals("/user_create.acc")) {
			//일반사용자 계정생성
			//내부에서 location.href 찍어줌.
			//사업자도 계정생성은 여기서 한꺼번에 처리.
			accAction = new AccountUserJoin(); accAction.exec(request, response);
		} else if (path.equals("/business_join_agree.acc")) {
			//사업자 회원가입 이용약관 동의창
			request.getRequestDispatcher("/acc/business_join_accept.jsp").forward(request, response);
		} else if (path.equals("/business_join.acc")) {
			//사업자 회원가입 정보 기입창
			//내부에서 location.href 찍어줌.
			request.getRequestDispatcher("/acc/business_join.jsp").forward(request,response);	
		} else if (path.equals("/user.acc")) {
			//마이페이지
			if (request.getParameter("id") == null) {
				
			}
			accAction = new AccountUserDetail(); accAction.exec(request, response);
			request.getRequestDispatcher("/acc/mypage.jsp").forward(request, response);
		} else if (path.equals("/user_list.acc")){
			//관리자 로그인했을 때 사용자 관리 리스트
			accAction = new AccountUserList(); accAction.exec(request, response);
			request.getRequestDispatcher("acc/user_list.jsp").forward(request, response);
		} else if (path.equals("/user_edit.acc")){
			//유저 수정 폼.
			accAction = new AccountUserDetail(); accAction.exec(request, response);
			request.getRequestDispatcher("/acc/user_edit.jsp").forward(request, response);
		} else if (path.equals("/user_edit_act.acc")) {
			//유저 데이터 수정.	
			//내부에서 location.href 찍어줌.
			accAction = new AccountUserEdit(); accAction.exec(request, response);
		} else if (path.equals("/id_dupl.acc")) {
			//아이디 중복체크 ajax
			accAction = new AccountDuplCheck(); accAction.exec(request, response);
		}
	}
}
