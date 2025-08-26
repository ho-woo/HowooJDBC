package com.howoo.jdbc.day02.member.view;

import java.util.List;
import java.util.Scanner;

import com.howoo.jdbc.day02.member.controller.MemberController;
import com.howoo.jdbc.day02.member.model.vo.Member;

public class MemberView {
	private MemberController mController;
	
	public MemberView() {
		mController = new MemberController();
	}
	
	// Run에서 시작할 메소드
	Member member;
	public void startProgram() {
		int result;
		finish:
		while(true) {
			int choice = this.printMenu();
			switch(choice) {
			case 1: 
				member = this.addMember();
				result = mController.registerMember(member);
				if(result > 0) {
					printMessage("회원 가입완료");
				}else {					
					printMessage("회원 가입이 완료되지 않았습니다.");
				}
				break;
			case 2: 
				List<Member> mList = mController.showMemberList();
				printAllMember(mList);
				break;
			case 3: 
				String memberId = this.inputMemberId();
				member = mController.findOneById(memberId);
				if(member != null) {					
					this.printOne(member);
				} else {
					this.printMessage("회원 정보가 존재하지 않습니다.");
				}
				break;
			case 4: 
				String memberId1 = this.inputMemberId();
				member = mController.findOneById(memberId1);
				if(member != null) {					
					member = this.modifyMember(memberId1);
					result = mController.updateMember(member);
					if(result > 0) {
						printMessage("회원 정보 수정 완료");
					}else {					
						printMessage("회원 정보 수정이 완료되지 않았습니다.");
					}
				} else {
					this.printMessage("해당 정보가 존재하지 않습니다.");
				}
				break;
			case 5: 
				String memberId2 = this.inputMemberId();
				// 입력받은 아이디로 회원 정보가 존재하는지 체크하기
				member = mController.findOneById(memberId2);
				if(member != null) {					
					// 디비에서 정보 삭제하도록 요청하기
					result = mController.deleteMember(memberId2);
					if(result > 0) {
						printMessage("회원 정보 삭제 완료");
					}else {					
						printMessage("회원 정보 삭제가 완료되지 않았습니다.");
					}
				}else {
					this.printMessage("해당 정보가 존재하지 않습니다.");
				}
				break;
			case 0: 
				this.printMessage("프로그램을 종료합니다.");
				break finish;
			default: this.printMessage("1 ~ 5 사이의 수를 입력해주세요."); break;
			}
		}
	}
	// 아이디 검색시 아이디 임력받기
	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("회원의 아이디 입력 : ");
		String memberId1 = sc.next();
		return memberId1;
	}
	
	// 회원 정보 추가시 회원정보 입력받기
	private Member addMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 정보 입력 ======");
		System.out.print("아이디 :");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.next();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby);
		return member;
		
	}
	// 회원 정보 수정시 수정정보 입력받기
	private Member modifyMember(String memberId) {
		Member member = new Member();
		Scanner sc = new Scanner(System.in);
		member.setMemberId(memberId);
		System.out.print("수정할 비밀번호 : ");
		member.setMemberPwd(sc.next());
		System.out.print("수정할 이름 : ");
		member.setMemberName(sc.next());
		System.out.print("수정할 성별 : ");
		member.setGender(sc.next().charAt(0));
		System.out.print("수정할 나이 : ");
		member.setAge(sc.nextInt());
		System.out.print("수정할 이메일 : ");
		member.setEmail(sc.next());
		System.out.print("수정할 핸드폰 : ");
		member.setPhone(sc.next());
		System.out.print("수정할 주소 : ");
		sc.next();
		member.setAddress(sc.nextLine());
		System.out.print("수정할 취미 : ");
		member.setHobby(sc.next());
		return member;
		
	}
	// 회원 1개 정보 출력
	private void printOne(Member member) {
		System.out.println("아이디:"+member.getMemberId()+
				", 비밀번호:"+member.getMemberPwd()+
				", 이름:"+member.getMemberName()+
				", 성별:"+member.getGender()+
				", 나이:"+member.getAge()+
				", 이메일:"+member.getEmail()+
				", 핸드폰:"+member.getPhone()+
				", 주소:"+member.getAddress()+
				", 취미:"+member.getHobby()+
				", 등록날짜:"+member.getEnrollDate()+"");
	}
	// 회원 전체 정보 출력
	private void printAllMember(List<Member> mList) {
		System.out.println("====== 회원 정보 전체 출력 =======");
		for(Member member: mList) {
			System.out.println("아이디:"+member.getMemberId()+
						", 비밀번호:"+member.getMemberPwd()+
						", 이름:"+member.getMemberName()+
						", 성별:"+member.getGender()+
						", 나이:"+member.getAge()+
						", 이메일:"+member.getEmail()+
						", 핸드폰:"+member.getPhone()+
						", 주소:"+member.getAddress()+
						", 취미:"+member.getHobby()+
						", 등록날짜:"+member.getEnrollDate()+"");
		}
	}
	// 메시지 출력
	private void printMessage(String message) {
		System.out.println(message);
	}
	// 메뉴 출력
	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색 (아이디)");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		return sc.nextInt();
		// 1. 회원가입
		// 2. 회원 전체 조회
		// 3. 회원 검색 (아이디)
		// 4. 회원 정보 수정
		// 5. 회원 정보 삭제
		// 0. 프로그램 종료
	}
}
