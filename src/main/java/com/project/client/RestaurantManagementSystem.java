package com.project.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.nt.controller.AdminPannelOperations;
import com.nt.controller.CustomerOperations;


public class RestaurantManagementSystem  {
	
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		//Logger logger = LoggerApp.getLogger();
		Logger logger = Logger.getLogger(RestaurantManagementSystem.class);
		
		logger.info("Main Method Started...");
		
		System.out.println("<<==== 🙏 WELL COME TO MAULI RESTAURANT 🙏 ====>>");
		System.out.println(" << ENTER 1 FOR ADMIN PANNAL MANAGEMENT >> ");
		System.out.println(" << ENTER 2 FOR LOGIN AS CUSTOMER >>");
		System.out.println("<<=======================================>>");
		int choice=sc.nextInt();
		
		switch(choice) {
		/*case 3: 
			AdminPannelOperations.addAdmin();
			break;
			*/
		
		case 1: 
			AdminPannelOperations.loginAsAdmin();
			break;
			
		case 2:
			CustomerOperations.callCustomerOperations();
			break;
			
			
			
		default :System.out.println("ENTER VALID CHOICE ");
		
		
		}
		
		logger.info("Main Method End...");
		
	}
}
