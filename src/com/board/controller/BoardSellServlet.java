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
 * Servlet implementation class BoardSellServlet
 */
@WebServlet("/board/boardSell")
public class BoardSellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSellServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Board b = new Board();
		b = new BoardService().selectBoard(Integer.parseInt(request.getParameter("no")));
		int result;
		// 판매중이라면 판매완료 처리.
		if (b.getBoardIsSell() == 1) {
			b.setBoardIsSell(0);
			result = new BoardService().sellBoard(b);
		} else {
			result = -1;
		}
//		b.setBoardIsSell(0);
//		b.setBoardNumber(Integer.parseInt(request.getParameter("no")));
//		
//		int result = new BoardService().sellBoard(b);
		
		if (result > 0) {
			System.out.println("판매 처리 완료");
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
			
			int num = Integer.parseInt(request.getParameter("no"));
			Board board = new BoardService().selectBoard(num);
			request.setAttribute("board", board);
			
			List<Comment> comments = new BoardService().selectComment(num);
			request.setAttribute("comments", comments);
		} else if (result == -1) {
			System.out.println("이미 판매 처리된 상품");
		} else {
			System.out.println("판매 처리 실패");
		}
		
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
