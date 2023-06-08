package com.threadpool.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.DBManager;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.hotel.HotelDto;
import com.threadpool.dto.hotel.HotelDtoImg;
import com.threadpool.dto.hotel.HotelDtoOption;
import com.threadpool.dto.hotel.HotelDtoProd;
import com.threadpool.dto.hotel.HotelDtoReserve;
import com.threadpool.dto.hotel.HotelReserveList;

public class HotelDao {
	
	public List<HotelDto> listAjax(int page , String where){
		List<HotelDto> list = new ArrayList<>();
		DBManager db = new DBManager();
		
		String sql = "select hno,hname,htype,img1,hprice,hnation,checkin,checkout,hcontent from hotel_prod a left join hotel_img b using(hno)" + where + " order by hno desc limit ?,4";
		Connection conn = null ; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			rset = pstmt.executeQuery(); //표
			while(rset.next()) {
				HotelDtoProd prod = new HotelDtoProd();
				prod.setHno(rset.getInt("hno"));
				prod.setHname(rset.getString("hname"));
				prod.setHtype(rset.getString("htype"));
				prod.setHnation(rset.getString("hnation"));
				prod.setHprice(rset.getInt("hprice"));
				prod.setCheckin(rset.getString("checkin"));
				prod.setCheckout(rset.getString("checkout"));
				prod.setHcontent(rset.getString("hcontent"));
				
				HotelDtoImg img = new HotelDtoImg();
				img.setHno(rset.getInt("hno"));
				img.setImg1(rset.getString("img1"));
				
				list.add(new HotelDto(prod,img));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			if(rset != null) { try {rset.close();}catch(Exception e) {e.printStackTrace();} }
			if(pstmt != null) { try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
			if(conn != null) { try {conn.close();}catch(Exception e) {e.printStackTrace();} }
		}
		return list;
	}
	
	
	
	public int listCnt() {
		int total =-1;
		String sql = "select count(*) from hotel_prod";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					//total = rset.getInt("count(*)");
					total = rset.getInt(1); // 구하는 순서 1,2,3 count(*) 한개밖에 없어서...
				}
		} catch (Exception e) {e.printStackTrace();
		} finally {
			if(rset != null) { try {rset.close();}catch(Exception e) {e.printStackTrace();} }
			if(pstmt != null) { try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
			if(conn != null) { try {conn.close();}catch(Exception e) {e.printStackTrace();} }
		}
		return total;
	}
	
	
	public List<HotelDtoProd> HotelListpage(int startno){
		List<HotelDtoProd> list = new ArrayList<>();
		
		DBManager db = new DBManager();
		String sql = "select * from hotel_prod order by hno desc limit  ?,10";
		Connection conn = null ; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startno);
			rset = pstmt.executeQuery(); //표
			while(rset.next()) {
				list.add(new HotelDtoProd(rset.getInt("hno"),rset.getString("hname"),rset.getString("htype")));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			if(rset != null) { try {rset.close();}catch(Exception e) {e.printStackTrace();} }
			if(pstmt != null) { try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
			if(conn != null) { try {conn.close();}catch(Exception e) {e.printStackTrace();} }
		}
		return list;
	}
	
	public List<HotelDtoReserve> ReserveListpage(int startno , AccountDto dto){
		List<HotelDtoReserve> list = new ArrayList<>();
		
		DBManager db = new DBManager();
		String sql = "select * from hotel_prod a left join reserve b using(hno) where id=? order by hno desc limit  ?,10";
		Connection conn = null ; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, startno);
			rset = pstmt.executeQuery(); //표
			while(rset.next()) {
				list.add(new HotelDtoReserve(rset.getInt("hno"),rset.getString("id"),rset.getString("rip"),rset.getString("reservetime"),rset.getString("hname")));
				
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			if(rset != null) { try {rset.close();}catch(Exception e) {e.printStackTrace();} }
			if(pstmt != null) { try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
			if(conn != null) { try {conn.close();}catch(Exception e) {e.printStackTrace();} }
		}
		return list;
	}
	
	
	public List<HotelDtoReserve> userListpage(int startno){
		List<HotelDtoReserve> list = new ArrayList<>();
		
		DBManager db = new DBManager();
		String sql = "select * from hotel_prod a left join reserve b using(hno) order by hno desc limit  ?,10";
		Connection conn = null ; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startno);
			rset = pstmt.executeQuery(); //표
			while(rset.next()) {
				list.add(new HotelDtoReserve(rset.getInt("hno"),rset.getString("id"),rset.getString("rip"),rset.getString("reservetime"),rset.getString("hname"),rset.getString("hnation")));
				
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			if(rset != null) { try {rset.close();}catch(Exception e) {e.printStackTrace();} }
			if(pstmt != null) { try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
			if(conn != null) { try {conn.close();}catch(Exception e) {e.printStackTrace();} }
		}
		return list;
	}
	
	
		public List<HotelDto> listHotel(int page){
			List<HotelDto> list = new ArrayList<>();
			
			DBManager db = new DBManager();
			String sql = "select hno,hname,htype,img1,hprice,hnation,checkin,checkout,hcontent from hotel_prod a left join hotel_img b using(hno) order by hno desc limit ?,10";
			Connection conn = null ; PreparedStatement pstmt = null; ResultSet rset = null;
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, page);
				rset = pstmt.executeQuery(); //표
				while(rset.next()) {
					HotelDtoProd prod = new HotelDtoProd();
					prod.setHno(rset.getInt("hno"));
					prod.setHname(rset.getString("hname"));
					prod.setHtype(rset.getString("htype"));
					prod.setHnation(rset.getString("hnation"));
					prod.setHprice(rset.getInt("hprice"));
					prod.setCheckin(rset.getString("checkin"));
					prod.setCheckout(rset.getString("checkout"));
					prod.setHcontent(rset.getString("hcontent"));
					
					HotelDtoImg img = new HotelDtoImg();
					img.setHno(rset.getInt("hno"));
					img.setImg1(rset.getString("img1"));
					
					list.add(new HotelDto(prod,img));
				}
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				if(rset != null) { try {rset.close();}catch(Exception e) {e.printStackTrace();} }
				if(pstmt != null) { try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
				if(conn != null) { try {conn.close();}catch(Exception e) {e.printStackTrace();} }
			}
			return list;
		}
			
		//hotel 부모테이블 create
		public int createProd(HotelDtoProd dto)  {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null ;
			PreparedStatement pstmt = null; 
			String sql = "insert into hotel_prod (hname,htype,hprice,hgrade,hbed,hcnt,hcontent,hip,checkin,checkout,hnation)"
					+ " value (?,?,?,?,?,?,?,?,?,?,?)";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getHname());
				pstmt.setString(2, dto.getHtype());
				pstmt.setInt(3, dto.getHprice());
				pstmt.setInt(4, dto.getHgrade());
				pstmt.setString(5, dto.getHbed());
				pstmt.setInt(6, dto.getHcnt());
				pstmt.setString(7, dto.getHcontent());
				pstmt.setString(8, InetAddress.getLocalHost().getHostAddress());
				pstmt.setString(9, dto.getCheckin());
				pstmt.setString(10, dto.getCheckout());
				pstmt.setString(11, dto.getHnation());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) { e.printStackTrace();
			}finally {
				if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
				if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
			}
			return result;
		} 
		
		//자식테이블들의 hno를 찍어주기위한 method  **1:1관계의 데이터베이스만 가능하다... 
		public int createNum() {	
			DBManager db = new DBManager();
			Connection conn = null ;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = -1;
			String sql = "select max(hno) from hotel_prod";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getInt("max(hno)");
				}
			}catch(Exception e) { e.printStackTrace();
			}finally {
				if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
				if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
			}
			return result;
		}
		
		//hotel 자식테이블 option의 create
		public int createOption(HotelDtoOption dto)  {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null ;
			PreparedStatement pstmt = null; 
			String sql = "insert into hotel_option (hno,smoke,ref,wifi,tv,tub,airc,wash)"
					+ " value (?,?,?,?,?,?,?,?)";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  createNum());
				pstmt.setBoolean(2, dto.isSmoke());
				pstmt.setBoolean(3, dto.isRef());
				pstmt.setBoolean(4, dto.isWifi());
				pstmt.setBoolean(5, dto.isTv());
				pstmt.setBoolean(6, dto.isTub());
				pstmt.setBoolean(7, dto.isAirc());
				pstmt.setBoolean(8, dto.isWash());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) { e.printStackTrace();
			}finally {
				if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
				if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
			}
			return result;
		} 
		
		//hotel의 자식테이블 img 의 create
		public int createImg(HotelDtoImg dto)  {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null ;
			PreparedStatement pstmt = null; 
			String sql = "insert into hotel_img (hno,img1,img2,img3,img4)"
					+ " value (?,?,?,?,?)";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, createNum());
				pstmt.setString(2, dto.getImg1());
				pstmt.setString(3, dto.getImg2());
				pstmt.setString(4, dto.getImg3());
				pstmt.setString(5, dto.getImg4());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) { e.printStackTrace();
			}finally {
				if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
				if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
			}
			return result;
		} 
		
		
		//hotel의 부모테이블 hotel_prod의 update
		public int updateProd ( HotelDtoProd dto ) {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt = null;
			String sql = "update hotel_prod set hname=?,htype=?,hprice=?"
					+ ",hgrade=?,hbed=?,hcnt=?,hcontent=?,"
					+ "hip=?,checkin=?,checkout=?,hnation=? "
					+ "where hno=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getHname());
				pstmt.setString(2, dto.getHtype());
				pstmt.setInt(3, dto.getHprice());
				pstmt.setInt(4, dto.getHgrade());
				pstmt.setString(5, dto.getHbed());
				pstmt.setInt(6, dto.getHcnt());
				pstmt.setString(7, dto.getHcontent());
				pstmt.setString(8, InetAddress.getLocalHost().getHostAddress());
				pstmt.setString(9, dto.getCheckin());
				pstmt.setString(10, dto.getCheckout());
				pstmt.setString(11, dto.getHnation());
				pstmt.setInt(12, dto.getHno());
				result = pstmt.executeUpdate();
			}catch(Exception e) { e.printStackTrace(); 
			}finally {
				if(pstmt != null) { try { pstmt.close(); }catch(Exception e){ e.printStackTrace(); } }
				if(conn != null) { try { conn.close(); }catch(Exception e){ e.printStackTrace(); } }
			}
			return result;
		}
		
		//hotel의 자식테이블 hotel_option의 update
		public int updateOption ( HotelDtoOption dto ) {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt = null;
			String sql = "update hotel_option set smoke=?,ref=?,wifi=?,tv=?,tub=?,airc=?,wash=? where hno=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setBoolean(1, dto.isSmoke());
				pstmt.setBoolean(2, dto.isRef());
				pstmt.setBoolean(3, dto.isWifi());
				pstmt.setBoolean(4, dto.isTv());
				pstmt.setBoolean(5, dto.isTub());
				pstmt.setBoolean(6, dto.isAirc());
				pstmt.setBoolean(7, dto.isWash());
				pstmt.setInt(8, dto.getHno());
				result = pstmt.executeUpdate();
			} catch (Exception e) { e.printStackTrace();
			}finally {
				if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
				if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
			}
			return result;
		}
		
		//hotel의 자식테이블 hotel_img의 update
		public int updateImg ( HotelDtoImg dto ) {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt = null;
			String sql = "update hotel_img set img1=?,img2=?,img3=?,img4=? where hno=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getImg1());
				pstmt.setString(2, dto.getImg2());
				pstmt.setString(3, dto.getImg3());
				pstmt.setString(4, dto.getImg4());
				pstmt.setInt(5, dto.getHno());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) { e.printStackTrace();
			}finally {
				if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
				if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
			}
			return result;
		} 
		
		public int deleteHotel ( HotelDtoProd dto ) {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt = null; 
			String sql = "delete from hotel_prod where hno=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getHno());
				result = pstmt.executeUpdate();
			}catch(Exception e) { e.printStackTrace(); 
			}finally {
				if(pstmt != null) {try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
				if(conn != null) {try {conn.close();}catch(Exception e) {e.printStackTrace();} }
			}
			return result;
		}
		
		public HotelDto detailHotel(HotelDtoProd dto) {
			HotelDto result = new HotelDto();
			Connection conn= null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			String sql = "select * from hotel_prod a left join hotel_option b using(hno) left join  hotel_img c using(hno) where hno=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getHno());
				rset  = pstmt.executeQuery();
				while(rset.next()) {
					HotelDtoProd prod = new HotelDtoProd();
					prod.setHno(rset.getInt("hno"));
					prod.setHname(rset.getString("hname"));
					prod.setHtype(rset.getString("htype"));
					prod.setHprice(rset.getInt("hprice"));
					prod.setHgrade(rset.getInt("hgrade"));
					prod.setHbed(rset.getString("hbed"));
					prod.setHcnt(rset.getInt("hcnt"));
					prod.setHcontent(rset.getString("hcontent"));
					prod.setHfix(rset.getString("hfix"));
					prod.setHip(rset.getString("hip"));
					prod.setCheckin(rset.getString("checkin"));
					prod.setCheckout(rset.getString("checkout"));
					prod.setHnation(rset.getString("hnation"));
					
					HotelDtoOption option = new HotelDtoOption();
					option.setHno(rset.getInt("hno"));
					option.setSmoke(rset.getBoolean("smoke"));
					option.setRef(rset.getBoolean("smoke"));
					option.setWifi(rset.getBoolean("wifi"));
					option.setTv(rset.getBoolean("tv"));
					option.setTub(rset.getBoolean("tub"));
					option.setAirc(rset.getBoolean("airc"));
					option.setWash(rset.getBoolean("wash"));
					
					HotelDtoImg img = new HotelDtoImg();
					img.setHno(rset.getInt("hno"));
					img.setImg1(rset.getString("img1"));
					img.setImg2(rset.getString("img2"));
					img.setImg3(rset.getString("img3"));
					img.setImg4(rset.getString("img4"));
					img.setIfix(rset.getString("ifix"));
					
					result =new HotelDto(prod,option,img);
				}
		}catch (Exception e) { e.printStackTrace();
		}finally { 
			if(rset != null) { try{ rset.close(); }catch(Exception e){ e.printStackTrace(); }  }
			if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
			if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
		}
			return result;
		}
		
		// 여기서부터 유저 파트
		
		//유저의 예약목록 확인
		public List<HotelReserveList> reserveList() {
			List<HotelReserveList> list = new ArrayList<>();
			DBManager db = new DBManager();
			String sql = "select * from hotel_prod a left join reserve b using(hno) left join thrdp_account c using(id);";
			Connection conn = null ; PreparedStatement pstmt = null; ResultSet rset = null;
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery(); //표
				while(rset.next()) {
					list.add(new HotelReserveList(rset.getInt("hno"),rset.getString("hname"),rset.getString("id")));
				}
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				if(rset != null) { try {rset.close();}catch(Exception e) {e.printStackTrace();} }
				if(pstmt != null) { try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
				if(conn != null) { try {conn.close();}catch(Exception e) {e.printStackTrace();} }
			}
			return list;
		}
		
		//유저의 예약
		public int createReserve(HotelDtoReserve dto)  {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null ;
			PreparedStatement pstmt = null; 
			String sql = "insert into reserve (hno,id,rip)"
					+ " value (?,?,?)";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getHno());
				pstmt.setString(2, dto.getId());	
				pstmt.setString(3, InetAddress.getLocalHost().getHostAddress());
	
				result = pstmt.executeUpdate();
			} catch (Exception e) { e.printStackTrace();
			}finally {
				if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
				if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
			}
			return result;
		} 
		
		//유저의 예약 상세보기
		public HotelDto detailReserve(HotelDtoProd dto) {
			HotelDto result = new HotelDto();
			Connection conn= null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			String sql = "select * from hotel_prod a left join reserve b using(hno) left join thrdp_account c using(id) left join  hotel_option d using(hno) left join hotel_img e using(hno) where hno=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getHno());
				rset  = pstmt.executeQuery();
				while(rset.next()) {
					HotelDtoProd prod = new HotelDtoProd();
					prod.setHno(rset.getInt("hno"));
					prod.setHname(rset.getString("hname"));
					prod.setHtype(rset.getString("htype"));
					prod.setHprice(rset.getInt("hprice"));
					prod.setHgrade(rset.getInt("hgrade"));
					prod.setHbed(rset.getString("hbed"));
					prod.setHcnt(rset.getInt("hcnt"));
					prod.setHcontent(rset.getString("hcontent"));
					prod.setHfix(rset.getString("hfix"));
					prod.setHip(rset.getString("hip"));
					prod.setCheckin(rset.getString("checkin"));
					prod.setCheckout(rset.getString("checkout"));
					prod.setHnation(rset.getString("hnation"));
					
					HotelDtoOption option = new HotelDtoOption();
					option.setHno(rset.getInt("hno"));
					option.setSmoke(rset.getBoolean("smoke"));
					option.setRef(rset.getBoolean("smoke"));
					option.setWifi(rset.getBoolean("wifi"));
					option.setTv(rset.getBoolean("tv"));
					option.setTub(rset.getBoolean("tub"));
					option.setAirc(rset.getBoolean("airc"));
					option.setWash(rset.getBoolean("wash"));
					
					HotelDtoImg img = new HotelDtoImg();
					img.setHno(rset.getInt("hno"));
					img.setImg1(rset.getString("img1"));
					img.setImg2(rset.getString("img2"));
					img.setImg3(rset.getString("img3"));
					img.setImg4(rset.getString("img4"));
					img.setIfix(rset.getString("ifix"));
					
					HotelDtoReserve reserve = new HotelDtoReserve();
					reserve.setHno(rset.getInt("hno"));
					reserve.setId(rset.getString("id"));
					reserve.setIp(rset.getString("rip"));
					reserve.setReserveTime(rset.getString("reservetime"));
					
					AccountDto account = new AccountDto();
					account.setName(rset.getString("name"));
					account.setPhonenum(rset.getString("phonenum"));
									
					result =new HotelDto(prod,option,img,reserve,account);
				}
		}catch (Exception e) { e.printStackTrace();
		}finally { 
			if(rset != null) { try{ rset.close(); }catch(Exception e){ e.printStackTrace(); }  }
			if(pstmt != null) { try{ pstmt.close(); }catch(Exception e){ e.printStackTrace(); }  }
			if(conn != null) { try{ conn.close(); }catch(Exception e){ e.printStackTrace(); }  }
		}
			return result;
		}
		
		//유저의 예약 취소
		public int deleteReserve ( HotelDtoReserve dto ) {
			int result = -1;
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt = null; 
			String sql = "delete from reserve where hno=?";
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getHno());
				result = pstmt.executeUpdate();
			}catch(Exception e) { e.printStackTrace(); 
			}finally {
				if(pstmt != null) {try {pstmt.close();}catch(Exception e) {e.printStackTrace();} }
				if(conn != null) {try {conn.close();}catch(Exception e) {e.printStackTrace();} }
			}
			return result;
		}		
			
}