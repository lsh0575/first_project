package com.threadpool.service.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountForcedOut implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDao dao = new AccountDao();
		int stautsUpdateResult = 0;
		int insertUserOut = 0;
		String[] idArr = request.getParameterValues("check");
		
		List<AccountDto> acclist = new ArrayList<>();
		//userStatus를 2번으로 업데이트하기
		for (String id : idArr) {
			AccountDto idDto = new AccountDto();
			idDto.setId(id);
			idDto.setStatus_id(2);
			acclist.add(idDto);
			stautsUpdateResult += dao.userStatusUpdate(idDto);
		}
		
		
		//out_users에 얹기(사유를 강퇴로 바꾸기)
		for (AccountDto dto : acclist) {
			insertUserOut += dao.userForceOut(dto);
		}
		System.out.println(stautsUpdateResult + "/" + insertUserOut);
		//어딘가 실패했을 경우
		if (idArr.length!=stautsUpdateResult || idArr.length!=insertUserOut) {
			response.getWriter().println("<script>"
					+ "alert('계정 상태 수정에 실패했습니다.');"
					+ "history.go(-1);</script>");
		} else { //무사히 성공했을 경우
			response.getWriter().println("<script>"
					+ "alert('계정 상태를 수정했습니다.');"
					+ "location.href='"
					+ request.getContextPath()+"/user_list.acc';</script>");
		}
		
	}
}
