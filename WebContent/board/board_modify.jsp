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

input[type=button] {
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}
</style>

<%@ include file="../include/header.jsp"%><%-- header --%>

<div align="center" class="div_center">
	<h3>게시판 글 수정 페이지</h3>
	<hr>

	<form action="update.board" method="post">
		<%-- 반드시 필요한 값을 숨겨서 보내는 input- 'hidden' --%>
		<%-- form안에서 화면에 보이지 않지만 반드시 넘겨줘야 하는 값을 숨겨서 보내주는 역활 --%>
		<input type="hidden" name="bno" value="${vo.bno }">
		<input type="hidden" name="writer" value="${vo.writer }">
		
		<table border="1" width="500">

			<tr>
				<td>글 번호</td>
				<td><input type="text" name="bno" value="${vo.bno }" disabled></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" value="${sessionScope.user.name }" disabled ></td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td><input type="text" name="title" value="${vo.title }"></td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea rows="10" style="width: 95%;" name="content">
					${vo.content }
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정 하기">&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href='list.board'">
				</td>
			</tr>

		</table>
	</form>

</div>


<%@ include file="../include/footer.jsp"%>
















