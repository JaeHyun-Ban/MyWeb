<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
input[type=submit] {
	padding: 4px 4px;
	background: #8da8db;
	border: 0 none;
	cursor: pointer;
	/*
	border-radius는 요소 테두리 경계의 꼭짓점을 둥글게 만든다
	  하나의 값을 사용 = 원형 꼭짓점, 두 개의 값을 사용 = 타원형 꼭짓점	
	*/
	border-radius: 4px;
	border-radius: 4px;
}
</style>

<%@ include file="../include/header.jsp"%>

<div align="center" class="div_center">
	<h3>게시판 글 작성 페이지</h3>
	<hr>

	<form action="regist.board" method="post">
		<table border="1" width="500">
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="writer" size="10" value="${sessionScope.user.id }" required>
				</td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td>
					<%-- 글제목 3글자 이상 작성  - pattern="[A-Za-z가핳0-9]{3}"--%>
					<input type="text" name="title"  required placeholder="3글자 이상" >
				</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea rows="10" style="width: 95%;" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="작성 완료"> 
					<input type="button" value="목록" class="btn btn-default" onclick="location.href='list.board'">
				</td>
			</tr>

		</table>
	</form>
	
</div>

<%@ include file="../include/footer.jsp"%>
























