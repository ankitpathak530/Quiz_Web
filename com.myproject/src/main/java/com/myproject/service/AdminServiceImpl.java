package com.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dao.AdminDaoImpl;
import com.myproject.model.Admin;

@Service
public class AdminServiceImpl implements AdminService {
    
	@Autowired
	private AdminDaoImpl admindaoimpl;
	
	public boolean isMatched(Admin admin)
	{
		return admindaoimpl.isMatched(admin);
	}
}
