<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
	.mystyle {
		text-align: center;
	}
</style>


</head>
<body>
	
	<h2>아래값들을 2020년05월03일 형식으로 변경</h2>
	
	<c:set var="TIME_A" value="2020-05-03"/>
	<c:set var="TIME_B" value="2020/05/03"/>
	<c:set var="TIME_C" value="2020-05-03 21:30:22"/>
	<c:set var="TIME_D" value="<%=new Date() %>"/>
	
	<!-- 1.문자형 -> Date()로 타입변환 -->
	<fmt:parseDate var="a" value="${TIME_A }" pattern="yyyy-MM-dd"/>
	<!-- 2. Date()형 -> 문자형태 패턴형태로 형변환 -->
	<fmt:formatDate var="aa" value="${a }" pattern="yyyy년MM월dd일" />
	2020-05-03 -> ${aa }<br/>
	
	<hr/>
	<fmt:parseDate var="b" value="${TIME_B}" pattern="yyyy/MM/dd" />
	<fmt:formatDate var="bb" value="${b }" pattern="yyyy년MM월dd일"/>
	2020/05/03 -> ${bb }<br/>
	
	
	<hr/>
	<fmt:parseDate var="c" value="${TIME_C }" pattern="yyyy-MM-dd HH:mm:ss"/>
	<fmt:formatDate var="cc" value="${c }" pattern="yyyy년MM월dd일"/>
	2020-05-03 21:30:22 -> ${cc }<br/>
	
	<hr/>
	<fmt:formatDate var="d" value="${TIME_D }" pattern="yyyy년MM월dd일"/>
	new Date() -> ${d }
	
	<hr/>
	<div class="mystyle" align="center">
		<table border="1" >
			<tr>
				<td>2020-05-03</td>
				<td>-></td>
				<td>${aa }</td>
				<td>공백공백공백</td>		
			</tr>
		</table>
	</div>
	
	
</body>
</html>











































