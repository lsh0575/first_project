package com.threadpool.service.message;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.threadpool.dao.MessageDao;
import com.threadpool.dto.AccountDto;

public class ThrdpMessageIDListAjax implements ThrdpMessageAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto dto = new AccountDto();
		dto.setId(request.getParameter("id"));
		List<AccountDto> list =  new MessageDao().idList(dto);

		response.getWriter().println(new Gson().toJson(list));
	}
}
