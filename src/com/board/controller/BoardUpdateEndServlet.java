package com.board.controller;

import java.io.File;
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
 * Servlet implementation class BoardUpdateEndServlet
 */
@WebServlet("/board/boardUpdateEnd")
public class BoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 잘못된 요청이기때문에
			request.setAttribute("msg", "상품수정 오류. 같은 현상이 반복된다면 관리자에게 문의하세요.");
			request.setAttribute("loc", "/board/boardUpdate?no="+request.getParameter("boardNo"));
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}

		// 2.파일 업로드처리를 위한 필요한 값을 설정하기
		// 1) 파일업로드 위치 -> 절대경로로 가져와야함.
		String filepath = getServletContext().getRealPath("/upload/board/");
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
		MultipartRequest mr = new MultipartRequest(request, filepath,(1024*1024*10),
				"UTF-8",new MyRename());

		Board b = new Board();
		b.setBoardTitle(mr.getParameter("boardTitle"));
		b.setBoardWriter(mr.getParameter("boardWriter"));
		b.setBoardContent(mr.getParameter("boardContent"));
		b.setBoardPrice(Integer.parseInt(mr.getParameter("boardPrice")));
		b.setBoardAmount(Integer.parseInt(mr.getParameter("boardAmount")));
		
		
		String isNego = mr.getParameter("boardIsNego");
		if (isNego != null) {
			b.setBoardIsNego(1);
		} else {
			b.setBoardIsNego(0);
		}
		//파일이 있는지 없는지 확인
		//File객체를 이용해서 확인함
		File f = mr.getFile("up_file");
		String fPath = mr.getFilesystemName("up_file");
		//f가 null이면 파일이없음, file.length()값이 0이면 없는것
		if(f != null && f.length() > 0) {
			//새로추가된 파일이 있음.
			//이전파일을 지워줌
			File deleteFile=new File(filepath + mr.getParameter("boardFilepath1"));
			System.out.println(deleteFile.delete());
		}else {
			fPath = mr.getParameter("boardFilepath1");
		}
		int num = Integer.parseInt(request.getParameter("no"));
		// 파일명을 DB에 저장해야함. -> rename된 파일을 가져오기
		// n.setFilePath(mr.getParameter("up_file"));
		b.setRenamedFileName(fPath);
		b.setBoardNumber(num);
		int result = new BoardService().updateBoard(b);

		// 등록성공하면 등록성공 메세지출력 후 리스트화면으로 이동
		// 등록실패하면 등록실패 메세지출력 후 등록화면으로 이동
		if (result > 0) {
			System.out.println("수정 성공");
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
			
			List<Love> loveList = new LoveService().selectLoveList();
			request.setAttribute("loveList",loveList);
			
			
			Board board = new BoardService().selectBoard(num);
			request.setAttribute("board", board);
			
			List<Comment> comments = new BoardService().selectComment(num);
			request.setAttribute("comments", comments);
		} else {
			System.out.println("수정 실패");
		}
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
