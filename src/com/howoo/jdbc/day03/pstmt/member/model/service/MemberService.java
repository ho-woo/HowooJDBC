package com.howoo.jdbc.day03.pstmt.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.howoo.jdbc.day03.pstmt.member.common.JDBCTemplate;
import com.howoo.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.howoo.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberService {
	
	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		//JDBCTemplate 싱글톤으로 객체 생성
		jdbcTemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO();
	}
	
	public List<Member> selectList(){
		List<Member> mList = null;
		try {
			// 서비스에서 연결 생성
			Connection conn = jdbcTemplate.getConnection();
			// 생성된 연결을 DAO에 전달
			mList = mDao.selectList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}

	public Member selectOneById(String memberId) {
		Member member = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
			member = mDao.selectOneById(memberId, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	public int insertMember(Member member) {
		int result = 0;
		try {
			Connection conn = jdbcTemplate.getConnection();
			result = mDao.insertMember(member, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = 0;
		try {
			Connection conn = jdbcTemplate.getConnection();
			result = mDao.updateMember(member, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		try {
			Connection conn = jdbcTemplate.getConnection();
			result = mDao.delectMember(memberId, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
