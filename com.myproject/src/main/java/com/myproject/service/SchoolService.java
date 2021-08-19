package com.myproject.service;

import java.util.ArrayList;

import com.myproject.model.School;

public interface SchoolService {

	public int createSchool(School school);
	public boolean verifySchool(String username, String password);
	
}
