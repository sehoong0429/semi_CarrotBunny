package com.notice.model.dao;

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

import com.notice.model.vo.Notice;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	//sql 연결 
	public NoticeDao() {
		String path=NoticeDao.class.getResource("/sql/notice_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public List<Notice> noticeList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <Notice> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectNotice"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rs.getInt("n_num"));
				n.setNoticeTitle(rs.getString("n_title"));
				n.setNoticeContent(rs.getString("n_content"));
				n.setNoticeDate(rs.getDate("n_date"));
//				n.setNoticeWriter(rs.getString("n_writer"));
				list.add(n);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	//공지사항 등록 
	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insertNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
//			pstmt.setDate(3, n.getNoticeDate());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	//상세보기 
	public Notice selectNoticeDetail(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Notice n = null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectNoticeDetail"));
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				n = new Notice();
				n.setNoticeNo(rs.getInt("n_num"));
				n.setNoticeTitle(rs.getString("n_title"));
				n.setNoticeContent(rs.getString("n_content"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return n;
	}
	
	
	//수정 
	public int noticeUpdate(Connection conn, Notice n) {
		PreparedStatement pstmt=null;
		int result =0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("noticeUpdate"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	//삭제 
	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("deleteNotice"));
			pstmt.setInt(1, noticeNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
}
