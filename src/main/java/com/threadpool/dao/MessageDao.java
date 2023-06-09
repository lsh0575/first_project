package com.threadpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.threadpool.dbmanager.DBManager;
import com.threadpool.dto.AccountDto;
import com.threadpool.dto.MessageDto;

public class MessageDao {
	public MessageDto writeMessage(MessageDto dto) {
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("insert into thrdp_message (msg_title,msg_context,msg_sender) values (?,?,?)");
			pstmt.setString(1, dto.getMsg_title());
			pstmt.setString(2, dto.getMsg_context());
			pstmt.setString(3, dto.getMsg_sender());
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement("select msg_no from thrdp_message order by msg_no desc limit 0,1");
			ResultSet rset = pstmt.executeQuery();
			if (rset.next()) {
				dto.setMsg_no(rset.getInt("msg_no"));
			}
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if (conn!=null) {
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return dto;
	}
	
	public int sendMessage(MessageDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			if (dto.getRecieveGroup()!=null) { //수신자 그룹이 있다면
				List<String> idlist = new ArrayList<>();
				int successCount = 0;
				for (Integer i : dto.getRecieveGroup()) { //수신자그룹 전체를 돌면서 대상 사용자 수집
					pstmt = conn.prepareStatement("select id from thrdp_account where role_id=?");
					pstmt.setInt(1,i);
					ResultSet rset = pstmt.executeQuery();
					while (rset.next()) {
						idlist.add(rset.getString("id"));
					}
					rset.close();
					pstmt.close();
				}
				for (String id : idlist) { //수신자 아이디를 바꿔끼워가며 세팅
					dto.setMsg_reciever(id);
					successCount += recieverSetting(dto,conn);
				}
				if (successCount==idlist.size()) {
					result = 1;
				}
			} else { //수신자 그룹이 없다면
				result = recieverSetting(dto,conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if (conn!=null) {
				try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return result;
	}
	
	//수신자 설정. sendMessage에서 불러짐.
	public int recieverSetting(MessageDto dto, Connection conn) {
		int result = 0;
		PreparedStatement pstmt=null;
		try {
			//1. 메세지 수신자 테이블에 넣기
			pstmt = conn.prepareStatement("insert into thrdp_recieve (msg_reciever,msg_no) values (?,?)");
			pstmt.setString(1, dto.getMsg_reciever());
			pstmt.setInt(2, dto.getMsg_no());
			pstmt.executeUpdate();
			pstmt.close();
			
			//수신번호 받아오기
			pstmt = conn.prepareStatement("select msg_recieve_no from thrdp_recieve order by msg_recieve_no desc limit 0,1");
			ResultSet rset = pstmt.executeQuery();
			if (rset.next()) {
				dto.setMsg_recieve_no(rset.getInt("msg_recieve_no"));//수신번호 설정
			}
			rset.close();
			pstmt.close();
			
			//읽음 확인 테이블에 추가하기
			pstmt = conn.prepareStatement("insert into thrdp_msg_read (msg_recieve_no) values (?)");
			pstmt.setInt(1, dto.getMsg_recieve_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return result;
	}
	
	public int listCount(AccountDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select count(*) `cnt` from thrdp_recieve natural join thrdp_message natural join thrdp_msg_read where msg_reciever=?");
			pstmt.setString(1, dto.getId());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();}catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	public List<MessageDto> messageList(AccountDto dto, int pageStartNum, int onePageListCount){
		List<MessageDto> list = new ArrayList<>();
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select * from thrdp_recieve natural join thrdp_message natural join thrdp_msg_read where msg_reciever=? order by msg_send_time desc limit ?,?");
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, pageStartNum);
			pstmt.setInt(3, onePageListCount);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				MessageDto msg = new MessageDto();
				//작성자
				msg.setMsg_sender(rset.getString("msg_sender"));
				//제목
				msg.setMsg_title(rset.getString("msg_title"));
				//작성시간
				msg.setMsg_send_time(rset.getString("msg_send_time"));
				//읽음여부
				msg.setMsg_read(rset.getBoolean("msg_read"));
				//읽은시간
				msg.setMsg_read_time(rset.getString("msg_read_time"));
				//메시지 번호
				msg.setMsg_recieve_no(rset.getInt("msg_recieve_no"));
				list.add(msg);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();}catch(Exception e) {e.printStackTrace();}
			}
		}
		return list;
	}
	
	public MessageDto readMessage(MessageDto dto) {
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select * from thrdp_recieve natural join thrdp_message natural join thrdp_msg_read where msg_recieve_no=?");
			pstmt.setInt(1, dto.getMsg_recieve_no());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				dto.setMsg_title(rset.getString("msg_title"));
				dto.setMsg_context(rset.getString("msg_context"));
				dto.setMsg_sender(rset.getString("msg_sender"));
				dto.setMsg_send_time(rset.getString("msg_send_time"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {rset.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (pstmt!=null) {
				try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();}catch(Exception e) {e.printStackTrace();}
			}
		}
		return dto;
	}
	
	public boolean hasRead(MessageDto dto) {
		boolean result=false;
		Connection conn = null; PreparedStatement pstmt=null; ResultSet rset=null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select msg_read from thrdp_msg_read where msg_recieve_no=?");
			pstmt.setInt(1, dto.getMsg_recieve_no());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getBoolean("msg_read");
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();}catch(Exception e) {e.printStackTrace();}
			}
		}
		
		return result;
	}
	
	public int readCheck(MessageDto dto) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("update thrdp_msg_read set msg_read=true, msg_read_time=now() where msg_recieve_no=?");
			pstmt.setInt(1, dto.getMsg_recieve_no());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();}catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	public int deleteMessages(List<MessageDto> list) {
		int result = 0;
		Connection conn = null; PreparedStatement pstmt=null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("delete from thrdp_recieve where msg_recieve_no=?");
			for (MessageDto dto : list) {
				pstmt.setInt(1, dto.getMsg_recieve_no());
				result += pstmt.executeUpdate();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {pstmt.close();}catch(Exception e) {e.printStackTrace();}
			}
			if (conn!=null) {
				try {conn.close();}catch(Exception e) {e.printStackTrace();}
			}
		}
		return result;
	}
	
	public List<AccountDto> idList(AccountDto id){
		List<AccountDto> list = new ArrayList<>();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select id from thrdp_account where status_id=0 and id like ? order by id asc limit 0,5");
			pstmt.setString(1, id.getId()+"%");
			rset = pstmt.executeQuery();
			while (rset.next()) {
				AccountDto searchResult = new AccountDto();
				searchResult.setId(rset.getString("id"));
				list.add(searchResult);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try { rset.close(); } catch (Exception e) { e.printStackTrace(); }
			}
			if (pstmt!=null) {
				try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			}
			if (conn!=null) {
				try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
			}
		}
		return list;
	}
	
	public boolean idCheck(AccountDto id){
		boolean result = false;
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement("select id from thrdp_account where id=?");
			pstmt.setString(1, id.getId());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try { rset.close(); } catch (Exception e) { e.printStackTrace(); }
			}
			if (pstmt!=null) {
				try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			}
			if (conn!=null) {
				try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
			}
		}
		return result;
	}
}
