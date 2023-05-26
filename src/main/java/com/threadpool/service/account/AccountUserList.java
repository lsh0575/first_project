package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.AccountDto;
import com.threadpool.dto.AccountListPage;

public class AccountUserList implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//만약 관리자 권한이 없는 사람이 왔으면 돌려보내기.
		AccountDto loginAccount = (AccountDto)(request.getSession().getAttribute("account"));
		if (loginAccount==null || loginAccount.getRole_id()!=1) {
			response.getWriter().println("<script>alert('권한이 없습니다.'); location.href='"+request.getContextPath()+"'</script>");
		}	else {
			int pageStartNum = 0; String where="";
			if (request.getParameter("pageStartNum")!=null) {
				pageStartNum = Integer.parseInt(request.getParameter("pageStartNum"));
			}
			if (request.getParameter("where")!=null) {
				where = "where "+request.getParameter("where")+" like '"+request.getParameter("searchkey")+"%'";
			}
			request.setAttribute("page_count", new AccountListPage(pageStartNum,where));
		}
	}
}
