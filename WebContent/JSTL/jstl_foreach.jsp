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

	<h3>1~100까지 홀수 합</h3>
	<%
	int sum = 0;
	for(int i = 1; i < 101; i+=2) {
		sum += i;
	}
	%>
	결과:<%=sum %>
	
	<hr/>
	<%-- JSTL 아주중요함 --%>
	<%-- 변수의 선언 --%>
	<c:set var="total" value="0" />
	<%-- forEach는 자주 사용된다 --%>
	<c:forEach var="i" begin="1" end="100" step="2" ><%-- item은 향상된 for문에서 사용한다 --%>
		<c:set var="total" value="${total + i}"/><%-- 값을 쌓아주는 법 --%>
	</c:forEach>
	결과:${total }
	
	
	<hr/>
	<h3>구구단 3단 출력</h3>
	<c:set var="dan" value="3"/>
	# 구구단 ${dan }단<br/>
	<c:forEach var="i" begin="1" end="9" step="1">
		${dan } x ${i } = ${dan * i }<br/>
	</c:forEach>
	
	
	
	<hr/>
	<h3>2~9단까지 모든 구구단 출력</h3>
	<c:forEach var="dan2" begin="2" end="9" step="1">
		구구단 ${dan2 }단<br/>
		<c:forEach var="i" begin="1" end="9" step="1">
			${dan2 } x ${i } = ${dan2 * i }<br/>
		</c:forEach>
		<hr/>
	</c:forEach>
	
	
	<hr/>
	<%-- 무지막지하게 중요한 향상for문 --%>
	<h3>향상된 for문</h3>
	<%
	int[] arr = new int[]{1,2,3,4,5};
	for(int i : arr){
		out.println(i);
	}
	%>
	<hr/>
	<c:set var="arr2" value="<%=new int[] {1,2,3,4,5} %>"/><%-- 배열 생성하는법 --%>
	<%-- items에는 session이나 request에 들어있는 값을 가지고와서 작성한다 --%>
	<%-- varStatus - 현재 for의 상태값들을 확인 --%>
	<c:forEach var="i" items="${arr2 }" varStatus="s"><%-- 배열의 길이만큼 자동으로 반복 --%>
		${s.index }번
		값${i }<br/>
		
	</c:forEach>
	
</body>
</html>













































