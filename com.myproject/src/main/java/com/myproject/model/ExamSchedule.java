package com.myproject.model;

public class ExamSchedule {
    
	 private String school_id;
	 private String subject;
	 private String exam_date;
	 @Override
	public String toString() {
		return "ExamSchedule [school_id=" + school_id + ", subject=" + subject + ", exam_date=" + exam_date
				+ ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
	public ExamSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamSchedule(String school_id, String subject, String exam_date, String start_time, String end_time) {
		super();
		this.school_id = school_id;
		this.subject = subject;
		this.exam_date = exam_date;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getExam_date() {
		return exam_date;
	}
	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	private String start_time;
	 private String end_time;
	 
} 
