package com.myproject.model;

public class School {
	
   private String teacher_name;
   private String school_name;
   private String school_address;
   private String school_email;
   private String school_contact;
   
   
public String getTeacher_name() {
	return teacher_name;
}
public void setTeacher_name(String teacher_name) {
	this.teacher_name = teacher_name;
}
public String getSchool_name() {
	return school_name;
}
public void setSchool_name(String school_name) {
	this.school_name = school_name;
}
public String getSchool_address() {
	return school_address;
}
public void setSchool_address(String school_address) {
	this.school_address = school_address;
}
public String getSchool_email() {
	return school_email;
}
public void setSchool_email(String school_email) {
	this.school_email = school_email;
}
public String getSchool_contact() {
	return school_contact;
}
public void setSchool_contact(String school_contact) {
	this.school_contact = school_contact;
}
public School() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "School [teacher_name=" + teacher_name + ", school_name=" + school_name + ", school_address="
			+ school_address + ", school_email=" + school_email + ", school_contact=" + school_contact + "]";
}
   
   
}
