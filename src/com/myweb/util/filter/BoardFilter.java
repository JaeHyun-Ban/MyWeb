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

//1. Filter인터페이스를 상속받고, doFilter메서드를 오버라이딩 한다
//2.필터클래스를 등록하는 방법 - @WebFilter, web.xml에 필터 설정
//@WebFilter("/*") //>모든 동작 할 때마다 필터가 뜸
//@WebFilter("*.board") //>.board로 들어가는 모든 것
@WebFilter({"/board/write.board", "/board/regist.board"})//특정 단어를 매핑({"글쓰기", "글 등록"})시 세션 검사
public class BoardFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//doFilter는  ServletRequest로 요청,응답을 받는다
		//ServletRequeset는 HttpServletRequest의 부모타입이다
		//형변환을 통해서 자식형태(HttpServlet...)로 변환
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//세션을 얻어서 권환 확인
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		if(user == null) {//로그인x -> 권한이 존재하지 않는다
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스 입니다');");
			out.println("location.href='/MyWeb/user/login.user';");//로그인화면 보내주기
			out.println("</script>");
			
//			res.sendRedirect("list.board");
			return;//return을 작성해서 doFilter가 실행되지 않는다(컨트롤러를 실행하지 않음)
		}
		
		
		
		chain.doFilter(request, response);//서블릿이나, 연결되어 있는 다른 필터를 실행
		
	}

	
}


































































































