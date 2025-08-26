package com.howoo.jdbc.day01.student.view;

import java.util.List;
import java.util.Scanner;

import com.howoo.jdbc.day01.student.model.Student;

public class ViewStudent implements ViewInterface{
	
	private Scanner sc;
	public ViewStudent() {
		sc = new Scanner(System.in);
	}

	@Override
	public int printMenu() {
		System.out.println("===== 학생 성적관리 프로그램 =====");
		System.out.println("1. 학생 정보 입력");
		System.out.println("2. 학생 정보 이름 검색");
		System.out.println("3. 학생 정보 전체 출력");
		System.out.println("4. 학생 정보 수정");
		System.out.println("5. 학생 정보 삭제");
		System.out.println("6. 재평가 대상 여부 확인");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int menu = sc.nextInt();
		return menu;
	}

	@Override
	public void displayMsg(String msg) {
		System.out.println(msg);
		
	}

	@Override
	public String inputName() {
		// 검색할 이름을 입력받음
				System.out.print("이름 입력 : ");
				String name = sc.next();
				return name;
	}

	@Override
	public Student inputStudent() {
		System.out.println("===== 학생 정보 입력 =====");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("1차 점수 : ");
		int firstscore = sc.nextInt();
		System.out.print("2차 점수 : ");
		int secondScore = sc.nextInt();
		Student student = new Student(name, firstscore, secondScore);
		return student;
	}

	@Override
	public void printStudents(List<Student> sList) {
		System.out.println("===== 학생 전체 정보 출력 =====");
		for(Student student: sList) {
			System.out.println(student.getName()+" 학생의 1차점수는 "
						+ student.getFirstScore() + "점이고 2차점수는 "
						+ student.getSecondScore() + "점입니다.");

		}
		
	}

	@Override
	public void printStudent(Student student) {
		System.out.println("====== 학생 정보 출력 ======");
		System.out.println(student.getName()+" 학생의 1차점수는 "
						+ student.getFirstScore() + "점이고 2차점수는 "
							+ student.getSecondScore() + "점입니다.");
		
	}

	@Override
	public Student modifyStudent(String name) {
		Student student = new Student();
		student.setName(name);
		System.out.print("수정할 1차 점수 : ");
		student.setFirstScore(sc.nextInt());
		System.out.print("수정할 2차 점수 : ");
		student.setSecondScore(sc.nextInt());
		return student;
	}

}
