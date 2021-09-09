package com.love.model.dao;

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

import com.love.model.vo.Love;
import com.member.model.vo.Member;

public class LoveDao {
	private Properties prop = new Properties();

	public LoveDao() {
		String filepath = LoveDao.class.getResource("/sql/love_sql.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	// Love 테이블의 모든 row를 가져온다.
	public List<Love> selectLoveList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Love> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectLoveList"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Love lo = new Love();
				lo.setBoardNumber(rs.getInt("b_num"));
				lo.setUserId(rs.getString("m_id"));
				list.add(lo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	// m_id가 좋아하는 row만 가져온다.
	public List<Love> selectUserLoveList(Connection conn, int cPage, int numPerPage, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Love> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectUserLoveList"));
			pstmt.setString(1, m.getUserId());
			pstmt.setInt(2, (cPage-1)*numPerPage + 1);
			pstmt.setInt(3,  cPage*numPerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Love lo = new Love();
				lo.setBoardNumber(rs.getInt("b_num"));
				lo.setUserId(rs.getString("m_id"));
				list.add(lo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectLoveCount(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectLoveCount"));
			pstmt.setString(1, m.getUserId());
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
	// m_id와 b_num 둘 다 일치하는 Love를 가져온다.
	public int findLove(Connection conn, Member m, int boardNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("findLove"));
			pstmt.setString(1, m.getUserId());
			pstmt.setInt(2, boardNumber);
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
	// Love를 table에 넣어준다.
	public int insertLove(Connection conn, Love love) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertLove"));
			pstmt.setInt(1, love.getBoardNumber());
			pstmt.setString(2, love.getUserId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	// m_id와 b_num이 일치하는 love 테이블 row를 삭제
	public int deleteLove(Connection conn, Love love) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("deleteLove"));
			pstmt.setInt(1, love.getBoardNumber());
			pstmt.setString(2, love.getUserId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
