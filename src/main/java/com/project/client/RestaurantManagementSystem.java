package com.project.client;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.controller.AdminPannelOperations;
import com.project.controller.CustomerOperations;

public class RestaurantManagementSystem {
	
    private static final Logger logger = LogManager.getLogger(RestaurantManagementSystem.class);

	
	public static void main(String[] args) {
		
		logger.info("Mian Mehtod Started...");

		Scanner sc = new Scanner(System.in);

		System.out.println("<<==== 🙏 WELL COME TO MAULI RESTAURANT 🙏 ====>>");
		System.out.println(" << ENTER 1 FOR ADMIN PANNAL MANAGEMENT >> ");
		System.out.println(" << ENTER 2 FOR LOGIN AS CUSTOMER >>");
		System.out.println(" << ENTER 3 FOR EXISTING PROJECT >>");
		
		System.out.println("<<=======================================>>");
		int choice = sc.nextInt();

		switch (choice) {

		case 1:
			AdminPannelOperations.loginAsAdmin();
			break;

		case 2:
			CustomerOperations.custFunction();
			break;
			
		case 3:
			
			System.out.println("Exiting Admin Pannel... See You Again 🙏🙏");
			logger.debug("Exiting Admin Pannel... Goodbye!");
			return;

		default:
			System.out.println("ENTER VALID CHOICE ");

		}

		logger.info("Main Method End...");
		sc.close();

	}
}