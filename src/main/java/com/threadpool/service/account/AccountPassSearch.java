package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountPassSearch implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto id = new AccountDto();
		id.setId(request.getParameter("passsearchid"));
		String keyType = request.getParameter("type");
		String key = request.getParameter("searchkey");
		id = new AccountDao().passSearch(id, keyType,key);
		if (id.getPass()==null) { //검색 결과가 없을 때
			response.getWriter().print("<script>"
					+ "alert('검색 결과가 없습니다!');"
					+ "history.go(-1);</script>");
		} else{ //검색결과가 있을 때
			response.getWriter().print("<script>"
					+ "alert('회원님의 정보로 검색한 결과,\\n"
					+ id.getId() + " 님의 비밀번호는 \\n"
					+ "[  " + id.getPass() + " ] 입니다.');"
					+ "history.go(-1);</script>");
		}
	}
}
