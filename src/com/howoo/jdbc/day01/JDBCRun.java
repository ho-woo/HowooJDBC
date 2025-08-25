package com.howoo.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "KH";
	private static final String PASSWORD = "KH";
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기 - 후처리
	 * 6. 자원해제
	 */
	public static void main(String[] args) {
//		insertRun();
		updateRun();
	}
	
	public static void updateRun() {
		String query = "UPDATE EMPLOYEE SET SALARY = 8000000 WHERE EMP_ID = '200'";
		Connection conn = null;
		Statement stmt = null;
		int result = -1;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("데이터 변경이 완료되었습니다.");
			}else {
				System.out.println("데이터 변경이 완료되지 않았습니다.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insertRun() {		
		String query = "INSERT INTO EMPLOYEE VALUES('201','송종기','631126-1548654','song_jk@kh.or.kr','01045686656','D9','J2','S1',6000000,null,'200',to_date('01/09/01','RR/MM/DD'),null,'N')";
		Connection conn = null;
		Statement stmt = null;
		// INSERT할건데 Resultset 필요할까? 안필요함
		int result = -1;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result =stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("데이터 추가가 완료되었습니다.");
			}else {
				System.out.println("데이터 추가가 완료되지 않았습니다.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteRun() {
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID=201";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = -1;
		
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. Statement 생성
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			// 오토커밋 해제 방법
			conn.setAutoCommit(false);
			if(result > 0) {
				conn.commit();
				System.out.println("데이터 삭제가 완료되었습니다.");
			}else {
				conn.rollback();
				System.out.println("데이터 삭제가 완료되지 않았습니다.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void selectRun() {		
		String query = "SELECT * FROM EMPLOYEE";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // 접속하기
			stmt = conn.createStatement(); // 워크시트 열기
			rset = stmt.executeQuery(query); // 컨트롤 + 엔터
			// 후처리
			while(rset.next()) {
				System.out.println("사번 : "+rset.getString("EMP_ID") + "\t이름:"+  rset.getString("EMP_NAME"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원해제
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
