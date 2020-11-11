package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

//클래스 이름도 잘 지어야함
public class RegistServiceImpl implements BoardService{
	
	//게시글 등록 작업 처리
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//DAO객체 생성
		BoardDAO dao = BoardDAO.getInstance();
		dao.regist(writer, title, content);
		
	}
	

}































