package com.threadpool.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dto.AccountDto;
import com.threadpool.service.travel.TMain;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Main() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}
	protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new TMain().exec(request, response);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
