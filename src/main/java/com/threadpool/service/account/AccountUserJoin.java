package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountBusinessDto;
import com.threadpool.dto.AccountDto;

public class AccountUserJoin implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDao dao= new AccountDao();
		AccountDto newUser = new AccountDto();
		newUser.setId(request.getParameter("id"));
		newUser.setPass(request.getParameter("pass"));
		newUser.setName(request.getParameter("name"));
		newUser.setBirth(request.getParameter("birth"));
		newUser.setEmail(request.getParameter("email"));
		newUser.setPhonenum(request.getParameter("phonenum"));
		newUser.setPostnum(Integer.parseInt(request.getParameter("postnum")));
		newUser.setAddress(request.getParameter("basic_addr"));
		newUser.setDetail_address(request.getParameter("addr"));
		
		int business = 1;
		int user = dao.userJoin(newUser);
		if (request.getParameter("company_type")!=null) {
			AccountBusinessDto bdto = new AccountBusinessDto();
			bdto.setId(request.getParameter("id"));
			bdto.setCompany_num(request.getParameter("company_num"));
			business = dao.businessJoin(bdto);
		}
		
		if (user * business != 0) {
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
