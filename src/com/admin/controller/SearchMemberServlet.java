package com.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.member.model.vo.Member;
/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/admin/searchMember")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//필요한 값들 다 받아오기 
		int cPage;
		int numPerPage;
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
		
		//client가 보낸 값 다 가져오기 
		String searchType=request.getParameter("searchType");
		String keyword=request.getParameter("searchKeyword");
		
		// 사용자가 원하는 페이지를 호출할 수 있게 pageBar 구성
		// board의 총 개수
		int totalData=new AdminService().searchMemberCount(searchType,keyword);
		// 1. 전체 페이지에 대한 수(전체자료에서 페이지당 수 나누기, 자동 올림처리)
		int totalPage=(int)Math.ceil((double)totalData/numPerPage);
		
		// pageBar에 출력될 페이지숫자 갯수
		int pageBarSize=5;
		// pageNo는 pageBar에 출력되는 페이지숫자의 시작값
		// 예를 들어 pageBarSize=5라는 가정 하에
		// cPage가 1~5는 pageNo=1, cPage가 6~10이면 pageNo=6
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		// pageEnd는 당연히 끝나는 값이겠죠
		int pageEnd=pageNo+pageBarSize-1;
		String pageBar="";
		
		// [이전] 출력
		if(pageNo==1) {
			pageBar="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/admin/searchMember?cPage="+(pageNo-1)
					+"&numPerPage="+numPerPage
					+"&searchType="+searchType+"&searchKeyword="+keyword+"'>[이전]</a>";
		}
		// [이전]과 [다음]사이에 오는 page 숫자를 출력
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/admin/searchMember?cPage="+pageNo
						+"&numPerPage="+numPerPage
						+"&searchType="+searchType+"&searchKeyword="+keyword+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		// [다음] 출력
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}
		else {
			pageBar+="<a href='"+request.getContextPath()+"/admin/searchMember?cPage="+pageNo
					+"&numPerPage="+numPerPage
					+"&searchType="+searchType+"&searchKeyword="+keyword+"'>[다음]</a>";
		}

		
		request.setAttribute("pageBar", pageBar);
		List<Member> result = new AdminService().searchMember(searchType,keyword,cPage,numPerPage);
		
		request.setAttribute("list", result);
		request.getRequestDispatcher("/views/admin/memberList.jsp") //.....,... ㅇ...ㅇㄹ,ㅡ나ㅣㅗㅓ존나 짜증 "/"...
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
