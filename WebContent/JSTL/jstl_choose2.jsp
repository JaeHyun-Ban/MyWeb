<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
	choose태그를 이용해서 90이상이면 A, 80이상 B, 70이상 C, 나머지 F
	
	90점 이상일 떄는 중첩 if구문의 형태로 A+, A로 나누어 표현
	-->
	<c:choose>
	
		<c:when test="${param.score >= 90 }">
			<c:choose><%-- 중첩 조건문 사용하기 --%>
				<c:when test="${param.score >= 95 }">
					점수:${param.score }, A+<br/>
				</c:when>
				<c:otherwise>
					점수:${param.score }, A<br/>
				</c:otherwise>
			</c:choose>
		</c:when>
		
		<c:when test="${param.score >= 80 }">
			점수:${param.score }, B<br/>
		</c:when>
		
		<c:when test="${param.score >= 70 }">
			점수:${param.score }, C<br/>
		</c:when>
		
		<c:otherwise>
			점수:${param.score }, F<br/>
		</c:otherwise>
		
	</c:choose>




</body>
</html>





































