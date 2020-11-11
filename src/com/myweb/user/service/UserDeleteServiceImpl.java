package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserDeleteServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		String id = user.getId();
		
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		
		//유효성 을 확인 후 삭제 진행
		//1.login메서드로 유효성을 확인
		UserVO check = dao.login(id, pw);
		int result = 0;
		
		if(check != null) {//로그인 성공
			dao.delete(id);//성공 1, 실패 0
			session.invalidate();//바로 삭제
			return 1;
		} else {//실패
			return 0;
		}
		
		//2.기존 pw와의 유효성 비교 >> ?
		
	}

}
