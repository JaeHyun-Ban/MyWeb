package com.myweb.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserVO;


//게시글 수정, 삭제에 대한 필터
@WebFilter({"/board/modify.board", "/board/update.board", "/board/delete.board"})
public class BoardFilter2 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//1.등록화면에서는 작성자를 id값으로 고정
		//2.각 요청으로 id가 parameter로 전달되는지 확인
		//3.write화면에서 작성자를 id값으로 고정
		//4.modify.board, update.board, delete.board요청으로 넘어갈 때 writer를 담아서 보내주도록 처리
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		if(user == null) {
			res.sendRedirect("/MyWeb/user/login.user");
			return;
		}
		
		String id = user.getId();//세션에서 넘어온 id
		
		String writer =  request.getParameter("writer");//수정화면에서 넘어온 파라미터 값
		//System.out.println("넘어온 writer값: " + writer);
		
		if(writer == null && !id.equals(writer)) {//권한이 없는 경우
			
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다');");
			out.println("location.href='/MyWeb/board/list.board';");//로그인화면 보내주기
			out.println("</script>");
			
			return;//반드시 작성
		}
		
		chain.doFilter(request, response);
		
		
		
		
		
		
		
		
	}

}






























































































