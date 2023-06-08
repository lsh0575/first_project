package com.threadpool.service.travel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TravelService {
	   public void exec(HttpServletRequest request, HttpServletResponse response) 
			   throws ServletException, IOException;
}
