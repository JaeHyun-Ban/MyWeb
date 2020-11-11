package com.myweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;
import com.myweb.util.PageVO;

public class GetListServiceImpl implements BoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO dao = BoardDAO.getInstance();
//		ArrayList<BoardVO> list = dao.getList();
		
		//페이징 처리
		//1.첫번째 페이지 진입할 때의 값
		int pageNum = 1;//현재 페이지
		int amount = 10;//10개씩보기
		
		//pageNum이 넘어 올 때 pageNum, amount값을 세팅
		if(request.getParameter("pageNum") != null || request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		ArrayList<BoardVO> list = dao.getList(pageNum, amount);	
		//2.PageVO생성
		int total = dao.getTotal();//전체 게시글 수 
		PageVO pageVO = new PageVO(pageNum, amount, total);
		
		//세션은 중요한걸 담고 리퀘스트에 넣어주자
		//화면으로 가져가기 위해 request에다가 list를 저장
		request.setAttribute("list", list);
		
		//pageNation을 화면에 전달
		request.setAttribute("pageVO", pageVO);
		
		
		
		
		
		
	}

	

}



































