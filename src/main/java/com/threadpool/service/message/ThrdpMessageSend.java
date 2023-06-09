package com.threadpool.service.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.MessageDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.MessageDto;

public class ThrdpMessageSend implements ThrdpMessageAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sendresult = 0;
		//dto 세팅
		MessageDto dto = new MessageDto();
		//제목
		String title = "";
		//내용 받아오기
		dto.setMsg_context(request.getParameter("context"));
		//발신자 받아오기
		dto.setMsg_sender(((AccountDto)request.getSession().getAttribute("account")).getId());
		
		//수신자 그룹이 있다면 수신자 그룹 세팅
		String[] checklist = request.getParameterValues("role");
		if (checklist!=null) { //만약 그룹이라면
			List<Integer> list = new ArrayList<>();
			title += "[그룹발송] "; //제목에 그룹발송 적어주기
			for (String values : checklist) {
				list.add(Integer.parseInt(values));
			}
			dto.setRecieveGroup(list);
		} else { //수신자 그룹이 없다면 수신자 세팅
			dto.setMsg_reciever(request.getParameter("reciever"));
		}
		dto.setMsg_title(title + request.getParameter("title"));
		
		MessageDao dao = new MessageDao();
		dto = dao.writeMessage(dto);
		sendresult = dao.sendMessage(dto);
		if (sendresult!=0) {
			response.getWriter().print("<script>"
					+ "alert('메시지를 보냈습니다.');"
					+ "location.href='"+request.getContextPath()+"/list.msg';"
					+ "</script>");
		}else {
			response.getWriter().print("<script>"
					+ "alert('메시지 전송에 실패했습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
