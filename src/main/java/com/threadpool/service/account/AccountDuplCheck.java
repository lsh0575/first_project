package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;

public class AccountDuplCheck implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDao dao = new AccountDao();
		String id = request.getParameter("id");
		boolean dupl = dao.isIdDupl(id);
		String result = "<span id='id_check' style='color:"+(dupl?"red'>아이디가 중복되었습니다.":"green' data-check='checked'>사용 가능한 아이디입니다.")+"</span>";
		response.getWriter().println(result);
	}
}
