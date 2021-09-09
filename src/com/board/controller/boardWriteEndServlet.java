package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.board.model.service.BoardService;
import com.board.model.vo.Board;
import com.board.model.vo.Comment;
import com.common.MyRename;
import com.love.model.service.LoveService;
import com.love.model.vo.Love;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class boardWriteEndServlet
 */
@WebServlet("/board/boardWriteEnd")
public class boardWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public boardWriteEndServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 잘못된 요청이기때문에
			request.setAttribute("msg", "상품등록 작성오류 [form:enctype] 관리자에게 문의하세요 :(");
			request.setAttribute("loc", "/board/boardPage");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}

		// 2.파일 업로드처리를 위한 필요한 값을 설정하기
		// 1) 파일업로드 위치 -> 절대경로로 가져와야함.
		String filepath = getServletContext().getRealPath("/upload/board/");
		//System.out.println("경로 : " + filepath);
		// 2) 저장할 파일에 대한 최대크기 설정
		//int maxSize = 1024 * 1024 * 10;// 10MB
		// 3) 문자열 인코딩
		//String encode = "utf-8";
		// 4) 업로드된 파일에 대한 이름 재정의(rename)
		// 개발자가 직접 작성할 수도 있고, 기본으로 제공하는 클래스가 있음(DefaultFileRenamePolicy)

		// 파일업로드하기
		// MultipartRequest클래스를 생성 -> request로 전송된 데이터가 지정한 경로에 저장
		// MultipartRequest클래스생성자는 5개의 매개변수를 가지고 있음
		// 1. HttpServletRequest, 2. 파일경로,3. 파일최대크기, 4. 인코딩, 5.rename규칙

		//DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request,filepath,(1024*1024*10),
				"UTF-8",new MyRename());

		Board b = new Board();
		b.setBoardTitle(mr.getParameter("boardTitle"));
		b.setBoardWriter(mr.getParameter("boardWriter"));
		b.setBoardContent(mr.getParameter("boardContent"));
		b.setBoardPrice(Integer.parseInt(mr.getParameter("boardPrice")));
		b.setBoardAmount(Integer.parseInt(mr.getParameter("boardAmount")));
		b.setOriginalFileName(mr.getOriginalFileName("boardFilepath1"));
		b.setRenamedFileName(mr.getFilesystemName("boardFilepath1"));
		
		
		String isNego = mr.getParameter("boardIsNego");
		if (isNego != null) {
			b.setBoardIsNego(1); //체크 
		} else {
			b.setBoardIsNego(0); //체크아님 
		}

//		b.setBoardFilePath(mr.getFilesystemName("boardFilepath1"));
//		System.out.println(mr.getFilesystemName("boardFilepath2"));
		int result = new BoardService().insertBoard(b);
		// 등록성공하면 등록성공 메세지출력 후 리스트화면으로 이동
		// 등록실패하면 등록실패 메세지출력 후 등록화면으로 이동
		if (result > 0) {
			request.setAttribute("msg", "등록 성공");
			int cPage;
			try {
				cPage=Integer.parseInt(request.getParameter("cPage"));
			}catch(NumberFormatException e) {
				cPage=1;
			}
			request.setAttribute("cPage", cPage);
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
			request.getRequestDispatcher("/views/board/boardMsg.jsp").forward(request, response);
			
		} else {
			request.setAttribute("msg", "등록 실패");
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
