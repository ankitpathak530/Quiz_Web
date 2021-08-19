package com.myproject.controller;


import org.hibernate.query.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;

import com.myproject.helper.FactoryProvider;
import com.myproject.model.Admin;
import com.myproject.model.Answer;
import com.myproject.model.ExamSchedule;
import com.myproject.model.Question;
import com.myproject.model.SaveStudent;
import com.myproject.model.School;
import com.myproject.model.Student;
import com.myproject.model.Teacher;
import com.myproject.service.AdminServiceImpl;
import com.myproject.service.SchoolServiceImpl;

@Controller
public class MyController implements WebMvcConfigurer{


	private String sid;
	
	 @Autowired
	 private AdminServiceImpl adminserviceimpl;
	 
	 @Autowired
	 private SchoolServiceImpl schoolserviceimpl;

	 @RequestMapping("/")
	 public String home() {		 
		 return "index";
	 }
	@RequestMapping("/quants")
	public String Quants() {
		return "QuantsDashboard";
	}
	@RequestMapping("/verbal")
	public String Verbal() {
		return "VerbalDashboard";
	}
	@RequestMapping("/reasoning")
	public String Reasoning() {
		return "ReasoningDashboard";
	}
	@RequestMapping("/java")
	public String Java() {
		return "JavaDashboard";
	}
	@RequestMapping("/student_index")
	public String student_index() {
		return "student_index";
	}
	 @RequestMapping("/school_index")
	 public String viewSchoolIndex(Model model){

		    ArrayList<ExamSchedule> ex = (ArrayList<ExamSchedule>) schoolserviceimpl.getExamScheduleById(this.sid);
				    
		    model.addAttribute("examSchedule",ex);
			model.addAttribute("school_id",sid);
			model.addAttribute("result","success");
		 return "school_index";				 
	   }
	 @RequestMapping(value="/delete",method=RequestMethod.POST)
	  public String deleteSchedule(@RequestParam("subject") String subject,@RequestParam("sid") String sid) {
			 this.sid=sid;
			 schoolserviceimpl.deleteSchedule(subject);
		   	 return "redirect:/school_index";
	  }
	  @RequestMapping(value="/setSchedule" , method=RequestMethod.POST)
	   public String setSchedule(@RequestParam("school_id") String school_id,@RequestParam("sname") String sname,
		    @RequestParam("edate") Date edate,@RequestParam("estart_time") String estart_time,
			@RequestParam("eend_time") String eend_time,Model model,HttpServletRequest request)
		{      	 	           
			    Date sqldate = new java.sql.Date(edate.getTime());
				schoolserviceimpl.saveSchedule(school_id,sname,sqldate,estart_time,eend_time);		     
			    return "redirect:/school_index";			         		
		}
		    
	 
	 
	 
	 
	 
	    
	    
	    
		@RequestMapping(value="/verify_school",method=RequestMethod.POST)
		public String verify_school(@ModelAttribute Teacher teacher,@RequestParam("username") String school_id ,Model model,HttpServletRequest request)
		{	
			RedirectView redirectview = new RedirectView();
		     if(schoolserviceimpl.verifySchool(teacher.getUsername(), teacher.getPassword()))
		     {	
				    this.sid=school_id;	   
					redirectview.setUrl(request.getContextPath()+"/school_index");
					return "redirect:/school_index";
		      }    
		      else
		      {
		    	  model.addAttribute("result","error");
		    	  redirectview.setUrl(request.getContextPath()+"/schoolLogin");
		          return "schoolLogin";
		      }			
		}	  
		
		@RequestMapping(value="/verifyStudent",method=RequestMethod.POST)
		public String verifyStudent(@ModelAttribute Student student,Model m)
		{
		   Student stu = schoolserviceimpl.verifyStudent(student.getUsername(), student.getPassword());
			System.out.println("Verified ok "+stu);
		   if(stu.getUsername() != null) {
				  m.addAttribute("username",stu.getUsername());
				  m.addAttribute("school_id",stu.getSchool_id());
				  return "student_index";
			  }
			return "studentLogin";  
		}
		
		
		
		
		 @RequestMapping("/schoolLogin")
		 public String schoolLogin()
		 {	
			 return "schoolLogin";
		 }
		 @RequestMapping("/school_registration")
		 public String school_registration()
		 {
			 return "school_registration";
		 }
		 @RequestMapping(value="/school_register",method=RequestMethod.POST)
			public String register_school(@ModelAttribute School school,Model model)
			{
			     int i = this.schoolserviceimpl.createSchool(school);
			     if(i!=0)
			     {
			    	 model.addAttribute("result","success");
			    	 return "schoolLogin";
			     }
			     else {
			    	 model.addAttribute("result","error");
			    	 return "schoolLogin";
			     }	
			}
		
	
		@RequestMapping(value="/saveQuestion",method=RequestMethod.POST)
		public String saveQuestion(@RequestParam("school_id") String school_id,@RequestParam("qid") String qid,@RequestParam("question")String question,
				@RequestParam("answer1") String answer1,@RequestParam("answer2") String answer2,@RequestParam("answer3") String answer3,
				@RequestParam("answer4") String answer4,@RequestParam("correct_answer") String correctanswer,Model model)
		{
			    Question qs = new Question(school_id,qid,question,correctanswer);
			    Answer as = new Answer(school_id,answer1,answer2,answer3,answer4,qid);
				ArrayList<ExamSchedule> ex = (ArrayList<ExamSchedule>) schoolserviceimpl.getExamScheduleById(school_id);				 
			    model.addAttribute("examSchedule",ex);
				model.addAttribute("school_id",school_id);
				
			 if(schoolserviceimpl.saveQuestion(qs,as,new String[] {answer1,answer2,answer3,answer4}))
			        model.addAttribute("result","success");		        
			 else 
			        model.addAttribute("result","error");
			 
		   return "redirect:/school_index";
		}	
	
		@RequestMapping(value="/saveStudent",method=RequestMethod.POST)
        public String saveStudent(@ModelAttribute SaveStudent s,Model model) {
        	
			ArrayList<ExamSchedule> ex = (ArrayList<ExamSchedule>) schoolserviceimpl.getExamScheduleById(s.getSchool_id());				 
		    model.addAttribute("examSchedule",ex);
			model.addAttribute("school_id",s.getSchool_id());
	        
		 try {
			if(!s.getUsername1().equals("") && !s.getPassword1().equals("")) {
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername1(), s.getPassword1());
			}
			if(!s.getUsername2().equals("") && !s.getPassword2().equals("")) {
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername2(), s.getPassword2());
			}
			if(!s.getUsername3().equals("") && !s.getPassword3().equals("")) {		
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername3(), s.getPassword3());
		    }
			if(!s.getUsername4().equals("") && !s.getPassword4().equals("")) {
		    	this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername4(), s.getPassword4());
			}
		    if(!s.getUsername5().equals("") && !s.getPassword5().equals("")) {
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername5(), s.getPassword5());
		    }
			if(!s.getUsername6().equals("") && !s.getPassword6().equals("")) {
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername6(), s.getPassword6());
			}
			if(!s.getUsername7().equals("") && !s.getPassword7().equals("")) {
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername7(), s.getPassword7());
			}
			if(!s.getUsername8().equals("") && !s.getPassword8().equals("")) {
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername8(), s.getPassword8());
			}
			if(!s.getUsername9().equals("") && !s.getPassword9().equals("")) {	
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername9(), s.getPassword9());
			}
			if(!s.getUsername10().equals("") && !s.getPassword10().equals("")) {
				this.schoolserviceimpl.saveStudent(s.getSchool_id(), s.getExam_subject(), s.getUsername10(), s.getPassword10());
			}	
		        model.addAttribute("result","success");
	        
		 }catch(Exception e) {
		       model.addAttribute("result","success");        
		  }  		
		return "redirect:/school_index";
       }
		
        
		
		
	 @RequestMapping("/studentLogin")
	 public String studentLogin()
	 {
		 return "studentLogin";
	 }
	
	 @RequestMapping("/admin")
	 public String admin()
	 {
		 return "adminLogin";
	 }
	 @RequestMapping("/about")
	 public String about()
	 {
		 return "about";
	 }
	 @RequestMapping("/contact")
	 public String contact()
	 {
		 return "contact";
	 }
		 
	 
	@RequestMapping(value="/verifyAdminUser",method= RequestMethod.POST)
	public String verifyadmin(@ModelAttribute Admin admin,Model model)
	{
		if(this.adminserviceimpl.isMatched(admin)) {
			model.addAttribute("result", "Success");
			return "adpg1";
		}
		else {
			model.addAttribute("result", "Invalid username or password");
			return "adminLogin";
	       }
	}
}
