package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateDeleteServlet
 */
@WebServlet("/adminupdatemember")
public class MemberUpdateDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	Member m = new Member();
	m.setIsDelete(1);
	m.setmemberNum(Integer.parseInt(request.getParameter("memberNum")));
	
	int result = new AdminService().updateMember(m);
	
	String msg="";
	String loc="";
	if (result > 0) {
		msg = "회원 삭제 성공";
		loc = "/memberList";
	}else {
		msg = "회원 삭제 실패. 다시 시도해주세요";
		loc = "/memberlistDetail?memberNum=?"+m.getmemberNum();
	}
		
	request.setAttribute("msg", msg);
	request.setAttribute("loc", loc);
	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
