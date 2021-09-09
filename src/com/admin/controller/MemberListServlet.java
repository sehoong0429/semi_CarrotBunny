package com.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.service.AdminService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(session == null || loginMember == null||!loginMember.getUserId().equals("admin")) {
			//잘못된 경로로 접근하셨습니다. ㅇㅣ페이지의 사용권한이 없습니다 -> 에러메세지 띄워주고 메인화면으로 돌아가게 
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다. 이 페이지 사용권한이 없습니다!!!!돌아가");
			request.setAttribute("loc", "/loginPage");
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg.jsp");
			rd.forward(request, response);

		}else { 

			int cPage; //보고있는 현재 페이지
			int numPerPage; //페이지당 데이터수 -> 사용자가 설정한다. 
			
			//쿼리스트링으로 데이터를 넘겨도 된다. 
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

				
			// 사용자가 원하는 페이지를 호출할 수 있게 페이지바를 구성 ! 
			
			
			List<Member> list = new AdminService().selectMemberList(cPage, numPerPage); //cpage,numperpage를 넘긴다.
			request.setAttribute("list", list);
//			
//			for(Member m : list) {
//				System.out.println(m.getmemberNum());
//			}
			
			int totalData = new AdminService().selectMemberCount();
			int totalPage = (int)Math.ceil((double)totalData/numPerPage);

			
			int pageBarSize = 4;
			int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd= pageNo+pageBarSize-1;


			String pageBar="";

			//이전 출력하기 
			if(pageNo==1) {
				pageBar+="<span>[이전]</span>";
			}else {
				pageBar +="<a href='"+request.getContextPath()
				+"/memberList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
			}

			
			//pageBar의 숫자를 출력하게 하는 구문 
			while(!(pageNo>pageEnd||pageNo>totalPage)) { 
				if(cPage==pageNo) { //분기처리 
					pageBar+="<span style='background-color:orange;'>"+pageNo+"</span>";
				}else {
					pageBar+="<a href='"+request.getContextPath()+"/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
				}
				pageNo++; //1부터 while문이 돈다. 
			}

			
			//다음 페이지 
			if(pageNo>totalPage) {
				pageBar+="<span>[다음]</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()
				+"/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
			}
			
			request.setAttribute("pageBar", pageBar); //memberlist.jsp에 페이지바를 넘긴다. 

			request.setAttribute("list", list);
			request.getRequestDispatcher("views/admin/memberList.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
