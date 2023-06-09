package com.threadpool.service.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountAction {
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
