package com.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.board.model.service.BoardService;
import com.board.model.vo.Board;
import com.member.model.vo.Member;

/**
 * Servlet implementation class searchBoardListServlet
 */
@WebServlet("/searchBoardList")
public class searchBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

 
		int cPage;
		int numPerPage;

		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}

		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerPage=5;
		}
		
		String searchType=request.getParameter("searchType");
		String keyword=request.getParameter("searchKeyword");
		

		int totalData=new AdminService().searchBoardCount(searchType, keyword);
		int totalPage=(int)Math.ceil((double)totalData/numPerPage);
		
		// pageBar에 출력될 페이지숫자 갯수
		int pageBarSize=4;
		// pageNo는 pageBar에 출력되는 페이지숫자의 시작값
		// 예를 들어 pageBarSize=5라는 가정 하에
		// cPage가 1~5는 pageNo=1, cPage가 6~10이면 pageNo=6
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		// pageEnd는 당연히 끝나는 값이겠죠
		int pageEnd=pageNo+pageBarSize-1;
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/searchBoardList?cPage="+(pageNo-1)
					+"&numPerpage="+numPerPage
					+"&searchType="+searchType+"&searchKeyword="+keyword+"'>[이전]</a>";
		}
	
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/searchBoardList?cPage="+pageNo
						+"&numPerpage="+numPerPage
						+"&searchType="+searchType+"&searchKeyword="+keyword+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/searchBoardList?cPage="+pageNo
					+"&numPerpage="+numPerPage
					+"&searchType="+searchType+"&searchKeyword="+keyword+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar",pageBar);
		


		List<Board> list=new AdminService().searchBoard(cPage, numPerPage, searchType, keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/boardList.jsp")
		.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
