package com.threadpool.dao.air;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.*;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.*;


public class AdminReservationDao {
	public int listCnt(String where) { // 어드민_페이징 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL구문
		String sql = "select count(reservation_number) from reservation " + where;

		// return
		int count = 0;
		
		try {
			conn= db.getConnection();
			pstmt= conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(rset != null) {try { rset.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			if(conn != null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); }}
		}
		return count;
	}
	
	public List<ReservationDto> read(String where, int pStartNo, int onePageListCount) { // 예약 데이터 전체 조회 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL 구문
		String sql = "select * from reservation " + where
				+ " order by reservation_date desc limit ?,?";
		
		// return
		List<ReservationDto> list = new ArrayList<>();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pStartNo);
			pstmt.setInt(2, onePageListCount);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new ReservationDto(
						rset.getInt("reservation_number"),
						rset.getString("user_ID"),
						rset.getString("reservation_name"),
						rset.getString("telephone_number"),
						rset.getString("email"),
						rset.getInt("adults"),
						rset.getInt("teenagers"),
						rset.getString("ticket_type"),
						rset.getInt("total_payment"),
						rset.getString("reservation_date"),
						rset.getNString("ip")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return list;
	}
	
	public List<ReservationDto> detail(ReservationDto dto) { // 특정 예약 데이터 조회 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL 구문
		String sql = "select * from reservation a natural join passenger b where reservation_number=?";
		
		// return
		List<ReservationDto> list = new ArrayList<>();

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getReservation_number());
			rset = pstmt.executeQuery();
			while(rset.next()) {					
				list.add(new ReservationDto(
						rset.getInt("reservation_number"),
						rset.getString("user_ID"),
						rset.getString("reservation_name"),
						rset.getString("telephone_number"),
						rset.getString("email"),
						rset.getInt("adults"),
						rset.getInt("teenagers"),
						rset.getString("ticket_type"),
						rset.getInt("total_payment"),
						rset.getString("reservation_date"),
						rset.getString("ip"),
						new PassengerDto(
								rset.getInt("passenger_number"),
								rset.getString("passenger_korean_name"),
								rset.getString("passenger_english_name"),
								rset.getString("gender"),
								rset.getString("birth_date"))
						));
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return list;
	}
	
	public int update(ReservationDto dto) { // 예약 데이터 수정 메소드
		// DB세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// SQL 구문
		String sql = "update reservation set "
				+ "reservation_name = ?, telephone_number = ?, email = ? "
				+ "where reservation_number = ? ";		
		// return
		int result = -1;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getReservation_name());
			pstmt.setString(2, dto.getTelephone_number());
			pstmt.setString(3, dto.getEmail());
			pstmt.setInt(4, dto.getReservation_number());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return result;
	}
	
	public int delete(ReservationDto dto, AccountDto dto2) { // 예약 데이터 삭제 메소드
 		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// SQL 구문
		String sql = "delete from reservation "
		           + "where reservation_number = ? "
		           + "and exists (select * from thrdp_account where id = ? and pass = ?)";
		
		// return
		int result = -1;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getReservation_number());
			pstmt.setString(2, dto2.getId());
			pstmt.setString(3, dto2.getPass());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return result;
	}
}