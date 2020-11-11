package com.myweb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			
			if(conn!=null)conn.close();
			if(pstmt!=null)pstmt.close();
			if(rs!=null)rs.close();
			
		} catch (SQLException e) {
			
			System.out.println("jdbcUtil_close()에서 에러 발생");
			e.printStackTrace();
		}
		
	}
	
}
