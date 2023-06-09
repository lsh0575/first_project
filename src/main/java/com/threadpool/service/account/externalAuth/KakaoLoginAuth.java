package com.threadpool.service.account.externalAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.threadpool.dao.ExternalAuthDao;
import com.threadpool.dto.AccountDto;
import com.threadpool.service.account.AccountAction;

public class KakaoLoginAuth implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		JsonParser parser = new JsonParser();
		final String REST_API_KEY = "6322b3212c52ed5df0b4129d63b7a6d3"; 
		final String REDIRECT_URI = "http://localhost:8080/threadpool_webproject01/kakao_auth.acc";
		
		String access_token = null;
		
		String kakao_id = null;
		
		//access_token, id_token 받아오기
		try {
			String param = "";
			param += "grant_type=authorization_code";
			param += "&client_id="+REST_API_KEY;
			param += "&redirect_uri="+REDIRECT_URI;
			param += "&code="+request.getParameter("code");
			
			URL url = new URL("https://kauth.kakao.com/oauth/token?"+param);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			BufferedReader reader = null;
			if (conn.getResponseCode()==200) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ( (line = reader.readLine()) != null ) {
				buffer.append(line);
			}
			
			JsonObject jobj = (JsonObject)parser.parse(buffer.toString());
			access_token = jobj.get("access_token").getAsString();
			
			reader.close();
			conn.disconnect();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		////유저정보 받아오기
		try {
			URL url = new URL("https://kapi.kakao.com/v2/user/me");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.addRequestProperty("Authorization", "Bearer "+access_token);
			conn.addRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			
			BufferedReader reader = null;
			if (conn.getResponseCode()==200) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ( (line = reader.readLine()) != null ) {
				buffer.append(line);
			}
			JsonObject jobj = (JsonObject) parser.parse(buffer.toString());
			System.out.println(buffer.toString());
			kakao_id = jobj.get("id").getAsString();
			System.out.println(kakao_id);
			reader.close();
			conn.disconnect();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExternalAuthDao dao = new ExternalAuthDao();
		AccountDto dto = (AccountDto)request.getSession().getAttribute("account");
		dto.setKakao_account(kakao_id);
		
		int result = 0;
		if (dao.isAuthed(dto)) {
			result = dao.updateKakaoAuth(dto);
		} else {
			result = dao.newKakaoAuth(dto);
		}
		
		if(result>0) {
			AccountDto acc = (AccountDto) request.getSession().getAttribute("account");
			response.getWriter().println("<script>alert('정상 인증 되었습니다.');"
					+ "location.href='"+request.getContextPath()+"/user.acc?id="+acc.getId()+"'; </script>" );
		} else {
			AccountDto acc = (AccountDto) request.getSession().getAttribute("account");
			response.getWriter().println("<script>alert('인증 실패했습니다.');"
					+ "location.href='"+request.getContextPath()+"/user.acc?id="+acc.getId()+"'; </script>" );
		}
		
	}
}
