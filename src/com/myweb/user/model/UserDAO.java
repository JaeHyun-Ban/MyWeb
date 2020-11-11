package com.myweb.user.model;
import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class UserDAO {
	
	//DAO + VO = Model
	
	//싱글톤 형식
	//UserDAO는 불필요하게 여러개 만들어질 필요가 없기 때문에 
	//한개의 객체만 만들어 지도록  Singleton형식으로 설계한다.
	
	//1. 나 자신의 객체를 생성햇 1개로 제한단다.
	private static UserDAO instance = new UserDAO();
	//>static = '1개'
	
	//2. 직접 객체를 생성할 수 없도록 생성자에도 private
	private UserDAO() {
		//생성자를 생성시 마다 드라이버 로드
		//드라이버 로드
		try {	
//			Class.forName("oracle.jdbc.driver.OracleDriver"); //커넥션 풀
			//커넥션 풀을 얻는 작업
			InitialContext ctx = new InitialContext(); //초기설정 정보가 저장되는 객체
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle"); 
			//java:comp/env/ > 필수 경로
			//DataSource - javax.sql
			
		} catch (Exception e) {
			System.out.println("드라이버 호출 에러 발생");
			e.printStackTrace();
		}
	}
	
	//3. 외부에서 객체생성을 요구할 때 getter메서드를 통해 1번의 객체를 반환
	public static UserDAO getInstance()	{
		return instance;
	}
	
	//---------------------------------------------------------------------
	//DB연결 변수들을 상수로 선언
//	private String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
//	private String uid = "JSP";
//	private String upw = "jsp";
	//>커넥션 풀에 미리 등록함
	
	private DataSource ds;
	
	//멤버변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//회원가입 메서드
	public int join(UserVO vo) {
		
		int result = 0;//결과를 반환할 변수
		
		String sql = "insert into users(id, pw, name, email, address) values(?, ?, ?, ?, ?)";
		
		
		try {
			//1. conn객체 생성
//			conn = DriverManager.getConnection(url, uid, upw);
			conn = ds.getConnection();//커넥션풀을 통해 연결
			
			//2. pstmt객체 생성
			pstmt = conn.prepareStatement(sql);
			//전달받은 vo객체에서 꺼내서 sql문에 대입
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			//3. sql문 실행
			result = pstmt.executeUpdate();//성공 1, 실패 0
			
			
		} catch (Exception e) {
			System.out.println("join()_회원가입 에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);//>rs는 null로 해도 상관없다.
		}
		return result;
	}
	
	
	//중복검사 메서드
	public int checkId(String id) {
		
		int result = 0;
		
		String sql = "select * from users where id = ?";
		try {
			//1. conn객체 생성
//			conn = DriverManager.getConnection(url, uid, upw);
			conn = ds.getConnection();//커넥션풀을 통해 연결
			
			//2. pstmt객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();//select구문 - executeQuery
			
			//rs의 결과가 1줄이거나 1줄이 아닐것
			if(rs.next()) { //1줄 = 중복
				result = 1;
			} else { //x = 중복 x
				result = 0;
			}
		} catch (Exception e) {
			System.out.println("중복검사 메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return result;
	}
	
	public UserVO login(String id, String pw) {
		
		UserVO vo = new UserVO();//결과 반환용
		//Uservo vo = null;//처음을 null로 진행
		
		String sql = "select * from users where id = ? and pw = ?";
		
		try {
			//1.연결 생성
//			conn = DriverManager.getConnection(url, uid, upw);
			conn = ds.getConnection();//커넥션풀을 통해 연결
			
			//2.pstmt객체생성 >>> !!!!!!!
			pstmt = conn.prepareStatement(sql);
			//2. pstmt
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();//select = executeQuery
			
			if(rs.next()) { //존재한다면
				//rs에 담긴 결과를 뽑을 때는
				//rs.getString(컬럼명), rs.getInt(컬럼명), rs.getTimestamp(컬럼명)
				//vo = new UserVO();
				//vo.setId(rs.getString("id"));
				//vo.setName(rs.getString("name"));
				//vo.setEmail(rs.getString("email"));
				//vo.setAddress(rs.getString("address"));
				
				vo.setId(id);
				vo.setPw(pw);
				//이걸 이용하도록 합시다
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setAddress(rs.getString("address"));
			} else { //존재하지 않는다면
				vo = null;
			}
			
		} catch (Exception e) {
			System.out.println("로그인 메서드에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
	//회원정보 업데이트
	public int update(UserVO vo) {
	
		int result = 0; //결과값을 반환
		
		//update - sql생성
		String sql = "update users set pw = ?, name = ?, email = ?, address = ? "
								+ "where id = ?";
		
		try {
			//1. conn객체 생성 - 3개
//			conn = DriverManager.getConnection(url, uid, upw);
			conn = ds.getConnection();//커넥션풀을 통해 연결
			
			//2. pstmt생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getId());
			
			result = pstmt.executeUpdate();
					
			
		} catch (SQLException e) {
			System.out.println("회원정보 수정 메서드에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		

		return result;
	}
	
	
	public int delete(String id) {
		
		int result = 0;
		
		//삭제 sql생성
		String sql = "DELETE FROM users WHERE id = ?";
		
		try {
			//conn객체 생성
//			conn = DriverManager.getConnection(url, uid, upw);
			conn = ds.getConnection();//커넥션풀을 통해 연결
			
			//pstmt생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate(); //insert, update, delete에 사용되는 executeQuery

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
		
	}
	
	
	
	
}


















































