package com.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.board.model.vo.Board;

/**
 * Servlet implementation class AdminCheckSellServlet
 */
@WebServlet("/adminCheckSell")
public class AdminCheckSellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCheckSellServlet() {
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
		// 데이터를 가져올때 원하는 구역 가져오기
		// 1. 사용자가 원하는 page -> 현재 페이지
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		// 2. 페이지당 데이터 수 -> 사용자가 설정
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerPage=5;
		}
//		
//		List<Board> list = new AdminService().selectBoardList(cPage, numPerPage); 
//		request.setAttribute("list", list);
		

		// 사용자가 원하는 페이지를 호출할 수 있게 pageBar 구성
		// board의 총 개수
		int totalData=new AdminService().SellBoardListCount();
		// 1. 전체 페이지에 대한 수(전체자료에서 페이지당 수 나누기, 자동 올림처리)
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
			pageBar+="<a href='"+request.getContextPath()+"/adminCheckSell?cPage="+(pageNo-1)
					+"&numPerpage="+numPerPage+"'>[이전]</a>";
		}
	
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/adminCheckSell?cPage="+pageNo
						+"&numPerpage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/adminCheckSell?cPage="+pageNo
					+"&numPerpage="+numPerPage+"'>[다음]</a>";
		}
		// pageBar에는 결국 [이전], 페이지 숫자들, [다음]과 관련된 html 문자열이 들어감
		request.setAttribute("pageBar",pageBar);
	//	request.setAttribute("cPage",cPage);
		

		List<Board> list=new AdminService().SellBoardList(cPage, numPerPage);
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
