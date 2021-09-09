package com.board.model.dao;

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
import com.board.model.vo.Comment;
import com.member.model.vo.Member;

public class BoardDao {

	private Properties prop = new Properties();
	
	public BoardDao() {
		String filepath = BoardDao.class.getResource("/sql/board_sql.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectBoardList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectBoardList"));
			pstmt.setInt(1, (cPage-1)*numPerPage + 1);
			pstmt.setInt(2,  cPage*numPerPage);
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
	
	public List<Board> selectAliveBoardList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectAliveBoardList"));
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
	
	public List<Board> selectSoldBoardList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectSoldBoardList"));
			pstmt.setInt(1, (cPage-1)*numPerPage + 1);
			pstmt.setInt(2, cPage*numPerPage);
			
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
	
	public List<Board> selectUserBoardList(Connection conn, int cPage, int numPerPage, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectUserBoardList"));
			pstmt.setInt(1, 0);
			pstmt.setString(2, m.getUserId());
			pstmt.setInt(3, (cPage-1)*numPerPage + 1);
			pstmt.setInt(4, cPage*numPerPage);
			
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
	
	public List<Board> selectAllUserBoardList(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectAllUserBoardList"));
			pstmt.setString(1, m.getUserId());
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
	
	public List<Board> searchBoardList(Connection conn, int cPage, int numPerPage, String searchType, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		String sql=prop.getProperty("searchBoardList");
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
	
	public List<Board> searchSoldBoardList(Connection conn, int cPage, int numPerPage, String searchType, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		String sql=prop.getProperty("searchSoldBoardList");
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
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectBoardCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int selectAliveBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectAliveBoardCount"));
			pstmt.setInt(1, 0);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int selectSoldBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectSoldBoardCount"));
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int selectUserBoardCount(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectUserBoardCount"));
			pstmt.setInt(1, 0);
			pstmt.setString(2, m.getUserId());
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
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
		String sql = prop.getProperty("searchBoardCount");
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
	public int searchSoldBoardCount(Connection conn, String searchType, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("searchSoldBoardCount");
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
	// b_num == num 인 board를 select해온다.
	public Board selectBoard(Connection conn, int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board b = null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectBoard"));
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b = new Board();
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
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertBoard"));
			pstmt.setString(1,b.getBoardTitle());
			pstmt.setString(2,b.getBoardWriter());
			pstmt.setString(3,b.getBoardContent());
			pstmt.setInt(4,b.getBoardPrice());
			pstmt.setInt(5,b.getBoardAmount());
			pstmt.setInt(6,b.getBoardIsNego());
			pstmt.setString(7,b.getOriginalFileName());
			pstmt.setString(8, b.getRenamedFileName());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateBoard"));
			pstmt.setString(1,b.getBoardTitle());
			pstmt.setString(2,b.getBoardWriter());
			pstmt.setString(3,b.getBoardContent());
			pstmt.setInt(4,b.getBoardPrice());
			pstmt.setInt(5,b.getBoardAmount());
			pstmt.setInt(6,b.getBoardIsNego());
			pstmt.setString(7,b.getOriginalFileName());
			pstmt.setString(8, b.getRenamedFileName());
			pstmt.setInt(9,b.getBoardNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("deleteBoard"));
			pstmt.setInt(1,b.getBoardIsDelete());
			pstmt.setInt(2,b.getBoardNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteComment(Connection conn, Comment cm) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("deleteComment"));
			pstmt.setInt(1,cm.getCommentNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;

	}
	
	
	
	
	public int updateLikeBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateLikeBoard"));
			pstmt.setString(1,b.getBoardLike());
			pstmt.setInt(2,b.getBoardNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int sellBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("sellBoard"));
			pstmt.setInt(1,b.getBoardIsSell());
			pstmt.setInt(2,b.getBoardNumber());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Comment> selectComment(Connection conn, int boardNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectComment"));
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment c = new Comment();
				c.setCommentNumber(rs.getInt("c_num"));
				c.setCommentWriter(rs.getString("c_writer"));
				c.setCommentContent(rs.getString("c_content"));
				c.setBoardNumber(rs.getInt("b_num"));
				c.setRefNumber(rs.getInt("c_refnum"));
				c.setCommentLevel(rs.getInt("c_level"));
				c.setCommentDate(rs.getDate("c_date"));
				list.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int insertComment(Connection conn, Comment c) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertComment"));
			pstmt.setString(1,c.getCommentWriter());
			pstmt.setString(2,c.getCommentContent());
			pstmt.setInt(3,c.getBoardNumber());
			pstmt.setString(4,c.getRefNumber()==0?null:String.valueOf(c.getRefNumber()));
			pstmt.setInt(5,c.getCommentLevel());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
