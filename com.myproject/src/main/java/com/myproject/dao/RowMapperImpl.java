package com.myproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myproject.model.ExamSchedule;

public class RowMapperImpl implements RowMapper<ExamSchedule>{

	public ExamSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
		  ExamSchedule es = new ExamSchedule();
		  es.setSchool_id(rs.getString(1));
		  es.setSubject(rs.getString(2));
		  es.setExam_date(rs.getString(3));
		  es.setStart_time(rs.getString(4));
		  es.setEnd_time(rs.getString(5));
		return es;
	}

	

}
