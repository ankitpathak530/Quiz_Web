package com.myproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	  @Id
	  private String school_id;
	  private String exam_subject;
	  private String username;
	  private String password;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String school_id, String exam_subject, String username, String password) {
		super();
		this.school_id = school_id;
		this.exam_subject = exam_subject;
		this.username = username;
		this.password = password;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getExam_subject() {
		return exam_subject;
	}
	public void setExam_subject(String exam_subject) {
		this.exam_subject = exam_subject;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Student [school_id=" + school_id + ", exam_subject=" + exam_subject + ", username=" + username
				+ ", password=" + password + "]";
	}
	  
	  
}
