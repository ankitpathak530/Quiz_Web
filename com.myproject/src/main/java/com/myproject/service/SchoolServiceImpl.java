package com.myproject.service;

import java.sql.Date;
import java.util.List;

import javax.swing.event.RowSorterListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dao.SchoolDaoImpl;
import com.myproject.model.Answer;
import com.myproject.model.ExamSchedule;
import com.myproject.model.Question;
import com.myproject.model.School;
import com.myproject.model.Student;



@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolDaoImpl schooldaoimpl;
	 
	 
	public int createSchool(School school)	{
		return schooldaoimpl.registerSchool(school);
	}
	public boolean verifySchool(String username, String password) {
		  return this.schooldaoimpl.verify_School(username, password);
	}
     public int saveSchedule(String school_id, String sname, Date sqldate, String estart_time, String eend_time) {	
		return schooldaoimpl.save_Schedule(school_id, sname, sqldate, estart_time, eend_time);		
	 }
     public boolean saveQuestion(Question question,Answer answer,String[] ar)	  {
		  return this.schooldaoimpl.save_Question(question,answer,ar);
	  }
    public List<ExamSchedule> getExamScheduleById(String school_id){
    	  List<ExamSchedule> al = this.schooldaoimpl.getExamScheduleById(school_id);
    	  return al;
    }
    public Student verifyStudent(String username,String password) {
    	return this.schooldaoimpl.verify_Student(username,password);
    }
    
    
    
    public void saveStudent(String sid,String sub,String un,String pswd) {
    	 this.schooldaoimpl.save_Student(sid,sub,un,pswd);
    }
    public void deleteStudent(String sub)
    {
    	this.schooldaoimpl.delete_Student(sub);
    }
	public void deleteSchedule(String id) {
		// TODO Auto-generated method stub
		this.schooldaoimpl.deleteESchedule(id);
	}
	
	
	
	
	
}
