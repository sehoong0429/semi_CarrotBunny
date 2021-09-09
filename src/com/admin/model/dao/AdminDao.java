package com.admin.model.dao;

import static com.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.board.model.vo.Board;
import com.member.model.vo.Member;

public class AdminDao {

	private Properties props = new Properties();
	public AdminDao() {
		String path = AdminDao.class.getResource("/sql/admin_sql.properties").getPath();
		try {
			props.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList();
		try {
			pstmt= conn.prepareStatement(props.getProperty("selectMemberAll"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs= pstmt.executeQuery();
			while(rs.next()) { //rs -> 완성된 쿼리문이 들어감. 
				Member m = new Member();
				m.setmemberNum(rs.getInt("member_num"));
				m.setUserId(rs.getString("member_id"));
				m.setPassword(rs.getString("member_pwd"));
				m.setUserName(rs.getString("mem_name"));
				m.setEmail(rs.getString("mem_email"));
				m.setPhone(rs.getString("mem_phone"));
				m.setIsAgree(rs.getInt("mem_agree"));
				m.setIsDelete(rs.getInt("mem_delete"));
				m.setIsAdmin(rs.getInt("mem_admin"));
				m.setenrollDate(rs.getDate("enroll_date")); 
				list.add(m); //첫번쨰 멤버추가, 2번쨰 행을 ~~~ 추가 반복. 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list; //멤버리스트 최종반환 
	}
	
	
	//전체자료 가져오기.
	public int selectMemberCount (Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectMemberCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	public Member selectMemberDetail(Connection conn, int no) {

		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(props.getProperty("selectMemberDetail")); //쿼리문을 가져오겠다 
			pstmt.setInt(1, no); //set으로 채워넣음. 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setmemberNum(rs.getInt("member_num"));
				m.setUserName(rs.getString("mem_name"));
				m.setPhone(rs.getString("mem_phone"));
				m.setenrollDate(rs.getDate("enroll_date")); 
			}
			//다 받아오는게 좋은 이유 :
			//selectMemberDetail 쿼리문을 다른곳에서 쓸때 다시만들필요없이 필요한것만 골라서 편하게 사용 가능 ! ! 
	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}

	
	public int deleteNotice(Connection conn, int memberNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(props.getProperty("deleteMember"));
			pstmt.setInt(1, memberNum);
			result=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(props.getProperty("updateMember"));
			pstmt.setInt(1, m.getIsDelete());
			pstmt.setInt(2, m.getmemberNum());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	
	public List<Member> searchMember(Connection conn, String searchType, String keyword , int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList();
		String sql=props.getProperty("searchMember");
//		switch(searchType) {
//			case "userId" : sql ="searchMemberId"; break;
//			case "userName" : sql ="searchMemberName"; break;
//		}
		try {
			pstmt=conn.prepareStatement(sql.replace("@", searchType));
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Member m=new Member();
				m.setmemberNum(rs.getInt("member_num"));
				m.setUserId(rs.getString("member_id"));
				m.setPassword(rs.getString("member_pwd"));
				m.setUserName(rs.getString("mem_name"));
				m.setEmail(rs.getString("mem_email"));
				m.setPhone(rs.getString("mem_phone"));
				m.setIsAgree(rs.getInt("mem_agree"));
				m.setIsDelete(rs.getInt("mem_delete"));
				m.setIsAdmin(rs.getInt("mem_admin"));
				m.setenrollDate(rs.getDate("enroll_date")); 
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list; 
	}
	

	
	//CONNECTION객체를 보내주는 곳 ! 
	public int searchMemberCount(Connection conn, String searchType, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = props.getProperty("searchMemberCount");
		try {
			pstmt= conn.prepareStatement(sql.replace("@", searchType));
			pstmt.setString(1,keyword);
			rs=pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}

	
	public List<Board> selectBoardList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt= conn.prepareStatement(props.getProperty("selectBoardList"));
			pstmt.setInt(1, 0);
			pstmt.setInt(2, (cPage-1)*numPerPage + 1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				b.setBoardNumber(rs.getInt("b_num"));
				b.setBoardTitle(rs.getString("b_title"));
				b.setBoardWriter(rs.getString("b_writer"));
				b.setBoardContent(rs.getString("b_content"));
				b.setBoardPrice(rs.getInt("b_price"));
				b.setBoardAmount(rs.getInt("b_amount"));
				b.setBoardIsSell(rs.getInt("b_sell"));
				b.setBoardLike(rs.getString("b_like"));
				b.setBoardIsNego(rs.getInt("b_nego"));
				b.setBoardIsDelete(rs.getInt("b_delete"));
				b.setBoardFilePath(rs.getString("b_original_filename"));
				b.setBoardReFilePath(rs.getString("b_renamed_filename"));
				b.setBoardDate(rs.getDate("b_date"));
				b.setBoardReadCount(rs.getInt("b_readcount"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectBoardList (Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("selectAlivBoardCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int searchBoardCount(Connection conn, String searchType, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = props.getProperty("searchBoardCount");
		try {
			pstmt = conn.prepareStatement(sql.replace("@", searchType));
			pstmt.setString(1,keyword);
			rs=pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Board> searchBoard(Connection conn, int cPage, int numPerPage, String searchType, String keyword){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		String sql = props.getProperty("searchBoard");
		try {
			pstmt = conn.prepareStatement(sql.replace("@", searchType));
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage + 1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				b.setBoardNumber(rs.getInt("b_num"));
				b.setBoardTitle(rs.getString("b_title"));
				b.setBoardWriter(rs.getString("b_writer"));
				b.setBoardContent(rs.getString("b_content"));
				b.setBoardPrice(rs.getInt("b_price"));
				b.setBoardAmount(rs.getInt("b_amount"));
				b.setBoardIsSell(rs.getInt("b_sell"));
				b.setBoardLike(rs.getString("b_like"));
				b.setBoardIsNego(rs.getInt("b_nego"));
				b.setBoardIsDelete(rs.getInt("b_delete"));
				b.setBoardFilePath(rs.getString("b_original_filename"));
				b.setBoardReFilePath(rs.getString("b_renamed_filename"));
				b.setBoardDate(rs.getDate("b_date"));
				b.setBoardReadCount(rs.getInt("b_readcount"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	public List<Board> SellBoardList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt= conn.prepareStatement(props.getProperty("SellBoardList"));
			pstmt.setInt(1, 1);
			pstmt.setInt(2, (cPage-1)*numPerPage + 1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				b.setBoardNumber(rs.getInt("b_num"));
				b.setBoardTitle(rs.getString("b_title"));
				b.setBoardWriter(rs.getString("b_writer"));
				b.setBoardContent(rs.getString("b_content"));
				b.setBoardPrice(rs.getInt("b_price"));
				b.setBoardAmount(rs.getInt("b_amount"));
				b.setBoardIsSell(rs.getInt("b_sell"));
				b.setBoardLike(rs.getString("b_like"));
				b.setBoardIsNego(rs.getInt("b_nego"));
				b.setBoardIsDelete(rs.getInt("b_delete"));
				b.setBoardFilePath(rs.getString("b_original_filename"));
				b.setBoardReFilePath(rs.getString("b_renamed_filename"));
				b.setBoardDate(rs.getDate("b_date"));
				b.setBoardReadCount(rs.getInt("b_readcount"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	public int SellBoardListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("SellBoardListCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	public List<Board> SoldBoardList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt= conn.prepareStatement(props.getProperty("SoldBoardList"));
			pstmt.setInt(1, 0);
			pstmt.setInt(2, (cPage-1)*numPerPage + 1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				b.setBoardNumber(rs.getInt("b_num"));
				b.setBoardTitle(rs.getString("b_title"));
				b.setBoardWriter(rs.getString("b_writer"));
				b.setBoardContent(rs.getString("b_content"));
				b.setBoardPrice(rs.getInt("b_price"));
				b.setBoardAmount(rs.getInt("b_amount"));
				b.setBoardIsSell(rs.getInt("b_sell"));
				b.setBoardLike(rs.getString("b_like"));
				b.setBoardIsNego(rs.getInt("b_nego"));
				b.setBoardIsDelete(rs.getInt("b_delete"));
				b.setBoardFilePath(rs.getString("b_original_filename"));
				b.setBoardReFilePath(rs.getString("b_renamed_filename"));
				b.setBoardDate(rs.getDate("b_date"));
				b.setBoardReadCount(rs.getInt("b_readcount"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int SoldBoardListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(props.getProperty("SoldBoardListCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
}

