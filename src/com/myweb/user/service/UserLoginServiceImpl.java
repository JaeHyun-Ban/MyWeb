package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserLoginServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		UserVO user = dao.login(id, pw);
		
		if(user != null) { //로그인 성공
			//request.getSession()현재 유지중인세션을 리턴
			HttpSession session = request.getSession();//자바에서 세션을 얻어오는법
			session.setAttribute("user", user);
			return 1;
			
		} else { //로그인 실패
			return 0;
		}
		
	}

}

























