package com.myweb.board.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class BoardDAO {
	
	//DAO + VO = Model
	
	//싱글톤 형식
	//UserDAO는 불필요하게 여러개 만들어질 필요가 없기 때문에 
	//한개의 객체만 만들어 지도록  Singleton형식으로 설계한다.
	
	//1. 나 자신의 객체를 생성해 1개로 제한한다.
	private static BoardDAO instance = new BoardDAO();
	//>static = '1개'
	
	//2. 직접 객체를 생성할 수 없도록 생성자에도 private
	private BoardDAO() {
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
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private DataSource ds;
	
	//멤버변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//등록하고 끝낼꺼니  void
	public void regist(String writer, String title, String content) {
		
		String sql = "insert into board(bno, writer, title, content) "
						+ "values(board_seq.nextval, ?, ?, ?)";
		
		try {
			//연결객체 생성
			conn = ds.getConnection();
			
			//pstmt생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();//등록 실패시 에러페이지 실행
			
		} catch (Exception e) {
			System.out.println("regist()메서드 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	//게시글 + 페이징 처리 조회
	public ArrayList<BoardVO> getList(int pageNum, int amount){
		
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql ="SELECT * " + 
				"FROM(" + 
				"    SELECT ROWNUM rn, " + 
				"           bno, " + 
				"           writer, " + 
				"           title, " + 
				"           content, " + 
				"           regdate, " + 
				"           hit " + 
				"    FROM (SELECT * " + 
				"          FROM board " + 
				"          ORDER BY bno DESC) " + 
				"    		) " + 
				"WHERE rn > ? AND rn <= ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum - 1)*amount);//(페이지번호-1)*리스트범위
			pstmt.setInt(2, pageNum * amount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");//java.sql
				int hit = rs.getInt("hit");
				
				BoardVO vo = new BoardVO(bno, writer, title, content, regdate, hit);
				list.add(vo); //리스트에 추가		
			}
			
			
		} catch (Exception e) {
			System.out.println("BoardDAO_getList()에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	/* 페이징 처리 전 getList()
	public ArrayList<BoardVO> getList() {
		
		ArrayList<BoardVO> list = new ArrayList<>();
		//★가장 마지막에 들어간 데이터 = "최신글"
		//따라서 번호를 내림차순으로 리스트에 담아야 한다
		
		String sql = "SELECT * FROM board ORDER BY bno DESC";//내림차순으로 조회
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
//				int bno = rs.getInt("bno");
//				String writer = rs.getString("writer");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				Timestamp regdate = rs.getTimestamp("regdate");//java.sql
//				int hit = rs.getInt("hit");
//				
//				BoardVO vo = new BoardVO(bno, writer, title, content, regdate, hit);
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			System.out.println("getList()에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	 */
	
	//전체 게시글 수 - total
	public int getTotal() {
		
		int total = 0;
		
		//count에 변수명을 생성해서 가져온다
		String sql = "SELECT count(*) as total FROM board";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
			}

		} catch (Exception e) {
			System.out.println("getTotal()에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return total;
	}
	
	
	

	public BoardVO getContent(String bno) {
		
		BoardVO vo = new BoardVO();
		
		/*
		 * 번호 기준으로 select구문으로 조회해서 BoardVO에 저장하고,
		 * vo이름으로 화면에 데이터를 전달
		 */
		
		String sql = "SELECT * FROM board WHERE bno = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, bno);
			rs = pstmt.executeQuery();
			
			//글을 한개만 볼꺼라 if로 충분하다
			if(rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
			}
			
			
		} catch (Exception e) {
			System.out.println("getContent()에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}

	public void update(String bno, String title, String content) {

		String sql = "UPDATE board SET title = ?, content = ? WHERE bno = ?";
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			
			pstmt.executeUpdate();
			//단순 업데이트만 할 것
			
			
		} catch (Exception e) {
			System.out.println("BoardDAO_update()에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	}

	public void delete(String bno) { //게시글 삭제 진행, 반환x
		
		String sql = "DELETE FROM board WHERE bno = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();//반환x
			
			
		} catch (Exception e) {
			System.out.println("BoardDAO_delete()에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
	}
	
	//조회수 업데이트 메서드
	//중복을 방지하기 위해서 cookie를 생성해준다
	public void upHit(String bno) {
		
		String sql = "UPDATE board SET hit = hit + 1 WHERE bno = ?";
		
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println("BoardDAO_upHit()에서 에러 발생");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);	
		}
		
		
	}
	
	
	
	
	
	
}


















































