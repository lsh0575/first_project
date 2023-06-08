package com.threadpool.service.hotel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.threadpool.dao.HotelDao;


public class Ajax_list implements HotelService {

    public Ajax_list() { super(); }

	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String where = "where ";
		if (!request.getParameter("grade").equals("default")) {
			where += "hgrade = " +request.getParameter("grade") + " and ";
		}
		where += "hprice <= " +request.getParameter("price");
		if (!request.getParameter("bed").equals("default")) {
			where += " and hbed = '" +request.getParameter("bed")+"'";
		}
		
		int pstartno = request.getParameter("pstartno") != null? Integer.parseInt(request.getParameter("pstartno")) : 0;
		HotelDao dao = new HotelDao();
		response.getWriter().print(new Gson().toJson(dao.listAjax(pstartno,where)));
		System.out.println(where);
		System.out.println(new Gson().toJson(dao.listAjax(pstartno,where)));
	}

}