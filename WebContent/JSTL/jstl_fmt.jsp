<%@page import="java.sql.Timestamp"%>
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
</head>
<body>
	<!-- DB와 날짜를 편하게 저장하기위해 String으로 저장하곤 한다 -->
	<h2>formatNumber, formatDate, parseDate, parseNumber</h2>
	<!-- format: 표현형식변환, parse: 타입변환(Date: 날짜->문자, Number: 숫자->문자)-->

	<h3>formatNumber -> 숫자의 자리수를 지정한다</h3>
	<c:set var="d01" value="2020" />
	<%-- value의 결과를 var라는 변수에 저장 --%>
	<fmt:formatNumber var="v01" value="${d01 }" pattern="000000" />
	<fmt:formatNumber var="v02" value="${d01 }" pattern="0000.00" />

	${v01 }<br/>
	${v02 }
	
	<hr/>
	<h3>formatDate -> 날짜형을 지정된 날짜형태로 변경</h3>
	<%-- 반드시 저장된 데이터가 Date()형이여야 한다 --%>
	<c:set var="d02" value="<%=new Date() %>" />
	<%-- Timestamp: '년-월-일 시간-분-초' 로 나타난대 --%>
	<%=new Timestamp(20230425) %><br/>
	<fmt:formatDate var="v03" value="${d02 }" pattern="yyyyMMdd HHmmss"/>
	<fmt:formatDate var="v04" value="${d02 }" pattern="yyyy년MM월dd일"/>
	<fmt:formatDate var="v05" value="${d02 }" pattern="yyyy-MM-dd HH:mm:ss"/>
	d02(Date()형태): ${d02 }<br/>
	v03: ${v03 }<br/>
	v04: ${v04 }<br/>
	v05: ${v05 }<br/>
	
	<hr/>
	<h3>parseDate -> 문자를 날짜(java.util.Date)로 형변환</h3>
	<c:set var="d03" value="2020/11/04" />
	<!-- pattern은 변수의 형태와 정확히 맞춰서 작성해줘야 함 -->
	<fmt:parseDate var="v06" value="${d03 }" pattern="yyyy/MM/dd" />
	<c:set var="d04" value="2020-11-04 23:12:34"/>
	<fmt:parseDate var="v07" value="${d04 }" pattern="yyyy-MM-dd HH:mm:ss"/>
	d03: ${d03 }<br/>
	v06: ${v06 }<br/><%-- 최종형식이 날짜로 바뀜 --%>
	d04: ${d04 }<br/>
	v07: ${v07 }<br/>
	
	
	<%-- 
	<c:set var="d05" value="<%=new Date() %>" />	
	getTime(): <%=new Date().getTime() %><br/>밀리초로 뽑아내기
	d05.time: ${d05.time }<br/>${}는 get을 제거하고 사용하는거단 
	--%>
	<hr/>
	<h3>parseNumber -> 문자를 숫자형으로 변경가능</h3>
	<fmt:parseNumber var="d06" value="1,100.000" pattern="0,000.000" />
	d06: ${d06 }
	
	
	
	
</body>
</html>
















































