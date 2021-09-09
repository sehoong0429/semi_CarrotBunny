package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(name="memberupdateendservlet",urlPatterns = "/memberupdateend.do")
public class MemberUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String userName=request.getParameter("userName");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		
		Member m=new Member();
		
		m.setUserId(userId);
		m.setPassword(password);
		m.setUserName(userName);
		m.setEmail(email);
		m.setPhone(phone);
		
		
		int result=new MemberService().updateMember(m);
		
		//result의 결과가 1이상이면 수정완료, 0이면 수정실패
		String msg=result>0?"회원정보수정완료":"회원정보수정실패";
		//String loc="/";
		//수정페이지로 이동해보자
		String loc="/memberUpdate.do?userId="+userId;
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		HttpSession session = request.getSession(); //세션을 다시 담는다. 
		session.setAttribute("loginMember", m);
		
		request.getRequestDispatcher("/views/common/msg.jsp")
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