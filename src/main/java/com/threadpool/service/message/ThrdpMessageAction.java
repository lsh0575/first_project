package com.threadpool.service.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ThrdpMessageAction {
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
