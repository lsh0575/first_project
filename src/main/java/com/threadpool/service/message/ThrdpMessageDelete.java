package com.threadpool.service.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.MessageDao;
import com.threadpool.dto.MessageDto;

public class ThrdpMessageDelete implements ThrdpMessageAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MessageDto> deleteList = new ArrayList<>();
		
		// 만약 체크박스로 선택해서 지울 경우
		if (request.getParameterValues("check")!=null) {
			for (String val : request.getParameterValues("check")) {
				MessageDto dto = new MessageDto();
				dto.setMsg_recieve_no(Integer.parseInt(val));
				deleteList.add(dto);
			}
		} else { //해당 쪽지에서 지울 경우
			MessageDto dto = new MessageDto();
			dto.setMsg_recieve_no(Integer.parseInt(request.getParameter("msgno")));
			deleteList.add(dto);
		}
		MessageDao dao = new MessageDao();
		if (dao.deleteMessages(deleteList) == deleteList.size()) { //전부 지우는데 성공한 경우
			response.getWriter().println("<script>"
					+ "alert('메시지를 삭제했습니다.');"
					+ "location.href='"+request.getContextPath()+"/list.msg';</script>");
		} else { //전부 지우는데에 실패한 경우
			response.getWriter().println("<script>"
					+ "alert('메시지를 삭제하는데에 실패했습니다.');"
					+ "history.go(-1);</script>");
		}
		
	}
}
