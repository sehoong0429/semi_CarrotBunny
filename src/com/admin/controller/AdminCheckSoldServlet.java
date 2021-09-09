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
 * Servlet implementation class AdminCheckSoldServlet
 */
@WebServlet("/adminCheckSold")

public class AdminCheckSoldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCheckSoldServlet() {
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

		
		int totalData=new AdminService().SoldBoardListCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerPage);
		
		int pageBarSize=4;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/adminCheckSold?cPage="+(pageNo-1)
					+"&numPerpage="+numPerPage+"'>[이전]</a>";
		}
	
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/adminCheckSold?cPage="+pageNo
						+"&numPerpage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/adminCheckSold?cPage="+pageNo
					+"&numPerpage="+numPerPage+"'>[다음]</a>";
		}
		// pageBar에는 결국 [이전], 페이지 숫자들, [다음]과 관련된 html 문자열이 들어감
		request.setAttribute("pageBar",pageBar);
		//request.setAttribute("cPage",cPage);
		

		List<Board> list=new AdminService().SoldBoardList(cPage, numPerPage);
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
