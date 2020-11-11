<%@page import="com.myweb.user.model.UserDAO"%>
<%@page import="com.myweb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	1. 사용자가 입력한 pw값과 id를 기반으로 login() 메서드를 실행시켜서
	비밀번호가 맞는지 검증한다.
	
	2. login() 가 null을 반환하면 "현재 비밀번호를 확인하세요"를 출력 뒤로가기
	   login() 가 값을 가진다면 delete()메서드를 호출해서 삭제를 진행	
	
	3. 삭제 성공시에는 세션을 전부 지우고 index페이지로 리다이렉트
		삭제 실패시에는 마이페이지로 리다이렉트
	*/
	
	
	request.setCharacterEncoding("utf-8");
	String pw = request.getParameter("pw"); //탈퇴 비밀번호
	
	//로그인 되어있는 값
	UserVO vo = (UserVO)session.getAttribute("login");
	String id = vo.getId(); //세션 아이디
	
	UserDAO dao = UserDAO.getInstance();
	UserVO check =  dao.login(id, pw);
	int result = 0;
	
	if(check == null) {//검증;;;;
%>
	<script type="text/javascript">
		alert("현재 비밀번호를 확인하세요");//출력
		history.go(-1);//뒤로가기
	</script>
	
<%
	} else {
		result = dao.delete(id);
		
		if(result == 1) { //삭제 성공
			
			session.invalidate();//세션 삭제
			response.sendRedirect(request.getContextPath());//메인 이동
			
		} else { //삭제 실패
%>
			<script type="text/javascript">
				alert("회원 탈퇴에 실패했습니다");
				location.href = "mypage.jsp";
			</script>
<%
		}
	}
	


%>


















































