package com.myproject.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.helper.FactoryProvider;
import com.myproject.model.Answer;
import com.myproject.model.ExamSchedule;
import com.myproject.model.Question;
import com.myproject.model.School;
import com.myproject.model.Student;
import com.myproject.model.Teacher;

@Repository
public class SchoolDaoImpl implements SchoolDao {

	 @Autowired
	 private JdbcTemplate jdbctemplate;
	
	 	 
	@Transactional
	public int registerSchool(School school) {
		try {
		String sql = "insert into school_details (teacher_name,school_name,school_address,school_email,school_contact)VALUES(?,?,?,?,?)";
		String sql2 = "insert into school_login (school_email,username,password)VALUES(?,?,?)";
		this.jdbctemplate.update(sql2,school.getSchool_email(),school.getSchool_email(),school.getSchool_contact());
		int i = this.jdbctemplate.update(sql,school.getTeacher_name(),school.getSchool_name(),school.getSchool_address(),school.getSchool_email(),school.getSchool_contact());
		      
		     return i;
		}catch(Exception ex)
		{
			return 0;
		}	
	 }
    @Transactional
	public boolean verify_School(String username, String password) {
		String sql = "select * from school_login where username=? and password=?";
		try {
		Teacher ts = jdbctemplate.queryForObject(sql, new RowMapper(){
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {				
					 Teacher t = new Teacher();
					 t.setUsername(rs.getString(1));
                     t.setPassword(rs.getString(3));
				 return t;
				}				 
		    },username,password);
        return (ts.getUsername().equals(username) && ts.getPassword().equals(password))?true:false;
		}catch(Exception ex) {}
		return false;
	 }	
	 @Transactional
	public int save_Schedule(String school_id, String sname, Date sqldate, String estart_time, String eend_time) {
		  String sql = "INSERT INTO exam_schedule (school_id,subject,exam_date,start_time,end_time)VALUES(?,?,?,?,?)";
		  return this.jdbctemplate.update(sql,school_id,sname,sqldate,estart_time,eend_time);
	 }
	 @Transactional
	 public boolean save_Question(Question question,Answer as,String[] ar)
		{		
			try {
			String sql = "insert into question (school_id,qid,question,ans_id) VALUES (?,?,?,?)";
			String sql2 = "insert into answer (school_id,answer,ans_id) VALUES(?,?,?)";
			this.jdbctemplate.update(sql,question.getSchool_id(),question.getQid(),question.getQuestion(),question.getAns_id());
			
			for(int i=0;i<4;i++) {
				  
		     	  this.jdbctemplate.update(sql2,as.getSchool_id(),ar[i],as.getAns_id());		  
			}
			}catch(Exception e) {
				return false;
			}
		  return true;
		}    
    
    
     @Transactional
	 public  List<ExamSchedule> getExamScheduleById(String school_id){
		  String sql = "select * from exam_schedule where school_id=?";
		  List<ExamSchedule> lst = (List<ExamSchedule>) jdbctemplate.query(sql, new RowMapperImpl(),school_id);
		  return lst;
	 }
	
    public void save_Student(String sid,String sub,String un,String pswd)
    {
    	 String sql="INSERT INTO STUDENT (school_id,exam_subject,username,password) VALUES (?,?,?,?)";
    	 this.jdbctemplate.update(sql,sid,sub,un,pswd);
    }
    public void delete_Student(String sub) {
    	 String sql = "DELETE FROM exam_schedule WHERE subject=?";
    	 this.jdbctemplate.update(sql,sub);
    }
	public void deleteESchedule(String id) {
		 String sql = "DELETE FROM exam_schedule WHERE subject=?";
    	 this.jdbctemplate.update(sql,id);
	}
	
	
	
	public Student verify_Student(String username,String password) {
		
		  Session session = FactoryProvider.getFactory().openSession();
		  Transaction tx = session.beginTransaction();
		  
		  Query query = session.createQuery("select username from Student where username=:u and password=:p");
		  query.setParameter("u", username);
		  query.setParameter("p", password);	  
		  List<Student>  stu = query.list();
	
		  tx.commit();
		  session.close();
		  Student s = new Student();
		  if(stu.size()!=0) {
			  for(Student st:stu)
			  {
				  s = st;
				  break;
			  }
		  }
		return s;
	 } 
}
