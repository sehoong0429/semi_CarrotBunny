package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;
import com.board.model.vo.Comment;
import com.love.model.service.LoveService;
import com.love.model.vo.Love;

/**
 * Servlet implementation class BoardCommentServlet
 */
@WebServlet("/board/comment")
public class BoardCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		int refNum=Integer.parseInt(request.getParameter("refNum"));
		int commentLevel=Integer.parseInt(request.getParameter("commentLevel"));
		String commentContent=request.getParameter("commentContent");
		String commentWriter=request.getParameter("commentWriter");
		
		Comment c=new Comment();
		c.setBoardNumber(boardNum);
		c.setCommentLevel(commentLevel);
		c.setRefNumber(refNum);
		c.setCommentWriter(commentWriter);
		c.setCommentContent(commentContent);
		
	
		int result = new BoardService().insertComment(c);
		String msg="";
		String loc="/board/boardView?no="+boardNum;
		if(result>0) {
				//등록성공 
			msg="댓글등록 성공";
		}else {
			//등록실패 
			msg="댓글등록실패";
			
		}
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		request.setAttribute("cPage", cPage);
		int user;
		try {
			user=Integer.parseInt(request.getParameter("user"));
		}catch(NumberFormatException e) {
			user=0;
		}
		request.setAttribute("user", user);
		int love;
		try {
			love=Integer.parseInt(request.getParameter("love"));
		}catch(NumberFormatException e) {
			love=0;
		}
		request.setAttribute("love", love);
		int sold;
		try {
			sold=Integer.parseInt(request.getParameter("sold"));
		}catch(NumberFormatException e) {
			sold=0;
		}
		request.setAttribute("sold", sold);
		String searchType;
		try {
			searchType=request.getParameter("searchType");
		}catch(NumberFormatException e) {
			searchType="";
		}
		request.setAttribute("searchType", searchType);
		String keyword;
		try {
			keyword=request.getParameter("keyword");
		}catch(NumberFormatException e) {
			keyword="";
		}
		request.setAttribute("keyword", keyword);
		List<Love> loveList = new LoveService().selectLoveList();
		request.setAttribute("loveList",loveList);
		int num = Integer.parseInt(request.getParameter("boardNum"));
		
		Board b = new BoardService().selectBoard(num);
		request.setAttribute("board", b);
		
		List<Comment> comments = new BoardService().selectComment(num);
		request.setAttribute("comments", comments);
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
