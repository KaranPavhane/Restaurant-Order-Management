package com.project.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project.commons.DBConfiguration;
import com.project.commons.LoggerApp;
import com.project.model.AdminModel;
import com.project.service.AdminService;
import com.project.service.AdminServiceImpl;

public class RestaurantManagementSystem  {
	
	public static void main(String[] args) {
		
//		System.out.println("hello");
		Scanner sc=new Scanner(System.in);
		Logger logger = LoggerApp.getLogger();
		DBConfiguration.getInstance();
		AdminService adminService=new AdminServiceImpl();
		logger.info("main method execute........");
		System.out.println("Enter Your Username ");
		String username=sc.nextLine();
		System.out.println("Enter Your Password ");
		String password=sc.nextLine();
		System.out.println("Enter Your Date of Birth ");
		String dob=sc.nextLine();
		AdminModel model=new AdminModel();
		model.setAdmin_username(username);
		model.setAdmin_pass(password);
		model.setDob(dob);
		
		boolean b=adminService.isAddNewAdmin(model);
		if(b) {
			System.out.println("New Admin added.......");
		}else {
			System.out.println("not added ");
		}
		
		
		
	}
}
