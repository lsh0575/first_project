package com.threadpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.threadpool.dbmanager.DBManager;
import com.threadpool.dto.air.*;

public class UserAviationDao {
	public JsonArray start_pointjoin(AviationDto dto) {
	    // DB 세팅
	    DBManager db = new DBManager();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    // SQL문
	    String sql = "select distinct start_point from aviation where start_point like CONCAT('%', ?, '%')";

	    // return
	    JsonArray list = new JsonArray();
	    JsonObject j1 = new JsonObject();

	    try {
	        conn = db.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, dto.getStart_point());
	        rset = pstmt.executeQuery();
	        while (rset.next()) {
	            j1 = new JsonObject();
	            j1.addProperty("start_point", rset.getString("start_point"));
	            list.add(j1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	    }
	    return list;
	}
	
	public JsonArray end_pointjoin(AviationDto dto) {
	    // DB 세팅
		DBManager db = new DBManager();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    // SQL문
	    String sql = "select distinct end_point from aviation where end_point like CONCAT('%', ?, '%')";

	    // return
	    JsonArray list = new JsonArray();
	    JsonObject j1 = new JsonObject();

	    try {
	        conn = db.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, dto.getEnd_point());
	        rset = pstmt.executeQuery();
	        while (rset.next()) {
	            j1 = new JsonObject();
	            j1.addProperty("end_point", rset.getString("end_point"));
	            list.add(j1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	    }
	    return list;
	}
	
	
	public List<AviationDto> read(String where) { // 유저_항공 데이터 조회 메소드 (편도, 왕복 조건)
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL문
		String sql = "select aviation.*, count(passenger.passenger_number) as check_seats from aviation "
				+ "left join one_way_reservation on aviation.flight_code = one_way_reservation.flight_code "
				+ "left join passenger on one_way_reservation.reservation_number = passenger.reservation_number "
				+ where;

		// return
	List<AviationDto> list = new ArrayList<>();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				AviationDto dto = new AviationDto();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Timestamp one = rset.getTimestamp("departure_time");
				String departure_time = df.format(one);
				Timestamp two = rset.getTimestamp("arrival_time");
				String arrival_time = df.format(two);
				Timestamp three = rset.getTimestamp("creation_date");
				String creation_date = df.format(three);
				dto.setFlight_code(rset.getInt("flight_code"));
				dto.setFlight(rset.getString("flight"));
				dto.setAirline(rset.getString("airline"));
				dto.setClassification_of_flights(rset.getString("classification_of_flights"));
				dto.setDeparture_time(departure_time);
				dto.setArrival_time(arrival_time);
				dto.setNumber_of_seats(rset.getInt("number_of_seats"));
				dto.setSeat_class(rset.getString("seat_class"));
				dto.setFreight_charge(rset.getInt("freight_charge"));
				dto.setStart_point(rset.getString("start_point"));
				dto.setEnd_point(rset.getString("end_point"));
				dto.setAdministrator_ID(rset.getString("administrator_ID"));
				dto.setCreation_date(creation_date);
				dto.setIp(rset.getString("ip"));
				list.add(dto);
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
	
	
	public List<AviationDto> detail(String where) { // 유저_항공 예약 편도/왕복 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL 구문
		String sql = "select * from aviation " + where;
		
		// return
		List<AviationDto> list = new ArrayList<>();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AviationDto dto = new AviationDto();
				dto.setFlight_code(rset.getInt("flight_code"));
				dto.setFlight(rset.getString("flight"));
				dto.setAirline(rset.getString("airline"));
				dto.setClassification_of_flights(rset.getString("classification_of_flights"));
				dto.setDeparture_time(rset.getString("departure_time"));
				dto.setArrival_time(rset.getString("arrival_time"));
				dto.setNumber_of_seats(rset.getInt("number_of_seats"));
				dto.setSeat_class(rset.getString("seat_class"));
				dto.setFreight_charge(rset.getInt("freight_charge"));
				dto.setStart_point(rset.getString("start_point"));
				dto.setEnd_point(rset.getString("end_point"));
				dto.setAdministrator_ID(rset.getString("administrator_ID"));
				dto.setCreation_date(rset.getString("creation_date"));
				dto.setIp(rset.getString("ip"));
				list.add(dto);
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
}