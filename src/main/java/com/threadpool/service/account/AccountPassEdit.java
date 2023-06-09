package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountPassEdit implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto dto = new AccountDto();
		dto.setId(request.getParameter("id"));
		dto.setPass(request.getParameter("pass"));
		if (new AccountDao().passEdit(dto, request.getParameter("newpass"))>0) {
			response.getWriter().print("<script>alert('정상적으로 비밀번호를 수정했습니다.');"
					+ "location.href='"+request.getContextPath()+"/user.acc?id="+request.getParameter("id")+"';</script>");
		} else {
			response.getWriter().print("<script>alert('비밀번호를 확인해주세요!');"
					+ "history.go(-1);</script>");
		}
		
	}
}
