package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserJoinServiceImpl implements UserService {
	
	
	//User의 회원가입 처리를 해주는 서비스 클래스
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("UserJoinSErviceImpl 실행");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		
		UserDAO dao = UserDAO.getInstance();
		//중복검사
		int result = dao.checkId(id);//중복 1, 중복x 2
		
		if(result == 1) {//중복회원o
			return 1;
		} else { //중복x
			UserVO vo = new UserVO(id, pw, name, email, address, null); //VO객체 생성
			dao.join(vo);//가입 실행 > 성공이라고 가정(반환값 신경 안씀)
			return 0;//결과 값을 리턴
		}
		
		 
	}

}












































