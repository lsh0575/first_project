package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountUserJoin implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDao dao= new AccountDao();
		AccountDto newUser = new AccountDto();
		
		int newUserResult = 0;
		newUser.setId(request.getParameter("id"));
		newUser.setPass(request.getParameter("pass"));
		newUser.setName(request.getParameter("name"));
		newUser.setBirth(request.getParameter("birth"));
		newUser.setEmail(request.getParameter("email"));
		newUser.setPhonenum(request.getParameter("phonenum"));
		newUser.setPostnum(request.getParameter("postnum"));
		newUser.setAddress(request.getParameter("basic_addr"));
		newUser.setDetail_address(request.getParameter("addr"));
		
		
		int business = 1;
		if (request.getParameter("company_type")!=null) {
			newUser.setRole_id(Integer.parseInt(request.getParameter("company_type")));
			newUser.setCompany_num(request.getParameter("company_num"));
		}
		newUserResult = dao.userJoin(newUser);
		business =request.getParameter("company_type")==null?
					1	:	dao.businessJoin(newUser);
		
		if (newUserResult*business != 0) {
			response.getWriter().println(
					"<script>alert('계정이 정상적으로 만들어졌습니다!');"
					+ "location.href='"+request.getContextPath()+"';</script>");
		} else {
			response.getWriter().println(
					"<script>alert('계정 생성에 실패했습니다! 아이디가 중복되었을 수 있습니다.');"
					+ "history.go(-1);</script>");
		}
	}
}
