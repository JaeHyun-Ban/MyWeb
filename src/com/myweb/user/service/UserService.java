package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	
	//User(회원)에서 처리해야할 기능을 인터페이스로 생성
	
	//반환유형 = int > 결과를 컨트롤러로 가져갈 수 있다
	public int execute(HttpServletRequest request, HttpServletResponse response);
	
	
}
















































