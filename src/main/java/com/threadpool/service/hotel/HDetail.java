package com.threadpool.service.hotel;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.HotelDao;
import com.threadpool.dto.hotel.HotelDto;
import com.threadpool.dto.hotel.HotelDtoProd;

public class HDetail implements HotelService {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HotelDao dao = new HotelDao();
		HotelDtoProd dto = new HotelDtoProd();
		dto.setHno( Integer.parseInt(request.getParameter("hno")));
		// 1. 전체데이터 담기 - dao.detailHotel(dto)
		// 2. 1에서 이미지만 뽑기
		// 3. 어레이리스트에 담기
		HotelDto result = dao.detailHotel(dto);
		ArrayList<String> list = new ArrayList<>(); 
			list.add(result.getHimg().getImg1());
			list.add(result.getHimg().getImg2());
			list.add(result.getHimg().getImg3());
			list.add(result.getHimg().getImg4());
		
		request.setAttribute("dto", result);
		request.setAttribute("imglist", list);
	}
}
