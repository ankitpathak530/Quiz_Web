
package com.myproject.model;

public class Question {

	 private String school_id;
	 private String qid;
	 private String question;
	 private String ans_id;
	 
	public Question(String school_id, String qid, String question, String ans_id) {
		super();
		this.school_id = school_id;
		this.qid = qid;
		this.question = question;
		this.ans_id = ans_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	@Override
	public String toString() {
		return "Question [school_id=" + school_id + ", qid=" + qid + ", question=" + question + ", ans_id=" + ans_id
				+ "]";
	}

	public String getQid() {
		return qid;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAns_id() {
		return ans_id;
	}
	public void setAns_id(String ans_id) {
		this.ans_id = ans_id;
	}
	 
	 
	 
}
