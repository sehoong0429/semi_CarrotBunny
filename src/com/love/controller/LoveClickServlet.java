package com.love.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;
import com.board.model.vo.Comment;
import com.love.model.service.LoveService;
import com.love.model.vo.Love;
import com.member.model.vo.Member;

/**
 * Servlet implementation class BoardLike
 */
@WebServlet("/love/loveClick")
public class LoveClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoveClickServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 이 서블릿은 찜을 눌렀을 때 실행된다.
		
		HttpSession session = request.getSession(false);
		Member m = (Member)session.getAttribute("loginMember");
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int user;
		try {
			user=Integer.parseInt(request.getParameter("user"));
		}catch(NumberFormatException e) {
			user=0;
		}
		int love;
		try {
			love=Integer.parseInt(request.getParameter("love"));
		}catch(NumberFormatException e) {
			love=0;
		}
		int sold;
		try {
			sold=Integer.parseInt(request.getParameter("sold"));
		}catch(NumberFormatException e) {
			sold=0;
		}
		String searchType;
		try {
			searchType=request.getParameter("searchType");
		}catch(NumberFormatException e) {
			searchType="";
		}
		String keyword;
		try {
			keyword=request.getParameter("keyword");
		}catch(NumberFormatException e) {
			keyword="";
		}
		
		int boardNumber = Integer.parseInt(request.getParameter("no"));
		int cnt = new LoveService().findLove(m, boardNumber);
		
		Love updateLove = new Love();
		updateLove.setBoardNumber(boardNumber);
		updateLove.setUserId(m.getUserId());
		int result = -1;
		
		if (cnt == 0) {
			// insert는 성공시 정수 반환하는 듯
			result = new LoveService().insertLove(updateLove);
			System.out.println("삽입");
		} else {
			// delete는 성공시 삭제한 row수 반환
			result = new LoveService().deleteLove(updateLove);
			System.out.println("삭제");
		}
		
		String msg = "";
		// String loc = "/board/boardView?cPage="+cPage+"&no="+boardNumber;
		if (result > 0) {
			msg = "찜 설정 성공";
			List<Love> loveList = new LoveService().selectLoveList();
			request.setAttribute("loveList",loveList);
			
			int num = Integer.parseInt(request.getParameter("no"));
			
			Board b = new BoardService().selectBoard(num);
			request.setAttribute("board", b);
			
			List<Comment> comments = new BoardService().selectComment(num);
			request.setAttribute("comments", comments);
			
			request.setAttribute("cPage", cPage);
			request.setAttribute("user", user);
			request.setAttribute("love", love);
			request.setAttribute("sold", sold);
			request.setAttribute("searchType", searchType);
			request.setAttribute("keyword", keyword);

		} else {
			msg = "찜 설정 실패";
		}
		request.setAttribute("msg", msg);
		// request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
