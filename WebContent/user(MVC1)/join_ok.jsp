<%@page import="com.myweb.user.model.UserVO"%>
<%@page import="com.myweb.user.model.UserDAO"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");

	
	//MVC1방식은 연결해주는 클래스를 따로 생성해서 사용한다(model클래스)
	//>UserDAO이동
	
	//1. UserDAO객체 생성 - 싱글통
	UserDAO dao = UserDAO.getInstance();
	//2. 중복 검사
	int result = dao.checkId(id);
	
	System.out.println(result);
	
	if(result == 1){ //중복
%>
	<script type="text/javascript">
		alert("중복된 아이디 입니다");
		history.go(-1); //뒤로가기(기록 -1)
	</script>
<%
	} else {//중복 x
		
		UserVO vo = new UserVO(id, pw, name, email, address, null);
		int result2 = dao.join(vo);//회원가입 진행
		
		if(result2 == 1) { //성공
%>
		<script type="text/javascript">
			alert("가입을 축하합니다");
			location.href = "login.jsp";
		</script>			
<%
		} else { //실패
%>
		<script type="text/javascript">
			alert("가입에 실패했습니다. 다시 시도하세요");
			location.href = "join.jsp";
		</script>
<%
		}
	
	}
%>






















































