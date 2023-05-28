package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountLogin implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDao dao = new AccountDao();
		AccountDto input = new AccountDto();
		input.setId(request.getParameter("id"));
		input.setPass(request.getParameter("pass"));
		AccountDto accountData = dao.login(input);
		if (accountData!=null) { //계정은 있을 때
			if (accountData.getStatus_id() != 0) { //계정이 활성화 상태가 아닌 경우
				response.getWriter().println("<script>alert('비활성화된 아이디입니다.\\n비활성화 사유 : \\n"+accountData.getOut_reason()+"');"
											+ "location.href='"+request.getContextPath()+"';</script>");
			} else { //계정이 활성화상태일 때
				request.getSession().setAttribute("account", accountData);
				response.sendRedirect(request.getContextPath());
			}
		} else { //계정 없음
			response.getWriter().println("<script>alert('아이디와 비밀번호를 확인해주세요.');"
					+ "history.go(-1);</script>");
		}
	}
}
