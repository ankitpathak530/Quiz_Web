package com.myproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.model.Admin;


@Repository
public class AdminDaoImpl implements AdminDao {
 
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Transactional
	public boolean isMatched(Admin admin)
	{
		String un = admin.getUsername();
		String pd = admin.getPassword();
		
		 String query = "select * from adminlogin where username=? and password=? ";
		 List<Admin> ls =  jdbctemplate.query(query, new Object[] {un,pd} , new RowMapper(){

			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				 Admin stu = new Admin();
					 stu.setName(rs.getString(1));
					 stu.setUsername(rs.getString(2));
					 stu.setPassword(rs.getString(3));
					 stu.setContact(rs.getLong(4));			 
				return stu;
			}
						 
		 });
		 if(ls.size()==0)
			 return false;
		 return true;
		 
	}
}
