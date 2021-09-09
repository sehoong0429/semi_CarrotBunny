package com.board.model.service;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.board.model.dao.BoardDao;
import com.board.model.vo.Board;
import com.board.model.vo.Comment;
import com.member.model.vo.Member;

public class BoardService {
	
	private BoardDao dao = new BoardDao();
	
	// 삭제가 된 상품까지 모두 select
	public List<Board> selectBoardList(int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list = dao.selectBoardList(conn, cpage, numPerPage);
		close(conn);
		return list;
	}
	// 삭제되지 않은 상품만 select
	public List<Board> selectAliveBoardList(int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list = dao.selectAliveBoardList(conn, cpage, numPerPage);
		close(conn);
		return list;
	}
	// 삭제되지 않으며 판매되지 않은 상품만 select
		public List<Board> selectSoldBoardList(int cpage, int numPerPage) {
			Connection conn = getConnection();
			List<Board> list = dao.selectSoldBoardList(conn, cpage, numPerPage);
			close(conn);
			return list;
		}
	// user의 상품만 select(삭제되지 않은) 페이징 처리 포함
	public List<Board> selectUserBoardList(int cpage, int numPerPage,Member m) {
		Connection conn = getConnection();
		List<Board> list = dao.selectUserBoardList(conn, cpage, numPerPage, m);
		close(conn);
		return list;
	}
	// user의 상품만 select(삭제되지 않은) 페이징 처리 미포함
	public List<Board> selectAllUserBoardList(Member m) {
		Connection conn = getConnection();
		List<Board> list = dao.selectAllUserBoardList(conn, m);
		close(conn);
		return list;
	}
	// searchType, keyword를 이용하여 boardList 받아오기
	public List<Board> searchBoardList(int cpage, int numPerPage, String searchType, String keyword) {
		Connection conn = getConnection();
		List<Board> list = dao.searchBoardList(conn, cpage, numPerPage, searchType, keyword);
		close(conn);
		return list;
	}
	// searchType, keyword를 이용하여 판매중인 boardList 받아오기
	public List<Board> searchSoldBoardList(int cpage, int numPerPage, String searchType, String keyword) {
		Connection conn = getConnection();
		List<Board> list = dao.searchSoldBoardList(conn, cpage, numPerPage, searchType, keyword);
		close(conn);
		return list;
	}
	// 삭제된 상품까지 모두 count
	public int selectBoardCount() {
		Connection conn = getConnection();
		int result = dao.selectBoardCount(conn);
		close(conn);
		return result;
	}
	// 삭제되지 않은 상품만 count
	public int selectAliveBoardCount() {
		Connection conn = getConnection();
		int result = dao.selectAliveBoardCount(conn);
		close(conn);
		return result;
	}
	// 삭제되지 않고 판매 중인 상품만 count
	public int selectSoldBoardCount() {
		Connection conn = getConnection();
		int result = dao.selectSoldBoardCount(conn);
		close(conn);
		return result;
	}
	// 사용자가 판매한 상품만 count
	public int selectUserBoardCount(Member m) {
		Connection conn = getConnection();
		int result = dao.selectUserBoardCount(conn, m);
		close(conn);
		return result;
	}
	// searchType, keyword를 받아와 검색 후 count를 반환
	public int searchBoardCount(String searchType, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchBoardCount(conn, searchType, keyword);
		close(conn);
		return result;
	}
	// searchType, keyword를 받아와 검색 후 판매중인 상품만 count를 반환
	public int searchSoldBoardCount(String searchType, String keyword) {
		Connection conn = getConnection();
		int result = dao.searchSoldBoardCount(conn, searchType, keyword);
		close(conn);
		return result;
	}
	// 상품 하나의 정보를 가져온다.
	public Board selectBoard(int num) {
		Connection conn = getConnection();
		Board b = dao.selectBoard(conn, num);
		close(conn);
		return b;
	}
	
	//게시물 작성 
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = dao.insertBoard(conn, b);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	//게시물 수정 
	public int updateBoard(Board b) {
		Connection conn = getConnection();
		int result = dao.updateBoard(conn, b);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	//게시물 삭제 
	public int deleteBoard(Board b) {
		Connection conn = getConnection();
		int result = dao.deleteBoard(conn, b);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	
	//댓글 삭제 
	public int deleteComment(Comment cm) {
		Connection conn = getConnection();
		int result = dao.deleteComment(conn, cm);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		return result;
	
	}
	
	//좋아요 업데이트  
	public int updateLikeBoard(Board b) {
		Connection conn = getConnection();
		int result = dao.updateLikeBoard(conn, b);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		return result;
	}
	// 상품 판매완료 처리
	public int sellBoard(Board b) {
		Connection conn = getConnection();
		int result = dao.sellBoard(conn, b);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	// board의 댓글 리스트 받아오기
	public List<Comment> selectComment(int boardNum) {
		Connection conn = getConnection();
		List<Comment> list = dao.selectComment(conn, boardNum);
		close(conn);
		return list;
	}
	// 댓글 작성 
	public int insertComment(Comment c) {
		Connection conn = getConnection();
		int result = dao.insertComment(conn, c);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
}
