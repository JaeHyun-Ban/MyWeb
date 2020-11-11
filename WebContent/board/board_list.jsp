<%@page import="com.myweb.board.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>




<%@ include file="../include/header.jsp"%>

	<div class="container">
		<h3>My Web게시판</h3>
		
		<%-- 한번에 볼 게시글의 수 
			일반사용자 보다는  기업에서 많은 데이터를 확인하고 싶을 때 사용한다--%>
		<select onchange="change(this)" ><%-- onchange: select가 바뀔 때를 감지한다 --%>
		<%-- js의 this는 select나 자체를 통째로 지칭한다 --%>
		
			<%-- 자바스크립트를 이용, select가 바뀔 때를 감지한다--%>
			<option value="10" ${pageVO.amount == 10 ? 'selected' : '' }>10개씩 보기</option>
			<%--> selected: 미리 기본값으로 지정해 놓는다 > 덕분에 amount가 변경되는 것을 감지해서 변경할 수 있다 --%>
			<option value="20" ${pageVO.amount == 20 ? 'selected' : '' }>20개씩 보기</option>
			<option value="50" ${pageVO.amount == 50 ? 'selected' : '' }>50개씩 보기</option>
			<option value="100" ${pageVO.amount == 100 ? 'selected' : '' }>100개씩 보기</option>
		</select>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<%-- 향상된 for문을 사용 >> 향상for문을 좀 더 연습하자 ※쩨발※ --%>
			<c:forEach var="vo" items="${list }">
			<tbody>
				<tr>
					<td>${vo.bno }</td>
					<td>${vo.writer }</td>
					<td><a href="content.board?bno=${vo.bno }">${vo.title }</a></td>
					<td><fmt:formatDate value="${vo.regdate }" pattern="yyyy년MM월mm일"/></td>
					<td>${vo.hit }</td>
				</tr>
			</tbody>
			</c:forEach>
			<tbody>
				<tr>
					<%-- 게시글 바 --%>
					<td colspan="5" align="center">
						<ul class="pagination pagination-sm">
							<%-- 이전버튼 활성화 --%>
							<c:if test="${pageVO.prev }">
							<li><a href="list.board?pageNum=${pageVO.startPage -1 }&amount=${pageVO.amount }">이전</a></li>
							</c:if>
							<c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage }">
							<%-- 현제 보고있는곳과 pageNum이 같다면 active 활성화 --%><%-- active = 현재 페이지색를 색칠해줌 --%>
							<li class="${num eq pageVO.pageNum ? 'active' : '' }">
								<%-- 아래 구간 잘 기억할 것 --%>
								<a href="list.board?pageNum=${num }&amount=${pageVO.amount}">${num }</a>
							</li>
							</c:forEach>
							<%-- 다음버튼 활성화 --%>
							<c:if test="${pageVO.next }">
							<li><a href="list.board?pageNum=${pageVO.endPage + 1 }&amount=${pageVO.amount }">다음</a></li>
							</c:if>
						</ul> 
						<input type="button" value="글 작성" class="btn btn-default pull-right" onclick="location.href='write.board'">

					</td>
				</tr>
			</tbody>

		</table>
	</div>
	
	<!-- 자바스크립트는 가장 아래쪽에 작성해 준다. -->
	<script type="text/javascript">
		function change(a){
			console.log(a)
			console.log(a.value)//내가 지목한 select의 option이 가진 값(value)을 확인 할 수 있다
			location.href="list.board?pageNum=1&amount=" + a.value;//
		
		}
	</script>
	
	
<%@ include file="../include/footer.jsp" %>





