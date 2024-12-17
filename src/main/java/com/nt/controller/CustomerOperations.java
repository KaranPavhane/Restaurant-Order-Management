package com.nt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.model.CategeryModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;
import com.project.service.CustomerServiceImpl;
import com.project.service.ICustomerService;

public class CustomerOperations {

	static ICustomerService custService=new CustomerServiceImpl();
	static Scanner sc=new Scanner(System.in);
	
	public static void callCustomerOperations() {
		
		System.out.println("ENTER YOUR NAME");
		String cust_Name=sc.nextLine();
		System.out.println("Enter Your Contact");
		String contact=sc.nextLine();
		
		System.out.println("<<<=== SELECT YOUR CATEGERIES ===>>>");
		callViewAllCategery();
		
		String cat_Name=sc.nextLine();
		getMenuByCategery(cat_Name);
		
		List<MenuModel> menuList=new ArrayList<MenuModel>();
		do {
			System.out.println("<<<==== PLACE YOUR ORDER ====>>>");
			
			System.out.println("Enter Menu Name ");
			String menu_name=sc.nextLine();
			System.out.println("Enter Quantity");
			int qty=sc.nextInt();
			
			MenuModel menuModel=new MenuModel();
			menuModel.setMenu_name(menu_name);
			menuModel.setQty(qty);
			
			menuList.add(menuModel);
			System.out.println("DO YOU WANT TO ADD MORE ITEMS (yes/no)");
			sc.nextLine();
			
			if(sc.nextLine().equals("no")) {
				System.out.println("Thank You Sir...😊");
				
				break;
			}
		}while(true);
		
		
		PlaceOrderModel placeOrder=new PlaceOrderModel();
		placeOrder.setCust_Name(cust_Name);
		placeOrder.setContact(contact);
		placeOrder.setList(menuList);
		
		boolean b=custService.isPlacedNewOrder(placeOrder);
		if(b) {
			System.out.println("Your Order Comes Within 10 Minutes ");
		}else {
			System.out.println("Some Thing Issuee....");
		}
				
	}
	
// call ViewAll Category
	public static void callViewAllCategery() {
		List<CategeryModel> list=custService.getAllCategery();
		sc.nextLine();
		System.out.println("======================================");
		for(CategeryModel model:list) {
			System.out.println(model.getCategery_id()+"\t"+model.getCategery_name());
		}
		System.out.println("======================================");
	}
	
// call get menu by category
	public static void getMenuByCategery(String cat_Name) {
		List<MenuModel> list=custService.getMenuByCategery(cat_Name);
		System.out.println("======================================");
		for(MenuModel model: list) {
			System.out.println(model.getMenu_id()+"\t"+model.getMenu_name()+"\t"+model.getPrice()+"\t"+model.getDescription());
		}
		System.out.println("======================================");
	}
	
}
