package com.threadpool.service.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.MessageListPage;

public class ThrdpMessagePagingAjax implements ThrdpMessageAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDto dto = (AccountDto)request.getSession().getAttribute("account");
		int pageStartNum = 0;
		if (request.getParameter("pageStartNum")!=null) { //만약 param이 없다면
			pageStartNum = Integer.parseInt(request.getParameter("pageStartNum"));
		}
		if (request.getParameterValues("check")!=null) {
			new ThrdpMessageDelete().exec(request, response);
		}
		
		
		MessageListPage paging = new MessageListPage(pageStartNum,dto);
		
		
		Gson gson = new Gson();
		response.getWriter().println(gson.toJson(paging));
		System.out.println(gson.toJson(paging));
	}
}
