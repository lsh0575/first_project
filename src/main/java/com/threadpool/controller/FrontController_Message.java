package com.threadpool.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.service.message.ThrdpMessageAction;
import com.threadpool.service.message.ThrdpMessageDelete;
import com.threadpool.service.message.ThrdpMessageIDCheckAjax;
import com.threadpool.service.message.ThrdpMessageIDListAjax;
import com.threadpool.service.message.ThrdpMessagePagingAjax;
import com.threadpool.service.message.ThrdpMessageRead;
import com.threadpool.service.message.ThrdpMessageSend;


public class FrontController_Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController_Message() {
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
		
		//만약 로그인하지 않았는데 메시지단으로 들어오려고 하면 메인으로 날려버리기
		if (request.getSession().getAttribute("account")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String path = request.getServletPath();
		ThrdpMessageAction serv = null;
		if (path.equals("/write.msg")) {
			//메시지 작성 폼으로 보내기
			request.getRequestDispatcher("/msg/send_form.jsp").forward(request, response);
		} else if (path.equals("/send.msg")) {
			//메시지 발송하기.
			serv = new ThrdpMessageSend(); serv.exec(request, response);
		} else if (path.equals("/list.msg")) {
			//메시지 목록 보기.
			//내부에서 링크 타고 감.
			request.getRequestDispatcher("/msg/msg_list.jsp").forward(request, response);
		} else if (path.equals("/read.msg")){
			//메세지 읽기
			serv = new ThrdpMessageRead(); serv.exec(request, response);
			request.getRequestDispatcher("/msg/read.jsp").forward(request, response);
		} else if (path.equals("/delete.msg")) {
			//메시지 삭제하기
			//내부에서 링크 타고 감.
			serv = new ThrdpMessageDelete(); serv.exec(request, response);
		} else if (path.equals("/list_ajax.msg")) {
			//메시지 리스트 ajax
			serv = new ThrdpMessagePagingAjax(); serv.exec(request, response);
		} else if (path.equals("/idlist.msg")) {
			//아이디 자동완성 ajax
			serv = new ThrdpMessageIDListAjax(); serv.exec(request, response);
		} else if (path.equals("/idcheck.msg")) {
			//아이디 체크 ajax
			serv = new ThrdpMessageIDCheckAjax(); serv.exec(request, response);
		}
	}
	
}
