package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountUserEdit implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto dto = new AccountDto();
		dto.setId(request.getParameter("id"));
		dto.setName(request.getParameter("name"));
		dto.setBirth(request.getParameter("birth"));
		dto.setEmail(request.getParameter("email"));
		dto.setPhonenum(request.getParameter("phonenum"));
		dto.setPostnum(request.getParameter("postnum"));
		dto.setAddress(request.getParameter("basic_addr"));
		dto.setDetail_address(request.getParameter("addr"));
		dto.setPass(request.getParameter("pass"));
		
		AccountDao dao = new AccountDao();
		if (dao.userEdit(dto)>0) {
			response.getWriter().println("<script>"
					+ "alert('계정 수정에 성공했습니다!');"
					+ "location.href='"+request.getContextPath()+"/user.acc?id="+request.getParameter("id")+"'</script>");
		} else {
			response.getWriter().println("<script>"
					+ "alert('계정 수정에 실패했습니다!');"
					+ "history.go(-1);</script>");
		}
	}
}