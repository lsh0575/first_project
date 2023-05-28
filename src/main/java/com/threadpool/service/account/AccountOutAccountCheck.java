package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.AccountDto;

public class AccountOutAccountCheck implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		String loginId = request.getSession().getAttribute("account")==null?
				"" : ((AccountDto)request.getSession().getAttribute("account")).getId();
		if (paramId!=null && paramId.equals(loginId)) {
			request.getRequestDispatcher("/acc/user_out.jsp").forward(request, response);
		} else {
			response.getWriter().print("<script>alert('내 계정이 아닙니다.');"
					+ "location.href='"+request.getContextPath()+"/"+(paramId==null?"":("user.acc?id="+paramId))+"'"
					+ "</script>");
		}
	}
}
