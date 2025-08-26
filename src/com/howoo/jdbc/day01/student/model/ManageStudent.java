package com.howoo.jdbc.day01.student.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageStudent implements ManageInterface{
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "HOWOOJDBC";
	private static final String PASSWORD = "HOWOOJDBC";
	
	private List<Student> sList;
	
	public ManageStudent() {
	}

	@Override
	public Student searchOneByName(String name) {
		Student student = new Student();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query ="SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '"+name+"'";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				student.setName(rset.getString("STUDENT_NAME"));
				student.setFirstScore(rset.getInt("FIRST_SCORE"));
				student.setSecondScore(rset.getInt("SECOND_SCORE"));
			}else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	@Override
	public int searchIndexByName(String name) {
		for(int i = 0; i < sList.size(); i++) {
			if(sList.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1; // index가 0이면 첫번째 값이기 때문에 -1로 리턴
	}
	
	public List<Student> getAllStudents(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM STUDENT_TBL";
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// 후처리
			// rset에 있는 필드의 값을 Student의 필드에 하나씩 넣어줌
			// Student 객체는 List에 저장해서 보내줌
			// sList를 생성자에서 한번만 초기화 하는 것이 아니라
			// getAllStudents() 메소드가 동작할 때마다 초기화해서 값이 누적되지 않도록 함.
			sList = new ArrayList<Student>();
			while(rset.next()) {
				// rset.getString("STUDENT_NAME");
				// rset.getString("FIRST_SCORE");
				// rset.getString("SECOND_SCORE");
				Student student = new Student();
				student.setName(rset.getString("STUDENT_NAME"));
				student.setFirstScore(rset.getInt("FIRST_SCORE"));
				student.setSecondScore(rset.getInt("SECOND_SCORE"));
				sList.add(student);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sList;
	}

	@Override
	public int addStudent(Student student) {
		// 정보가 입력된 student 객체를 sList에 저장
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
//		sList.add(student);
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getName()+"', "+student.getFirstScore()+", "+student.getSecondScore()+")";
			result = stmt.executeUpdate(query); // int를 쓰는 이유 -> (1)행이 삽입되었습니다.
			stmt.close();
			conn.close();
					
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
		return result;
	}

	@Override
	public void setStudent(int index, Student student) {
		sList.set(index, student);
	}

	@Override
	public void removeStudent(int index) {
		sList.remove(index);
	}

	@Override
	public int removeStudent(String name) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query ="DELETE FROM STUDENT_TBL WHERE STUDENT_NAME = '"+name+"'";
			result = stmt.executeUpdate(query);
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
		return result;
	}

	@Override
	public int setStudent(Student student) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "UPDATE STUDENT_TBL SET FIRST_SCORE = '"+student.getFirstScore()+"', SECOND_SCORE = '"
					+student.getSecondScore()+"' WHERE STUDENT_NAME = '"+student.getName()+"'";
			result = stmt.executeUpdate(query);

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
		return result;
	}
	

}
