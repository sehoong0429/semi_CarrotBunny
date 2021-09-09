package com.qna.controller;

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
import com.board.model.service.BoardService;
import com.board.model.vo.Board;
import com.member.model.vo.Member;
import com.qna.model.service.QnaService;
import com.qna.model.vo.Qna;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/qna.do")
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaServlet() {
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

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		if (session == null || loginMember == null) {
			request.setAttribute("msg", "로그인 후 이용할 수 있습니다.");
			request.setAttribute("loc", "/index.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg.jsp");
			rd.forward(request, response);
		} else {
			
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
			
			String userId = loginMember.getUserId();


			List<Qna> list = null;
			String admin = "admin";

			if (userId.equals(admin)) {
				System.out.println("관리자가 보는 1:1");
				list = new QnaService().QnaList(cPage,numPerPage);
				request.setAttribute("list", list);
				
				int totalData = new QnaService().QnaListCount();
				int totalPage = (int)Math.ceil((double)totalData/numPerPage);


				int pageBarSize = 4;
				int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
				int pageEnd= pageNo+pageBarSize-1;


				String pageBar="";


				if(pageNo==1) {
					pageBar+="<span>[이전]</span>";
				}else {
					pageBar +="<a href='"+request.getContextPath()
					+"/qna.do?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
				}


				while(!(pageNo>pageEnd||pageNo>totalPage)) { 
					if(cPage==pageNo) { //분기처리 
						pageBar+="<span>"+pageNo+"</span>";
					}else {
						pageBar+="<a href='"+request.getContextPath()+"/qna.do?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
					}
					pageNo++; //1부터 while문이 돈다. 
				}


				if(pageNo>totalPage) {
					pageBar+="<span>[다음]</span>";
				}else {
					pageBar+="<a href='"+request.getContextPath()
					+"/qna.do?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>"; 
				}
				request.setAttribute("pageBar", pageBar); 
				
			} else {
				System.out.println("문의 작성자가 보는 1:1");
				list = new QnaService().QnaListUser(userId);
			}


			request.setAttribute("qna", list);

			request.getRequestDispatcher("/views/qna/qna.jsp").forward(request, response);
		}

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
