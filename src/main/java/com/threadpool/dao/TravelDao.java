package com.threadpool.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.DBManager;
import com.threadpool.dto.TravelDto;

public class TravelDao {
	
	// Main 리스트화면
	public List<TravelDto> img(){
		List<TravelDto> main = new ArrayList<>();
		String sql = "select * from user_review left join images using(tno) order by tno desc limit 0,10";
		DBManager db = new DBManager();
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		
		// 필요한 필드만 가져오기.
		// 메인 리스트 화면에서 출력할 내용위주로 정리
		// 첫 이미지, 작성자, 제목, 내용, 업로드 날짜
		
		try {
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				main.add(new TravelDto(
						rset.getInt("tno"), 
						rset.getString("tname"),
						rset.getString("ttitle"), 
						rset.getString("tupload_date"),
						rset.getInt("thit"), 
						rset.getString("timages_1")
						));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(rset != null) {try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return main;
	}
	
	// 데이터 리스트 출력
	public List<TravelDto> listpage(int startno){
		List<TravelDto> list = new ArrayList<>();
		String sql = "select * from user_review left join images using(tno) order by tno desc limit ?,8";
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,startno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new TravelDto(
						rset.getInt("tno"), 
						rset.getString("tname"),
						rset.getString("ttitle"), 
						rset.getString("tupload_date"),
						rset.getInt("thit"), 
						rset.getString("timages_1")
						));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(rset!=null) {try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt!=null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn!=null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return list;
	}
	
	
	// 페이징 Dao
	public int listCnt() {
		int result = -1;
		String sql = "SELECT count(*) FROM user_review";
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("count(*)");
			}
			System.err.println("Dao listCnt ==> " + result);
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(rset!=null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(pstmt!=null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn!=null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		
		return result;
	}
	
	
	// 데이터 삽입
	public int insert(TravelDto dto) {
		
		int result = -1;
		
		String sql = "INSERT INTO user_review (tname,tpass,tcategory,ttitle,tcontent,tscore,tcost,tstart_date,tend_date,tip) values(?,?,?,?,?,?,?,?,?,?)";
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null; /* ResultSet rset=null; */
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTname());
			pstmt.setString(2, dto.getTpass());
			pstmt.setInt(3, dto.getTcategory());
			pstmt.setString(4, dto.getTtitle());
			pstmt.setString(5, dto.getTcontent());
			pstmt.setInt(6, dto.getTscore());
			pstmt.setString(7, dto.getTcost());
			pstmt.setString(8, dto.getTstart_date());
			pstmt.setString(9, dto.getTend_date());
			pstmt.setString(10, InetAddress.getLocalHost().getHostAddress());
			result = pstmt.executeUpdate();
		} catch (SQLException | UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn!=null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return result;
	}
	
	
	// 최신 글 넘버 받아오기
	public int recent_tno() {
		int result = -1;
		
		String sql = "select tno from user_review order by tno desc limit 1";
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("tno");
			}
		}catch (Exception e) { e.printStackTrace(); }
		return result;
	}
	
	
	// 이미지 삽입
	public int insert_img(TravelDto dto) {
		int result = -1;
		String sql ="INSERT INTO images (tno, timages_1, timages_2, timages_3) values (?,?,?,?)";
		DBManager db = new DBManager();
		Connection conn=null; PreparedStatement pstmt=null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recent_tno());
			pstmt.setString(2, dto.getTimages_1());
			pstmt.setString(3, dto.getTimages_2());
			pstmt.setString(4, dto.getTimages_3());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if(conn!=null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return result;
	}
	
	
	// 조회수 증가
	public int update_hit(TravelDto dto) {
		int result = -1;
		String sql = "UPDATE user_review SET thit=thit+1 where tno=?";
		DBManager db = new DBManager();
		Connection conn = null;
		PreparedStatement pstmt = null; /* ResultSet rset=null; */
		
		try {
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getTno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null){ try{pstmt.close(); }catch(Exception e){e.printStackTrace();}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){e.printStackTrace();}}
		}
		return result;
	}
	
	
	// 페이지 상세보기
	public TravelDto detail (TravelDto dto) {
		TravelDto result = null;
//		TravelDto result = null;
		String sql = "SELECT u.tno, u.tname, u.tpass, u.ttitle, u.tcontent, u.tcost, u.tscore, u.tstart_date, u.tend_date, c.tcategory_name, u.thit, timages_1, timages_2, timages_3 FROM user_review u JOIN images i ON u.tno = i.tno join category c on u.tcategory=c.tcategory WHERE u.tno = ?";
	
		DBManager db = new DBManager();
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getTno());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = new TravelDto(
						rset.getInt("u.tno"), 
						rset.getString("u.tname"), 
						rset.getString("u.tpass"),
						rset.getString("u.ttitle"), 
						rset.getString("u.tcontent"),
						rset.getString("u.tcost"), 
						rset.getInt("u.tscore"), 
						rset.getString("u.tstart_date"),
						rset.getString("u.tend_date"),
						rset.getString("c.tcategory_name"),
						rset.getInt("u.thit"), 
						rset.getString("timages_1"),
						rset.getString("timages_2"),
						rset.getString("timages_3")
						);
			}
			System.out.println(result);
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(rset!=null) {try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt!=null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn!=null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	
	// 작성자로 검색 시 페이지 상세보기
	public TravelDto search_tname (TravelDto dto) {
		TravelDto result = null;
		String sql = "SELECT u.tno, u.tname, u.tpass, u.ttitle, u.tcontent, u.tcost, u.tscore, u.tstart_date, u.tend_date, c.tcategory_name, u.thit, timages_1, timages_2, timages_3 FROM user_review u JOIN images i ON u.tno = i.tno join category c on u.tcategory=c.tcategory WHERE u.tname = ?";
	
		DBManager db = new DBManager();
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		System.out.println("dto.getTname() == > " + dto.getTname()); 
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTname());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = new TravelDto(
						rset.getInt("u.tno"), 
						rset.getString("u.tname"), 
						rset.getString("u.tpass"),
						rset.getString("u.ttitle"), 
						rset.getString("u.tcontent"),
						rset.getString("u.tcost"), 
						rset.getInt("u.tscore"), 
						rset.getString("u.tstart_date"),
						rset.getString("u.tend_date"),
						rset.getString("c.tcategory_name"),
						rset.getInt("u.thit"), 
						rset.getString("timages_1"),
						rset.getString("timages_2"),
						rset.getString("timages_3")
						);
			}
			System.out.println(result);
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(rset!=null) {try { rset.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt!=null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn!=null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	
	// 글 수정
	public int update(TravelDto dto) {
		int result = -1;
		String sql ="UPDATE user_review SET ttitle=?, tcontent=?, tcost=?, tstart_date=?, tend_date=? where tpass=? and tno=?";
		DBManager db = new DBManager();
		Connection conn=null; PreparedStatement pstmt=null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTtitle());
			pstmt.setString(2, dto.getTcontent());
			pstmt.setString(3, dto.getTcost());
			pstmt.setString(4, dto.getTstart_date());
			pstmt.setString(5, dto.getTend_date());
			pstmt.setString(6, dto.getTpass());
			pstmt.setInt(7, dto.getTno());
			
			result = pstmt.executeUpdate();
//			System.out.println("-----> " + result);
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(pstmt!=null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn!=null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	
	// 글 삭제
	public int delete(TravelDto dto) {
		int result = -1;
		String sql="DELETE FROM user_review where tno=? and tpass=?";
		DBManager db = new DBManager();
		Connection conn=null; PreparedStatement pstmt=null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getTno());
			pstmt.setString(2,dto.getTpass());
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(pstmt!=null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn!=null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	// admin 글 삭제
    public int delete_admin(TravelDto dto) {
        int result = -1;
        String sql = "DELETE FROM user_review WHERE tno=?";
        DBManager db = new DBManager();
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getTno());
            result = pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace();
        } finally {
            if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
        }
        return result;
    }
}
