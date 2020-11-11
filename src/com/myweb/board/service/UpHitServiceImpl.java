package com.myweb.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class UpHitServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno = request.getParameter("bno");
		
		//화면에서 넘어온 쿠키를 받아서, 현재 조회번호와 같은지 검사해서 싫행여부 결정
		Cookie[] arr = request.getCookies();//모든 쿠키를 저장
		boolean flag = true;
		if(arr != null) {//null로 여부 확인
			for(Cookie c : arr) {
				if(c.getName().equals(bno)) {//봤던 글
					flag = false;
					break;
				}
			}
		}
		
		
		if(flag) {//봣던글이 아니라면(falg==true상태)
			
			BoardDAO dao = BoardDAO.getInstance();
			dao.upHit(bno);
		}
		
		//쿠키를 이용해서 조회된 번호를 클라이언트 측으로 전달 검사하기
		Cookie cookie = new Cookie(bno, bno);
		cookie.setMaxAge(30);
		response.addCookie(cookie);
		
		
		
		

	}

}






























