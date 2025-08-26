package com.howoo.jdbc.day03.pstmt.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.howoo.jdbc.day03.pstmt.member.common.JDBCTemplate;
import com.howoo.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberDAO {
	
	public List<Member> selectList(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		String query = "SELECT * FROM MEMBER_TBL ORDER BY MEMBER_ID ASC";
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		mList = new ArrayList<Member>();
		
		while(rset.next()) {
			Member member = this.rsetToMember(rset);
			mList.add(member);
		}
		return mList;
	}
	
	public Member selectOneById(String memberId, Connection conn) throws SQLException {
		Member member = new Member();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?"; // 위치홀더, 값이 들어갈 위치
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberId);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			member = this.rsetToMember(rset);
		} else {
			return null;
		}
		conn.close();
		pstmt.close();
		rset.close();
		return member;
	}
	
	public int insertMember(Member member, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		int result = 0;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPwd());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getGender()+""); // 문자열로 바꾸기
//			pstmt.setString(4, String.valueOf(member.getGender()); // 문자열로 바꾸기
		pstmt.setInt(5, member.getAge());
		pstmt.setString(6, member.getEmail());
		pstmt.setString(7, member.getPhone());
		pstmt.setString(8, member.getAddress());
		pstmt.setString(9, member.getHobby());
		result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		return result;
	}
	
	public int updateMember(Member member, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?, MEMBER_NAME = ?, GENDER = ?, AGE =?, EMAIL =?, PHONE = ?, ADDRESS = ?,"
											+ "HOBBY = ? WHERE MEMBER_ID = ?";
		int result = 0;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, member.getMemberPwd());
		pstmt.setString(2, member.getMemberName());
		pstmt.setString(3, member.getGender()+""); // 문자열로 바꾸기
//			pstmt.setString(3, String.valueOf(member.getGender()); // 문자열로 바꾸기
		pstmt.setInt(4, member.getAge());
		pstmt.setString(5, member.getEmail());
		pstmt.setString(6, member.getPhone());
		pstmt.setString(7, member.getAddress());
		pstmt.setString(8, member.getHobby());
		pstmt.setString(9, member.getMemberId());
		result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	public int delectMember(String memberId, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		int result = 0;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberId);
		result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	private Member rsetToMember(ResultSet rset) throws SQLException {
		String memberId   = rset.getString("MEMBER_ID");
		String memberPwd  = rset.getString("MEMBER_PWD");
		String memberName = rset.getString("MEMBER_NAME");
		char gender		  = rset.getString("GENDER").charAt(0);
		int age			  = rset.getInt("AGE");
		String email	  = rset.getString("EMAIL");
		String phone	  = rset.getString("PHONE");
		String address	  = rset.getString("ADDRESS");
		String hobby	  = rset.getString("HOBBY");
		Date enrollDate	  = rset.getDate("ENROLL_DATE");
		Member member = new Member(memberId, memberPwd, memberName
				, gender, age, email, phone, address, hobby, enrollDate);
		return member;
	}
}
