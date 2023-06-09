package com.threadpool.service.account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountIdSearch implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyType = request.getParameter("type");
		String key = request.getParameter("searchkey");
		List<AccountDto> list = new AccountDao().idSearch(keyType,key);
		if (list.size()==0) { //검색 결과가 없을 때
			response.getWriter().print("<script>"
					+ "alert('검색 결과가 없습니다!');"
					+ "history.go(-1);</script>");
		} else{ //검색결과가 있을 때
			response.getWriter().print("<script>"
					+ "alert('회원님의 정보로 검색한 결과,\\n"
					+ "회원님의 아이디는 \\n[ ");
			for (int i=0; i<list.size(); i++) {
				response.getWriter().print(i==0?"":" , ");
				response.getWriter().print(list.get(i).getId());
			}
			response.getWriter().print(" ]\\n입니다.');"
					+ "history.go(-1);</script>");
		}
	}
}
