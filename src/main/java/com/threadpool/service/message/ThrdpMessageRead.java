package com.threadpool.service.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.MessageDao;
import com.threadpool.dto.MessageDto;

public class ThrdpMessageRead implements ThrdpMessageAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDto msg = new MessageDto();
		msg.setMsg_recieve_no(Integer.parseInt(request.getParameter("msgno")));
		MessageDao dao = new MessageDao();
		if (!dao.hasRead(msg)) {//읽은 적 없다면 
			dao.readCheck(msg); //읽기 체크해주기
		}
		msg = dao.readMessage(msg);
		request.setAttribute("msg",msg);
	}
}
