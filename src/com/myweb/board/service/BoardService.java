package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardVO;

public interface BoardService {
	
	//게시판에서 처리해야할 기능을 인터페이스로 생성해서 처리해준다
	
	//추상메서드 매개변수로(requeset, response를 받음), 게시글 등록 메서드
	public void execute(HttpServletRequest request, HttpServletResponse response);
	
	
	
	
	
}


















































