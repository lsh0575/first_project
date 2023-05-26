package com.threadpool.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.DBManager;
import com.threadpool.dto.AccountBlockedDto;
import com.threadpool.dto.AccountBusinessDto;
import com.threadpool.dto.AccountDto;

public class AccountDao {
	public AccountDto login(AccountDto acc) {
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset=null;
		AccountDto result = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select id,pass,name,role_id,status_id,pic from thrdp_account where id=? and pass=?");
			pstmt.setString(1, acc.getId());
			pstmt.setString(2, acc.getPass());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = new AccountDto();
				result.setId(rset.getString("id"));
				result.setPass(rset.getString("pass"));
				result.setName(rset.getString("name"));
				result.setRole_id(rset.getInt("role_id"));
				result.setStatus_id(rset.getInt("status_id"));
				result.setPic(rset.getString("pic"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		
		return result;
	}
	
	
	public AccountBlockedDto blockedReason(AccountBlockedDto acc) {
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset=null;
		AccountBlockedDto result = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from out_user where id=?");
			pstmt.setString(1, acc.getId());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = new AccountBlockedDto(rset.getString("id"),
										rset.getString("out_reason"),
										rset.getInt("status_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		
		return result;
	}
	
	
	public int userJoin(AccountDto input) {
		int result = 0;
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt=null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("insert into thrdp_account "
					+ "(id, pass, name, birth, email, "
					+ "phonenum, postnum, address, detail_address, role_id, create_ip) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, input.getId());
			pstmt.setString(2, input.getPass());
			pstmt.setString(3, input.getName());
			pstmt.setString(4, input.getBirth());
			pstmt.setString(5, input.getEmail());
			pstmt.setString(6, input.getPhonenum());
			pstmt.setInt(7, input.getPostnum());
			pstmt.setString(8, input.getAddress());
			pstmt.setString(9, input.getDetail_address());
			pstmt.setInt(10, input.getRole_id());
			pstmt.setString(11, InetAddress.getLocalHost().getHostAddress());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	
	public int businessJoin(AccountBusinessDto bdto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		DBManager db = new DBManager();
		try {
		 	conn = db.getConnection();
		 	pstmt = conn.prepareStatement("insert into thrdp_company_info values (?,?)");
		 	pstmt.setString(1, bdto.getId());
		 	pstmt.setString(2, bdto.getCompany_num());
		 	result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return result; 
	}
	
	
	public AccountDto detail(AccountDto dto) {
		AccountDto result = new AccountDto();
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset= null;
		try {
		 	conn = db.getConnection();
		 	pstmt = conn.prepareStatement("select * from thrdp_account natural join thrdp_role where id=?");
		 	pstmt.setString(1, dto.getId());
		 	rset = pstmt.executeQuery();
		 	if (rset.next()) {
		 		result.setId(rset.getString("id"));
		 		result.setPass(rset.getString("pass"));
		 		result.setName(rset.getString("name"));
		 		result.setBirth(rset.getString("birth"));
		 		result.setEmail(rset.getString("Email"));
		 		result.setPhonenum(rset.getString("phonenum"));
		 		result.setPostnum(rset.getInt("postnum"));
		 		result.setAddress(rset.getString("address"));
		 		result.setDetail_address(rset.getString("detail_address"));
		 		result.setRole_id(rset.getInt("role_id"));
		 		result.setStatus_id(rset.getInt("status_id")) ;
		 		result.setCreate_date(rset.getString("create_date"));
		 		result.setCreate_ip(rset.getString("create_ip"));
		 		result.setPic(rset.getString("pic"));
		 		result.setRole_name(rset.getString("role_name"));
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			if (rset!=null) {
				try {rset.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	
	public AccountBusinessDto businessDetail(AccountDto dto) {
		AccountBusinessDto businessResult = null;
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset= null;
		try {
		 	conn = db.getConnection();
		 	pstmt = conn.prepareStatement("select * from thrdp_company_info natural join thrdp_role where id=?");
		 	pstmt.setString(1, dto.getId());
		 	rset = pstmt.executeQuery();
		 	if (rset.next()) {
			 	businessResult = new AccountBusinessDto();
		 		businessResult.setId(rset.getString("id"));
		 		businessResult.setCompany_num(rset.getString("company_num"));
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			if (rset!=null) {
				try {rset.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return businessResult;
	}
	
	
	public int listCount(String where) {
		int count=0;
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select count(id) `c`  from thrdp_account `acc` natural join thrdp_role `role` natural join user_status `status` left join thrdp_company_info c using(id) "+where);
			rset=pstmt.executeQuery();
			if (rset.next()) {
				count=rset.getInt("c");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return count;
	}
	
	
	public List<AccountDto> userList(String where, int pStartNo, int onePageListCount) {
		List<AccountDto> list = new ArrayList<>();
		Connection conn=null; PreparedStatement pstmt=null; ResultSet rset=null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select * from thrdp_account `acc` natural join thrdp_role `role` natural join user_status `status` left join thrdp_company_info c using(id) "+where+" order by create_date desc limit ?,?");
			
			pstmt.setInt(1, pStartNo);
			pstmt.setInt(2, onePageListCount);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				AccountDto dto = new AccountDto();
				dto.setRole_name(rset.getString("role_name"));
				dto.setId(rset.getString("id"));
				dto.setName(rset.getString("name"));
				dto.setEmail(rset.getString("email"));
				dto.setPhonenum(rset.getString("phonenum"));
				dto.setCompany_num(rset.getString("company_num"));
				dto.setCreate_date(rset.getString("create_date"));
				dto.setStatus_name(rset.getString("status_name"));
				
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return list;
	}

	
	public int userEdit(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		DBManager db = new DBManager();
		try {
		 	conn = db.getConnection();
		 	pstmt = conn.prepareStatement("update thrdp_account set name=?,birth=?,email=?,phonenum=?,postnum=?,address=?,detail_address=? where id=? and pass=?");
		 	pstmt.setString(1, dto.getName());
		 	pstmt.setString(2, dto.getBirth());
		 	pstmt.setString(3, dto.getEmail());
		 	pstmt.setString(4, dto.getPhonenum());
		 	pstmt.setInt(5, dto.getPostnum());
		 	pstmt.setString(6, dto.getAddress());
		 	pstmt.setString(7, dto.getDetail_address());
		 	pstmt.setString(8, dto.getId());
		 	pstmt.setString(9, dto.getPass());
		 	result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return result; 
	}
	
	
	public List<AccountDto> getIdList(){
		List<AccountDto> list = new ArrayList<>();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		try {
		 	conn = new DBManager().getConnection();
		 	pstmt = conn.prepareStatement("select id from thrdp_account");
		 	rset = pstmt.executeQuery();
		 	while (rset.next()) {
		 		AccountDto dto = new AccountDto();
		 		dto.setId(rset.getString("id"));
		 		list.add(dto);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();} catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
		return list;
	}
}
