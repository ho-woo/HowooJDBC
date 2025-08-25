package com.howoo.jdbc.day02.member.controller;

import java.util.List;

import com.howoo.jdbc.day02.member.model.dao.MemberDAO;
import com.howoo.jdbc.day02.member.model.vo.Member;

public class MemberController {
	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public Member findOneById(String memberId) {
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	public List<Member> showMemberList(){
		List<Member> mList = mDao.selectList();
		return mList;
	}
	
	public int registerMember(Member member) {
		int result = mDao.insertMember(member);
		return result;
	}
	public int updateMember(Member member) {
		return 0;
	}
	public int deleteMember(String memberId) {
		return 0;
	}
	
}
