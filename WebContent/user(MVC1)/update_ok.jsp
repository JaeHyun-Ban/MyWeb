<%@page import="com.myweb.user.model.UserDAO"%>
<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	현재 VC부분 화면의 값을 받아서 처리하는 부분
	
	1. 폼데이터를 받는다
	2. DAO에 update()메서드를 생성하고, 업데이트 구문을 실행
	3. 수정(update)성공시 "회원정보가 수정되었습니다" 출력 후 마이페이지로 이동
		실패 시 "회원정보 수정에 실패했습니다" 출력 후 마이페이지로 이동
	*/

	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	
	
	UserVO vo = new UserVO(id, pw, name, email, address, null);
	UserDAO dao = UserDAO.getInstance();
	
	int result = dao.update(vo);
	
	//검사
	if(result == 1) {//update성공
		session.setAttribute("login", vo);
%>
	<script type="text/javascript">
		alert("회원정보가 수정되었습니다");
		location.href = "mypage.jsp";
	</script>
<%	
	} else { //실패
%>
	<script type="text/javascript">
		alert("회원정보가 수정에 실패했습니다");
		location.href = "mypage.jsp";
	</script>
<%
	}
	
%>
































