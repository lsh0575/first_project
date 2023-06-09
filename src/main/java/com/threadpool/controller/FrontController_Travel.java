package com.threadpool.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.AccountDto;
import com.threadpool.service.travel.TDelete;
import com.threadpool.service.travel.TDeleteAdmin;
import com.threadpool.service.travel.TDetail;
import com.threadpool.service.travel.TEdit;
import com.threadpool.service.travel.TEditView;
import com.threadpool.service.travel.TMain;
import com.threadpool.service.travel.TPagingServiceImpl;
import com.threadpool.service.travel.TWrite;
import com.threadpool.service.travel.TravelService;


public class FrontController_Travel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController_Travel() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionTravel(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionTravel(request, response);
	}

	protected void actionTravel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String path = request.getServletPath();

		
		TravelService service = null;
		if (path.equals("/main.travel")) {
			service = new TMain();
			service.exec(request, response);
			request.getRequestDispatcher("/travel/main.jsp").forward(request, response);
		}
		else if(path.equals("/paging.travel")) {
			service = new TPagingServiceImpl();
			service.exec(request, response);
			request.getRequestDispatcher("/travel/list.jsp").forward(request, response);
		}
		else if (path.equals("/search.travel")) {
		    service = new TDetail();
		    service.exec(request, response);
		    request.getRequestDispatcher("/travel/detail.jsp").forward(request, response);
		}
		else if (path.equals("/write_view.travel")) {
			// 사용자 로그인 여부 확인
			if (request.getSession().getAttribute("account") == null) {
				out.println("<script>alert('로그인이 필요합니다!'); history.go(-1); </script>");
			} else {
			  request.getRequestDispatcher("/travel/write.jsp").forward(request, response);
			}
		} 
		else if (path.equals("/write.travel")) {
			service = new TWrite();
			service.exec(request, response);
			out.println("<script>location.href='list.travel';</script>");
		} 
		else if (path.equals("/detail.travel")) {
			service = new TDetail();
			service.exec(request, response);
			request.getRequestDispatcher("/travel/detail.jsp").forward(request, response);
		} 
		else if (path.equals("/edit_view.travel")) {
			service = new TEditView();
			service.exec(request, response);
			request.getRequestDispatcher("/travel/edit.jsp").forward(request, response);
//			if (request.getSession().getAttribute("account") == null) {
//				out.println("<script>alert('로그인이 필요합니다!'); history.go(-1); </script>");
//			} else {
//				AccountDto dto = (AccountDto) request.getSession().getAttribute("account");
//				if (dto.getRole_id() == 1 || dto.getRole_id() == 4) {
//					request.getRequestDispatcher("/edit.travel").forward(request,response);
//				} else {
//					service.exec(request, response);
//					request.getRequestDispatcher("/travel/edit.jsp").forward(request, response);
//				}
//			}
		} 
		else if (path.equals("/edit.travel")) {
			service = new TEdit();
			service.exec(request, response);
			out.println("<script>location.href='detail.travel?tno="+request.getParameter("tno")+"';</script>");
		} 
		else if (path.equals("/delete_view.travel")) {
			if (request.getSession().getAttribute("account") == null) {
				out.println("<script>alert('로그인이 필요합니다!'); history.go(-1); </script>");
			} else {
				AccountDto dto = (AccountDto) request.getSession().getAttribute("account");
				if (dto.getRole_id() == 1 || dto.getRole_id() == 4) {
					request.getRequestDispatcher("/delete_admin.travel").forward(request,response);
				} else {
					request.getRequestDispatcher("/travel/delete.jsp").forward(request, response);
				}
			}
		}
		 else if (path.equals("/delete.travel")) {
		        service = new TDelete();
		        if (request.getSession().getAttribute("account") == null) {
		            out.println("<script>alert('로그인이 필요합니다!'); history.go(-1); </script>");
		        } else {
		            AccountDto dto = (AccountDto) request.getSession().getAttribute("account");
		            if (dto.getRole_id() == 1 || dto.getRole_id() == 4) {
		            	request.getRequestDispatcher("/delete_admin.travel").forward(request,response);
		            } else {
		                // Role_id가 1 또는 4가 아닌 경우
		                // 패스워드 입력 폼을 표시하고 해당 폼을 통해 글 삭제를 처리하는 방법을 구현해야 합니다.
//		                // 입력한 패스워드와 게시글의 패스워드가 일치하는지 확인한다.
//		                // 글을 삭제하는 로직 수행
		            	service.exec(request, response);
		            }
		        }
		 } else if (path.equals("/delete_admin.travel")) {
			 service = new TDeleteAdmin();
			 service.exec(request, response);
		     out.println("<script>location.href='paging.travel';</script>");
		 }
		
	}
}
