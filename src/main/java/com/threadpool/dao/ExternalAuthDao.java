package com.threadpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.threadpool.dbmanager.DBManager;
import com.threadpool.dto.AccountDto;

public class ExternalAuthDao {
	
	public boolean isAuthed(AccountDto dto) {
		boolean result = false;
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select * from thrdp_account_external_auth where id=?");
			pstmt.setString(1, dto.getId());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		return result;
	}
	
	public int newKakaoAuth(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("insert into thrdp_account_external_auth (id,kakao_auth,kakao_account) values (?,?,?)");
			pstmt.setString(1, dto.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, dto.getKakao_account());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateKakaoAuth(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("update thrdp_account_external_auth set kakao_auth=true,kakao_account=? where id=?");
			pstmt.setString(1, dto.getKakao_account());
			pstmt.setString(2, dto.getId());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int newNaverAuth(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("insert into thrdp_account_external_auth (id,naver_auth,naver_account) values (?,?,?)");
			pstmt.setString(1, dto.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, dto.getNaver_account());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return result;
	}
	public int updateNaverAuth(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("update thrdp_account_external_auth set naver_auth=true,naver_account=? where id=?");
			pstmt.setString(1, dto.getNaver_account());
			pstmt.setString(2, dto.getId());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
