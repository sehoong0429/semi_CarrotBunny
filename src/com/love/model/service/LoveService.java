package com.love.model.service;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.love.model.dao.LoveDao;
import com.love.model.vo.Love;
import com.member.model.vo.Member;

public class LoveService {
	private LoveDao dao = new LoveDao();
	// Love 테이블의 모든 row를 리스트에 받아온다.
	public List<Love> selectLoveList() {
		Connection conn = getConnection();
		List<Love> list = dao.selectLoveList(conn);
		close(conn);
		return list;
	}
	
	public List<Love> selectUserLoveList(int cpage, int numPerPage, Member m) {
		Connection conn = getConnection();
		List<Love> list = dao.selectUserLoveList(conn, cpage, numPerPage, m);
		close(conn);
		return list;
	}
	// 사용자의 찜 상품 카운트
	public int selectLoveCount(Member m) {
		Connection conn = getConnection();
		int result = dao.selectLoveCount(conn, m);
		close(conn);
		return result;
	}
	public int findLove(Member m, int boardNumber) {
		Connection conn = getConnection();
		int result = dao.findLove(conn, m, boardNumber);
		close(conn);
		return result;
	}
	
	public int insertLove(Love love) {
		Connection conn = getConnection();
		int result = dao.insertLove(conn, love);
		close(conn);
		return result;
	}
	
	public int deleteLove(Love love) {
		Connection conn = getConnection();
		int result = dao.deleteLove(conn, love);
		close(conn);
		return result;
	}
}
