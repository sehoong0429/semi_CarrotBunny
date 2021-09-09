package com.qna.model.dao;

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
import com.notice.model.vo.Notice;
import com.qna.model.vo.Qna;

import static com.common.JDBCTemplate.close;

public class QnaDao {
	
	private Properties prop=new Properties();
	
	//db와 연결
	public QnaDao() {
		String path=QnaDao.class.getResource("/sql/qna_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//QNA데이터들 불러오기  ADMIN
	public List<Qna> qnaList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Qna> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectQnaList"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Qna q = new Qna();
				q.setQnaNo(rs.getInt("q_num"));
				q.setQnaTitle(rs.getString("q_title"));
				q.setQnaWriter(rs.getString("q_writer"));
				q.setQnaContent(rs.getString("q_content"));
				q.setQnaAnswer(rs.getString("q_answer"));
				q.setQnaDate(rs.getDate("q_date"));
				list.add(q);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int QnaListCount (Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectQnaCount"));
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
	
	
	
	//QNA데이터들 불러오기   USER
		public List<Qna> qnaListUser(Connection conn, String id){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Qna> list = new ArrayList();
			try {
				pstmt = conn.prepareStatement(prop.getProperty("selectQnaUser"));
				pstmt.setString(1,id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Qna q = new Qna();
					q.setQnaNo(rs.getInt("q_num"));
					q.setQnaTitle(rs.getString("q_title"));
					q.setQnaWriter(rs.getString("q_writer"));
					q.setQnaContent(rs.getString("q_content"));
					q.setQnaAnswer(rs.getString("q_answer"));
					q.setQnaDate(rs.getDate("q_date"));
					list.add(q);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return list;
		}
	
	
	//선택한 1:1문의 글 내용 불러오기
	public Qna selectQna(Connection conn, int num) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Qna q=null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectQnaNo"));
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				q = new Qna();
				q.setQnaNo(rs.getInt("q_num"));
				q.setQnaTitle(rs.getString("q_title"));
				q.setQnaWriter(rs.getString("q_writer"));
				q.setQnaContent(rs.getString("q_content"));
				q.setQnaAnswer(rs.getString("q_answer"));
				q.setQnaDate(rs.getDate("q_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return q;
	}

	// 새로운 QNA 등록하기 (USER)
	public int insertQna(Connection conn, Qna q) {
		
		PreparedStatement pstmt=null;
		int result=0;
		System.out.println("QNA 등록 체크 1");
		System.out.println(q.getQnaTitle());
		System.out.println(q.getQnaWriter());
		System.out.println(q.getQnaContent());
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertQna"));
			pstmt.setString(1, q.getQnaTitle());
			pstmt.setString(2, q.getQnaWriter());
			pstmt.setString(3, q.getQnaContent());
			result=pstmt.executeUpdate();
			System.out.println("QNA 등록 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	// 선택한 QNA에 ANSWER 등록하기 (ADMIN)
	public int updateAnswer(Connection conn, int num, String answer) {
		
		PreparedStatement pstmt=null;
		int result=0;
		System.out.println("QNA 답변 등록 체크 1");
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateQna"));
			pstmt.setString(1, answer);
			pstmt.setInt(2, num);
			result=pstmt.executeUpdate();
			System.out.println("QNA 답변 등록 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	// QNA 삭제하기 ONLY ADMIN
	public int deleteQna(Connection conn, int num) {

		PreparedStatement pstmt=null;
		int result=0;
		System.out.println("QNA 삭제 체크 1");
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteQna"));
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			System.out.println("QNA 삭제 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	
	
	
}
