package com.member.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.member.model.vo.Member;
import static com.common.JDBCTemplate.close;

public class MemberDao {

	private Properties prop=new Properties();
	
	public MemberDao() {
		try {
			System.out.println(MemberDao.class.getResource("/sql/member_sql.properties"));
			String filePath=MemberDao.class.getResource("/sql/member_sql.properties").getPath();
			prop.load(new FileReader(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member login(Connection conn, String userId, String pw) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMember"));
			pstmt.setString(1,userId);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				//조회된 row가 있다 , 없으면 안돌아감. 
				m=new Member();
				m.setUserId(rs.getString("member_id")); //대소문자구분안함.
				m.setPassword(rs.getString("member_pwd"));
				m.setUserName(rs.getString("mem_name"));
				//char형으로 데이터를 받을 때 사용
				//m.setGender(rs.getString("gender").charAt(0));
				m.setEmail(rs.getString("mem_email"));
				m.setPhone(rs.getString("mem_phone"));
				m.setIsAgree(rs.getInt("mem_agree"));
				m.setIsDelete(rs.getInt("mem_delete"));
				m.setIsAdmin(rs.getInt("mem_admin"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		
		System.out.println("회원가입 체크 1");
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertMember"));
			pstmt.setString(1,m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			result=pstmt.executeUpdate();
			System.out.println("회원가입 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Member checkDuplicateId(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		System.out.println("듀플체크 체크 1");
		try {
			System.out.println("듀플체크 체크 2");
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberId"));
			System.out.println("듀플체크 체크 3");
			pstmt.setString(1, userId);
			System.out.println("듀플체크 체크 4");
			rs=pstmt.executeQuery();
			System.out.println("듀플체크 체크 5");
			if(rs.next()) {
				m=new Member();
				m.setUserId(rs.getString("member_id"));
				m.setPassword(rs.getString("member_pwd"));
				m.setUserName(rs.getString("mem_name"));
				m.setEmail(rs.getString("mem_email"));
				m.setPhone(rs.getString("mem_phone"));
				m.setIsAgree(rs.getInt("mem_agree"));
				m.setIsDelete(rs.getInt("mem_delete"));
				m.setIsAdmin(rs.getInt("mem_admin"));

				System.out.println("듀플체크 체크 완료");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		System.out.println("회원정보수정 체크 1");
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateMember"));
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4,m.getUserId());
			result=pstmt.executeUpdate();
			System.out.println("회원정보수정 완료");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteMember"));
			pstmt.setString(1,userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	
	public int updatePassword(Connection conn,String userId, String newPw) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updatePassword"));
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
}
