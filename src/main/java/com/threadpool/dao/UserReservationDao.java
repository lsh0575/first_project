package com.threadpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.*;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.air.*;


public class UserReservationDao {
	public int insert(ReservationDto dto) { // 유저_예약자 데이터 삽입 메소드 -> 유저_탑승객 데이터 삽입 메소드
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql 구문
		String sql = "insert into reservation (user_ID, reservation_name, telephone_number, "
				+ "email, adults, teenagers, ticket_type, total_payment, ip) values "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// return
		int primarykey = -1;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			pstmt.setString(1, dto.getUser_ID());
			pstmt.setString(2, dto.getReservation_name());
			pstmt.setString(3, dto.getTelephone_number());
			pstmt.setString(4, dto.getEmail());
			pstmt.setInt(5, dto.getAdults());
			pstmt.setInt(6, dto.getTeenagers());
			pstmt.setString(7, dto.getTicket_type());
			pstmt.setInt(8, dto.getTotal_payment());
			pstmt.setString(9, dto.getIp());
			pstmt.executeUpdate();
			
			rset = pstmt.getGeneratedKeys();
			if(rset.next()) {
				primarykey = rset.getInt(1);
			} else {
				throw new SQLException("x");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}	
		return primarykey;
	}
	
	public List<ReservationDto> read(ReservationDto dto) {
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql 구문
		String sql = "select * from reservation a left join one_way_reservation b on "
				+ "a.reservation_number = b.reservation_number left join "
				+ "aviation c on b.flight_code = c.flight_code where user_ID = ? "
				+ "order by reservation_date desc";
		
		// return
		List<ReservationDto> list = new ArrayList<>();

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_ID());
			rset = pstmt.executeQuery();
			while (rset.next()) {
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
						new AviationDto(
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
								)
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
	
	public List<ReservationDto> detail (AccountDto dto1,ReservationDto dto2) {
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql 구문
		String sql = "select * "
				+ "from reservation a "
				+ "left join passenger b on a.reservation_number = b.reservation_number "
				+ "left join one_way_reservation c on b.reservation_number = c.reservation_number "
				+ "left join aviation d on c.flight_code = d.flight_code "
				+ "where user_ID = ? and a.reservation_number = ?";
	
		// return
		List<ReservationDto> list = new ArrayList<>();

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto1.getId());
			pstmt.setInt(2, dto2.getReservation_number());
			rset = pstmt.executeQuery();
			while (rset.next()) {
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
								rset.getString("birth_date"),
								rset.getInt("reservation_number")),
						new AviationDto(
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
								)
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
	
	public int update(ReservationDto dto) {
		// DB 세팅
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// SQL 구문
		String sql = "update reservation set reservation_name = ?, telephone_number = ?, email = ? "
				+ "where user_ID = ? and reservation_number = ?";		
		
		// return
		int result = 0;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getReservation_name());
			pstmt.setString(2, dto.getTelephone_number());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getUser_ID());
			pstmt.setInt(5, dto.getReservation_number());
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