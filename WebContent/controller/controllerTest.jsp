<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<!-- JSP페이지를 이용하면 아래의 주소 5개를 생성해야 할 것이다. -->
	<!-- 하지만 서블릿으로 모두 보내준다면?? -->
	<!-- 요청주소 경로는 폴더명을 맞추어 사용하는게 좋습니다 -->
	<a href="<%=request.getContextPath() %>/controller/asdasd.test">가입요청</a>
	<a href="<%=request.getContextPath() %>/controller/login.test">로그인요청</a>
	<a href="<%=request.getContextPath() %>/controller/logout.test">로그아웃</a>
	<a href="<%=request.getContextPath() %>/controller/delete.test">회원탈퇴</a>
	<a href="<%=request.getContextPath() %>/controller/update.test">회원수정</a>
	



</body>
</html>



















































