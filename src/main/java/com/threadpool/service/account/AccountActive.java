package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountActive implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int statusUpdateResult = 0;
		String[] checkedId = request.getParameterValues("check");
		AccountDao dao = new AccountDao();
		for (String id :checkedId) {
			AccountDto dto = new AccountDto();
			dto.setId(id);
			//기본생성자로 만들어지면 status_Id는 기본값 0이므로 set해줄 필요 없음. 
			dao.userOutCancel(dto);
			statusUpdateResult += dao.userStatusUpdate(dto);
		}
		if (statusUpdateResult != checkedId.length) {
			response.getWriter().println("<script>"
					+ "alert('계정 활성화에 실패한 경우가 있습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		} else {
			response.getWriter().println("<script>"
					+ "alert('계정을 다시 활성화했습니다!');"
					+ "location.href='"+request.getContextPath()+"/user_list.acc';"
					+ "</script>");
		}
	}
}
