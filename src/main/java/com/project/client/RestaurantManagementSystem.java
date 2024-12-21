package com.project.client;

import java.util.Scanner;

import com.project.controller.AdminPannelOperations;
import com.project.controller.CustomerOperations;

public class RestaurantManagementSystem {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
//		Logger logger = LoggerApp.getLogger();

//		logger.info("Main Method Started...");

		System.out.println("<<==== 🙏 WELL COME TO MAULI RESTAURANT 🙏 ====>>");
		System.out.println(" << ENTER 1 FOR ADMIN PANNAL MANAGEMENT >> ");
		System.out.println(" << ENTER 2 FOR LOGIN AS CUSTOMER >>");

		System.out.println("<<=======================================>>");
		int choice = sc.nextInt();

		switch (choice) {

		case 1:
			AdminPannelOperations.loginAsAdmin();
			break;

		case 2:
			CustomerOperations.custFunction();
			break;

		default:
			System.out.println("ENTER VALID CHOICE ");

		}

//		logger.info("Main Method End...");
		sc.close();

	}
}

