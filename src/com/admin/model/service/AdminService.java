package com.admin.model.service;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.admin.model.dao.AdminDao;
import com.board.model.vo.Board;
import com.member.model.vo.Member;


public class AdminService {

	private AdminDao dao = new AdminDao();
	
	public List<Member> selectMemberList(int cPage, int numPerPage){
		Connection conn= getConnection();
		List<Member> list = dao.selectMemberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int selectMemberCount() {
		Connection conn = getConnection();
		int result = dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	public Member selectMemberDetail(int no) {
		Connection conn = getConnection();
		Member m = dao.selectMemberDetail(conn, no);
		close(conn);
		return m;
	}
	
	public int deleteMember(int memberNum) {
		Connection conn=getConnection();
		int result = dao.deleteNotice(conn,memberNum);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateMember(Member m) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, m);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	
	public List<Member> searchMember(String searchType, String keyword,int cPage, int numPerPage){
		Connection conn= getConnection();
		List<Member> list = dao.searchMember(conn,searchType, keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	
	public int searchMemberCount(String searchType, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchMemberCount(conn,searchType,keyword);
		close(conn);
		return result;
	}
	
	
	public List<Board> selectBoardList(int cPage, int numPerPage){
		Connection conn= getConnection();
		List<Board> list = dao.selectBoardList(conn,cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int selectBoardListCount() {
		Connection conn = getConnection();
		int result = dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	public int searchBoardCount(String searchType, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchBoardCount(conn, searchType, keyword);
		close(conn);
		return result;
	}
	
	public List<Board> searchBoard(int cpage, int numPerPage, String searchType, String keyword) {
		Connection conn = getConnection();
		List<Board> list = dao.searchBoard(conn, cpage, numPerPage, searchType, keyword);
		close(conn);
		return list;
	}


	public List<Board> SellBoardList(int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list = dao.SellBoardList(conn, cpage, numPerPage);
		close(conn);
		return list;
	}
	
	public int SellBoardListCount() {
		Connection conn = getConnection();
		int result = dao.SellBoardListCount(conn);
		close(conn);
		return result;
	}

	public List<Board> SoldBoardList(int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list = dao.SoldBoardList(conn, cpage, numPerPage);
		close(conn);
		return list;
	}
	
	public int SoldBoardListCount() {
		Connection conn = getConnection();
		int result = dao.SoldBoardListCount(conn);
		close(conn);
		return result;
	}

}
