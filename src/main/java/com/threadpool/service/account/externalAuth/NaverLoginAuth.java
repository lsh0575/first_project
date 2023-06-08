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

public class NaverLoginAuth implements AccountAction{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId = "b8rOsHSrE677qq5RQEWZ";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "JOkjRgdqsu";//애플리케이션 클라이언트 시크릿값";
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        
        String naver_id = null;
        
        String access_token = null;
        JsonParser parser = new JsonParser();
        
		try {
			
		    
			String param ="grant_type=authorization_code";
			param +=  "&client_id=" + clientId;
			param += "&state=" + state;
			param += "&client_secret="+clientSecret;
			param += "&code="+code;
			param += "&state"+state;
			URL url = new URL("https://nid.naver.com/oauth2.0/token?" + param);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			
			BufferedReader reader = null;
			if (conn.getResponseCode()==200) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ( (line=reader.readLine())!=null) {
				buffer.append(line);
			}
			
			JsonObject obj = (JsonObject)parser.parse(buffer.toString());
			access_token = obj.get("access_token").getAsString();
			
			reader.close();
			conn.disconnect();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			URL url = new URL("https://openapi.naver.com/v1/nid/me");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Authorization", "Bearer "+access_token);
			
			BufferedReader reader = null;
			if (conn.getResponseCode()==200) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ( (line=reader.readLine())!=null) {
				buffer.append(line);
			}
			
			JsonObject jobj = (JsonObject) parser.parse(buffer.toString());
			JsonObject naverResponse = jobj.get("response").getAsJsonObject();
			naver_id = naverResponse.get("id").getAsString();
			
			ExternalAuthDao dao = new ExternalAuthDao();
			AccountDto dto = (AccountDto)request.getSession().getAttribute("account");
			dto.setKakao_account(naver_id);
			
			int result = 0;
			if (dao.isAuthed(dto)) {
				result = dao.updateNaverAuth(dto);
			} else {
				result = dao.newNaverAuth(dto);
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
