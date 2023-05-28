package com.threadpool.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.DBManager;
import com.threadpool.dto.AccountDto;

public class AccountDao {
	public AccountDto login(AccountDto acc) {
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset=null;
		AccountDto result = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement("select * from thrdp_account left join out_user using(id) where id=? and pass=?");
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
				result.setOut_reason(rset.getString("out_reason"));
				result.setOut_date(rset.getString("out_date"));
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
			pstmt.setString(7, input.getPostnum());
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
	
	
	public int businessJoin(AccountDto bdto) {
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
		 	pstmt = conn.prepareStatement("select * from thrdp_account natural join thrdp_role left join thrdp_company_info using(id) left join out_user using(id) where id=?");
		 	pstmt.setString(1, dto.getId());
		 	rset = pstmt.executeQuery();
		 	if (rset.next()) {
		 		result.setId(rset.getString("id"));
		 		result.setPass(rset.getString("pass"));
		 		result.setName(rset.getString("name"));
		 		result.setBirth(rset.getString("birth"));
		 		result.setEmail(rset.getString("Email"));
		 		result.setPhonenum(rset.getString("phonenum"));
		 		result.setPostnum(rset.getString("postnum"));
		 		result.setAddress(rset.getString("address"));
		 		result.setDetail_address(rset.getString("detail_address"));
		 		result.setRole_id(rset.getInt("role_id"));
		 		result.setStatus_id(rset.getInt("status_id")) ;
		 		result.setCreate_date(rset.getString("create_date"));
		 		result.setCreate_ip(rset.getString("create_ip"));
		 		result.setPic(rset.getString("pic"));
		 		result.setRole_name(rset.getString("role_name"));
		 		result.setCompany_num(rset.getString("company_num"));
		 		result.setOut_reason(rset.getString("out_reason"));
		 		result.setOut_date(rset.getString("out_date"));
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
		 	pstmt.setString(5, dto.getPostnum());
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
	
	
	public boolean isIdDupl(String id){
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		boolean isDupl = false;
		try {
		 	conn = new DBManager().getConnection();
		 	pstmt = conn.prepareStatement("select count(*) cnt from thrdp_account where id=?");
		 	pstmt.setString(1,id);
		 	rset = pstmt.executeQuery();
		 	if (rset.next()) {
		 		if (rset.getInt("cnt") > 0) {
		 			isDupl = true;
		 		}
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
		return isDupl;
	}
	
	
	public int passEdit(AccountDto dto, String newpass) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		try {
		 	conn = new DBManager().getConnection();
		 	pstmt = conn.prepareStatement("update thrdp_account set pass=? where id=? and pass=?");
		 	pstmt.setString(1, newpass);
		 	pstmt.setString(2, dto.getId());
		 	pstmt.setString(3, dto.getPass());
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
	
	public int userStatusUpdateWithPass(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		try {
		 	conn = new DBManager().getConnection();
		 	pstmt = conn.prepareStatement("update thrdp_account set status_id=? where id=? and pass=?");
		 	pstmt.setInt(1, dto.getStatus_id());
		 	pstmt.setString(2,dto.getId());
		 	pstmt.setString(3,dto.getPass());
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
	
	public int userStatusUpdate(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		try {
		 	conn = new DBManager().getConnection();
		 	pstmt = conn.prepareStatement("update thrdp_account set status_id=? where id=?");
		 	pstmt.setInt(1, dto.getStatus_id());
		 	pstmt.setString(2,dto.getId());
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
	public int userOut(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		try {
		 	conn = new DBManager().getConnection();
		 	pstmt = conn.prepareStatement("insert into out_user (id, out_reason) values (?,?)");
		 	pstmt.setString(1, dto.getId());
		 	pstmt.setString(2,dto.getOut_reason());
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
	public int userForceOut(AccountDto dto) { //유저 강퇴
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		try {
		 	conn = new DBManager().getConnection();
		 	pstmt = conn.prepareStatement("select * from out_user where id=?");
		 	pstmt.setString(1, dto.getId());
		 	rset=pstmt.executeQuery();
		 	if (!rset.next()) { //나가지 않은 유저일 경우
		 		rset.close(); pstmt.close();
		 		pstmt = conn.prepareStatement("insert into out_user (id,out_reason) values (?,?) ");
		 		pstmt.setString(1, dto.getId());
		 		pstmt.setString(2, "관리자에 의해 강제로 비활성화된 계정입니다.");
		 		result = pstmt.executeUpdate();
		 	} else { //이미 스스로 나간 유저일경우
		 		rset.close(); pstmt.close();
		 		pstmt = conn.prepareStatement("update out_user set out_reason=? where id=?");
		 		pstmt.setString(1, "관리자에 의해 강제로 비활성화된 계정입니다.");
		 		pstmt.setString(2, dto.getId());
		 		result = pstmt.executeUpdate();
		 	}
		 	result = pstmt.executeUpdate();
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

	
	public int userDelete(List<AccountDto> list) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("delete from thrdp_account where id=?");
			for (AccountDto dto : list) {
				pstmt.setString(1, dto.getId());
				result += pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try { pstmt.close(); } catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try { conn.close(); } catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	
	public int userOutCancel(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("delete from out_user where id=?");
			pstmt.setString(1, dto.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try { pstmt.close(); } catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try { conn.close(); } catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	public List<AccountDto> idSearch(String type, String key) {
		List<AccountDto> result = new ArrayList<>();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select id from thrdp_account where "+type+"=?");
			pstmt.setString(1, key);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				AccountDto dto = new AccountDto();
				dto.setId(rset.getString("id"));
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try { rset.close(); } catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try { pstmt.close(); } catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try { conn.close(); } catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	public AccountDto passSearch(AccountDto id, String type, String key) {
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select pass from thrdp_account where id=? and "+type+"=?");
			pstmt.setString(1, id.getId());
			pstmt.setString(2, key);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				id.setPass(rset.getString("pass"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try { rset.close(); } catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try { pstmt.close(); } catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try { conn.close(); } catch(Exception e) {e.printStackTrace();}
			}
		}
		return id;
	}
}
