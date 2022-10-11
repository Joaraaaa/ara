package org.ara.dbtest;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;


public class JDBCTest {
	// Driver 로드정보
	String driver="com.mysql.jdbc.Driver";
	// url정보
	String url="jdbc:mysql://localhost:3306/exam?serverTimezone=Asia/Seoul";
	// userID정보
	String user="root";
	// password정보
	String pw="1234";
	// 드라이브 로드
	
	// JDBC 연결 테스트 하기위한 메소드
	
	@Test
	public void testConnection()throws Exception {
		Class.forName(driver);
		try(Connection con = DriverManager.getConnection(url,user,pw)){
			System.out.println("DB연결성공");
		}catch(Exception e){
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
	}

}
