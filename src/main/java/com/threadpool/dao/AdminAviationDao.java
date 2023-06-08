package com.threadpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.*;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.*;

public class AdminAviationDao {
	
	public int listCnt(String where) {
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL구문
		String sql = "select count(flight_code) from aviation " + where;

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
	
	public List<AviationDto> read(String where, int pStartNo, int onePageListCount) {
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL 구문
		String sql = "select * from aviation " + where
				+ " order by departure_time desc limit ?,?";
		
		// return
		List<AviationDto> list = new ArrayList<>();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pStartNo);
			pstmt.setInt(2, onePageListCount);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new AviationDto(
						rset.getInt("flight_code"),
						rset.getString("flight"),
						rset.getString("airline"),
						rset.getString("classification_of_flights"),
						rset.getString("departure_time"),
						rset.getString("arrival_time"),
						rset.getInt("number_of_seats"),
						rset.getString("seat_class"),
						rset.getInt("freight_charge"),
						rset.getString("start_point"),
						rset.getString("end_point"),
						rset.getString("administrator_ID"),
						rset.getString("creation_date"),
						rset.getString("ip")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return list;
	}
		
	public int insert(AviationDto dto) { // 항공 데이터 삽입 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// SQL 구문
		String sql = "insert into aviation (flight, airline, classification_of_flights, departure_time, arrival_time, "
				+ "number_of_seats, seat_class, freight_charge, start_point, end_point, administrator_ID, ip) values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// return
		int result = -1; 
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFlight());
			pstmt.setString(2, dto.getAirline());
			pstmt.setString(3, dto.getClassification_of_flights());
			pstmt.setString(4, dto.getDeparture_time());
			pstmt.setString(5, dto.getArrival_time());
			pstmt.setInt(6, dto.getNumber_of_seats());
			pstmt.setString(7, dto.getSeat_class());
			pstmt.setInt(8, dto.getFreight_charge());
			pstmt.setString(9, dto.getStart_point());
			pstmt.setString(10, dto.getEnd_point());
			pstmt.setString(11, dto.getAdministrator_ID());
			pstmt.setString(12, dto.getIp());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return result;
	}
	

	
	public AviationDto detail(AviationDto dto) { // 항공 특정 데이터 조회 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL 구문
		String sql = "select * from aviation where flight_code = ?";
		
		// return
		AviationDto data = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getFlight_code());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				data = new AviationDto(
						rset.getInt("flight_code"),
						rset.getString("flight"),
						rset.getString("airline"),
						rset.getString("classification_of_flights"),
						rset.getString("departure_time"),
						rset.getString("arrival_time"),
						rset.getInt("number_of_seats"),
						rset.getString("seat_class"),
						rset.getInt("freight_charge"),
						rset.getString("start_point"),
						rset.getString("end_point"),
						rset.getString("administrator_ID"),
						rset.getString("creation_date"),
						rset.getString("ip")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return data;
	}
	
	public int update(AviationDto dto) { // 항공 데이터 수정 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// SQL 구문
		String sql = "update aviation set airline=?, departure_time=?, arrival_time=?, freight_charge=? "
				+ "where flight_code=? and flight=?";
		
		// return
		int result = -1;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAirline());
			pstmt.setString(2, dto.getDeparture_time());
			pstmt.setString(3, dto.getArrival_time());
			pstmt.setInt(4, dto.getFreight_charge());
			pstmt.setInt(5, dto.getFlight_code());
			pstmt.setString(6, dto.getFlight());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return result;
	}
	
	public int delete(AviationDto dto, AccountDto dto2){ // 항공 데이터 삭제
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		// SQL 구문
		String sql = "delete from aviation "
				+ "where flight_code = ? and exists "
				+ "(select * from thrdp_account where id = ? and pass = ?)";
		// return
		int result = -1;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getFlight_code());
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