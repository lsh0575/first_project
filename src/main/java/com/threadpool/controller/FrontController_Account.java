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
		}else if (path.equals("/id_dupl.acc")) {
			//아이디 중복체크 ajax
			accAction = new AccountDuplCheck(); accAction.exec(request, response);
		} else if (path.equals("/user.acc")) {
			//마이페이지
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
		}else if(path.equals("/user_pass_edit.acc")) {
			//비밀번호 수정 폼
			request.getRequestDispatcher("/acc/user_pass_edit.jsp").forward(request, response);
		}else if (path.equals("/user_pass_edit_act.acc")) {
			//비밀번호 수정.
			//내부에서 location.href 찍어줌.
			accAction = new AccountPassEdit(); accAction.exec(request, response);
		} else if (path.equals("/user_out.acc")) {
			//회원탈퇴 폼
			//자신의 계정이 아니라면 돌아가게.
			//내부에서 링크 보내줌.
			accAction = new AccountOutAccountCheck(); accAction.exec(request, response);
		} else if (path.equals("/user_out_act.acc")) {
			//회원탈퇴
			//내부에서 링크 보내줌.
			accAction = new AccountOutSelf(); accAction.exec(request, response);
		} else if (path.equals("/user_active.acc")){ 
			//회원 재활성화
			//내부에서 링크 보내줌.
			accAction = new AccountActive(); accAction.exec(request,response);
		}else if (path.equals("/user_forced_out.acc")) {
			//유저 강제 탈퇴
			//내부에서 링크 보내줌
			accAction = new AccountForcedOut(); accAction.exec(request, response);
		}else if (path.equals("/user_del.acc")) {
			//유저 계정 삭제
			//내부에서 링크 보내줌
			accAction = new AccountDelete(); accAction.exec(request, response);
		} else if (path.equals("/idsearch.acc")) {
			//유저 아이디 검색 폼
			request.getRequestDispatcher("/acc/idsearch.jsp").forward(request,response);
		} else if (path.equals("/id_search_act.acc")) {
			//유저 아이디 검색 실행
			//내부에서 링크 보내줌
			accAction = new AccountIdSearch(); accAction.exec(request, response);
		} else if (path.equals("/pass_search_act.acc")) {
			//유저 패스워드 검색 실행
			//내부에서 링크 보내줌
			accAction = new AccountPassSearch(); accAction.exec(request, response);
		}
	}
}
