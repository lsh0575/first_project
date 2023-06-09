package com.threadpool.service.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountDelete implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto loginAccount = (AccountDto)request.getSession().getAttribute("account");
		if (loginAccount.getRole_id()!=1) { //관리자가 아닐 경우
			response.getWriter().println("<script>"
					+ "alert('권한이 없습니다!');"
					+ "location.href='"+request.getContextPath()+"';"
					+ "</script>");
		} else { //관리자가 계정삭제를 시도했을 경우
			List<AccountDto> list = new ArrayList<>();
			for (String id : request.getParameterValues("check")) {
				AccountDto dto = new AccountDto();
				dto.setId(id);
				list.add(dto);
			}
			int result = new AccountDao().userDelete(list);
			if (result !=list.size()) {
				response.getWriter().println("<script>"
						+ "alert('"+(list.size()-result)+"개의 실패가 있습니다!');"
						+ "history.go(-1);"
						+ "</script>");
			} else {
				response.getWriter().println("<script>"
						+ "alert('계정 정보를 삭제했습니다!');"
						+ "location.href='"+request.getContextPath()+"/user_list.acc';"
						+ "</script>");
			}
		}
	}
}
