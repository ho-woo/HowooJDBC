package com.howoo.jdbc.day01.student.model;

import java.util.List;
public interface ManageInterface {
	/**
	 * 이름으로 Student 객체 찾기
	 * @param sList
	 * @param name 이름 입력
	 * @return student 객체
	 */
	Student searchOneByName(String name);
	/**
	 * 이름으로 인덱스 찾아주는 메소드
	 * @param sList 검색할 이름
	 * @param name 
	 */
	int searchIndexByName(String name);
	/**
	 * 리스트에 정보를 저장하는 메소드
	 * @param student 저장할 Student 객체
	 */
	int addStudent(Student student);
	/**
	 *  리스트에 정보를 수정하는 메소드
	 * @param index 수정할 객체의 인덱스
	 * @param student 수정할 Student 객체
	 */
	void setStudent(int index, Student student);
	/**
	 * 리스트에 정보를 삭제하는 메소드
	 * @param index 삭제할 객체의 인덱스
	 */
	void removeStudent(int index);
	/**
	 * 테이블에 정보를 삭제하는 메소드
	 * @param name
	 */
	int removeStudent(String name);
	int setStudent(Student student);
	public List<Student> getAllStudents();
}
