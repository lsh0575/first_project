package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountBusinessDto;
import com.threadpool.dto.AccountDto;

public class AccountUserDetail implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto account = new AccountDto();
		account.setId(request.getParameter("id"));
		AccountDao dao = new AccountDao();
		request.setAttribute("account_detail", dao.detail(account));
		if ( ((AccountDto)request.getAttribute("account_detail")).getRole_id()>1){
			AccountBusinessDto businessDetail = dao.businessDetail(account);
			request.setAttribute("business_detail",businessDetail);
		}
	}
}