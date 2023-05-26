package com.threadpool.service.account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threadpool.dao.AccountDao;
import com.threadpool.dto.AccountDto;

public class AccountDuplCheck implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDao dao = new AccountDao();
		boolean dupl = false;
		String id = request.getParameter("id");
		List<AccountDto> list = dao.getIdList();
		for (AccountDto dto : list) {
			if (dto.getId().equals(id)) {
				dupl = true;
				break;
			}
		}
		String result = "<span style='color:"+(dupl?"red'>아이디가 중복되었습니다.":"green'>사용 가능한 아이디입니다.")+"</span>";
		result += "<script>\\n";
		result += "if ("+dupl+"){\\n";
		result += "alert('아이디가 중복되었습니다.');\\n";
		result += "$('#id_form_input').focus();\\n";
		result += "return false;\\n";
		result += "</script>";
		
		response.getWriter().println(result);
	}
}
