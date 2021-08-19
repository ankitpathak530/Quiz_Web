package com.myproject.model;

public class Answer {
    
	private String school_id;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	
	private String ans_id;
	
	
	public String getSchool_id() {
		return school_id;
	}
	public Answer(String school_id, String answer1, String answer2, String answer3, String answer4, String ans_id) {
		super();
		this.school_id = school_id;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.ans_id = ans_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer) {
		this.answer1 = answer;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAns_id() {
		return ans_id;
	}
	public void setAns_id(String ans_id) {
		this.ans_id = ans_id;
	}
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Answer [school_id=" + school_id + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3="
				+ answer3 + ", answer4=" + answer4 + ", ans_id=" + ans_id + "]";
	}
	
	
}
