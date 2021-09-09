package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		Board b = new BoardService().selectBoard(Integer.parseInt(request.getParameter("no")));
		
		request.setAttribute("cPage", cPage);
		request.setAttribute("board", b);
		request.getRequestDispatcher("/views/board/boardUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
