package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountOutSelf implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto dto = new AccountDto();
		int statusUpdate = 0;
		int insertOut = 0;
		dto.setId(request.getParameter("id"));
		dto.setPass(request.getParameter("pass"));
		dto.setOut_reason(request.getParameter("out_reason").trim());
		dto.setStatus_id(1);
		
		AccountDao dao= new AccountDao();
		
		statusUpdate = dao.userStatusUpdateWithPass(dto);
		if ( statusUpdate>0 ) {
			insertOut = dao.userOut(dto);
		}
		
		if (statusUpdate * insertOut != 0) {
			response.getWriter().print("<script>"
					+ "alert('탈퇴가 정상적으로 처리되었습니다.\\n"
					+ "그동안 감사했습니다.');"
					+ "location.href='"+request.getContextPath()+"/logout.acc';"
					+ "</script>");
		} else {
			response.getWriter().print("<script>"
					+ "alert('비밀번호를 확인해주세요.');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
