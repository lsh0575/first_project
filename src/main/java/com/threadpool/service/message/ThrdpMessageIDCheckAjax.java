package com.threadpool.service.message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.threadpool.dao.MessageDao;
import com.threadpool.dto.AccountDto;

public class ThrdpMessageIDCheckAjax implements ThrdpMessageAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDto dto = new AccountDto();
		dto.setId(request.getParameter("id"));
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		
		JsonObject check = new JsonObject();
		check.addProperty("check", new MessageDao().idCheck(dto));
		out.print(gson.toJson(check));
	}
}
