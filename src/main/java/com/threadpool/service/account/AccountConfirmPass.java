package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountConfirmPass implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = request.getParameter("pass");
		String passRe = request.getParameter("pass_re");
		boolean confirm = false;
		if (pass.equals(passRe)) {
			confirm = true;
		}
		String result = "<span id='pass_check' style='color:"+(confirm?"green' data-check='checked'>비밀번호 재입력이 일치합니다.":"red'>비밀번호를 다시 확인해주세요.")+"</span>";
		
		response.getWriter().println(result);
	}
}
