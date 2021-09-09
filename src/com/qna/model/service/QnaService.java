package com.qna.model.service;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.board.model.vo.Board;
import com.member.model.vo.Member;
import com.qna.model.dao.QnaDao;
import com.qna.model.vo.Qna;

public class QnaService {
	
	private QnaDao dao=new QnaDao();
	
	// admin이 볼 때 전부 뜨기
	public List<Qna> QnaList(int cPage, int numPerPage){
		Connection conn = getConnection();
		List<Qna> list = dao.qnaList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int QnaListCount() {
		Connection conn = getConnection();
		int result = dao.QnaListCount(conn);
		close(conn);
		return result;
	}
	
	//USER이 볼 때 ID 값으로 찾기
	public List<Qna> QnaListUser(String id){
		Connection conn = getConnection();
		List<Qna> list = dao.qnaListUser(conn, id);
		close(conn);
		return list;
	}

	public Qna selectQna(int num) {
		
		Connection conn=getConnection();
		Qna q=dao.selectQna(conn, num);
		close(conn);
		
		return q;
	}
	
	public int insertQna(Qna q) {
		
		Connection conn=getConnection();
		int result=dao.insertQna(conn,q);
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int updateAnswer(int num, String answer) {
		
		Connection conn=getConnection();
		int result=dao.updateAnswer(conn, num, answer);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
	public int deleteQna(int num) {
		Connection conn=getConnection();
		int result=dao.deleteQna(conn, num);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
}
