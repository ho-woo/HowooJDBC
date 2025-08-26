package com.howoo.jdbc.day03.pstmt.member.controll;

import java.util.List;

import com.howoo.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.howoo.jdbc.day03.pstmt.member.model.service.MemberService;
import com.howoo.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberController {
	private MemberDAO mDao;
	private MemberService mService;
	
	public MemberController() {
		mDao = new MemberDAO();
		mService = new MemberService();
	}
	
	public Member findOneById(String memberId) {
		Member member = mService.selectOneById(memberId);
		return member;
	}
	public List<Member> showMemberList(){
		List<Member> mList = mService.selectList();
		return mList;
	}
	
	public int registerMember(Member member) {
		int result = mService.insertMember(member);
		return result;
	}
	public int updateMember(Member member) {
		int result = mService.updateMember(member);
		return result;
	}
	public int deleteMember(String memberId) {
		int result = mService.deleteMember(memberId);
		return result;
	}
	
}
