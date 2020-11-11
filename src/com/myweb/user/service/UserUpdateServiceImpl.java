package com.myweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserVO;

public class UserUpdateServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		//수정할 파라미터 값 받기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		UserVO vo = new UserVO(id, pw, name, email, address, null);
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.update(vo);//성공 1, 실패 0
		
		HttpSession session = request.getSession();//>>이거 안해도됨?
		session.setAttribute("user", vo);//세션 갱신
		
		return result;
	}

}
