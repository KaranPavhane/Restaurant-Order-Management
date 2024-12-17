package com.project.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project.commons.LoggerApp;
import com.project.staticMethods.AdminPannelOperations;
import com.project.staticMethods.CustomerOperations;

public class RestaurantManagementSystem  {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		Logger logger = LoggerApp.getLogger();
		
		logger.info("Main Method Started...");
		
		System.out.println("<<==== 🙏 WELL COME TO MAULI RESTAURANT 🙏 ====>>");
		System.out.println(" << ENTER 1 FOR ADMIN PANNAL MANAGEMENT >> ");
		System.out.println(" << ENTER 2 FOR LOGIN AS CUSTOMER >>");
		System.out.println("<<=======================================>>");
		int choice=sc.nextInt();
		System.out.println("Rusuuuuuuuuuuuuuuuuuuu kadi yenar");
		
		switch(choice) {
		case 3: 
			AdminPannelOperations.addAdmin();
			break;
			
		
		case 2:
			CustomerOperations.callCustomerOperations();
			break;
			
			
		case 1: 
			AdminPannelOperations.loginAsAdmin();
			break;
		default :System.out.println("ENTER VALID CHOICE ");
		
		
		}
				
		logger.info("Main Method End...");
		
	}
}



/*
 * create procedure add_menu_procedure(menu varchar(200), menu_price int,
 * disception varchar(400), cat_id int(5)) begin insert into menu_master
 * values('0', menu, menu_price, disception, cat_id); end //
 */