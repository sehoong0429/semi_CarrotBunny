package com.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.vo.Member;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/noticewrite")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
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
			request.setAttribute("loc", "/");
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg.jsp");
			rd.forward(request, response);
			
		}else {
			request.getRequestDispatcher("/views/notice/noticeWrite.jsp").forward(request, response);
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
