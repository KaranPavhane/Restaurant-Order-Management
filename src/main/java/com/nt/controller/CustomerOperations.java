package com.nt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.model.CategeryModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;
import com.project.model.TableModel;
import com.project.service.CustomerServiceImpl;
import com.project.service.ICustomerService;

public class CustomerOperations {

	static ICustomerService custService=new CustomerServiceImpl();
	static Scanner sc=new Scanner(System.in);
	
	public static void callCustomerOperations() {
		availableTable();  										// for showing the available tables
		System.out.println("russuuuuuuuuuuuuuuu");
		System.out.println("Enter Table No ");
		int table_number=sc.nextInt();
		String staff_name=custService.getStaffNameByTableId(table_number);
		System.out.println("Hello Sir My Self :: "+staff_name+" Plzz Fill-up the Following Information");
		sc.nextLine();
		
<<<<<<< HEAD:src/main/java/com/nt/controller/CustomerOperations.java
		System.out.println("ENTER YOUR NAME");
		String cust_Name=sc.nextLine();
		System.out.println("Enter Your Contact");
		String contact=sc.nextLine();
=======
		String cust_Name="";
		String contact="";
		System.out.println("Enter Your Email ");
		String cust_email=sc.nextLine();
		
		CustomerModel cust=custService.getCustomerDetailsByEmail(cust_email);
		if(cust!=null) {
			System.out.println("Customer present :: " +cust.getCust_name());
			cust_Name=cust.getCust_name();
			contact=cust.getContact();
		}else {
			System.out.println("<<============== Sir Please Enter Your Details ========>>");
			System.out.println("Enter Your Name ");
			cust_Name=sc.nextLine();
			System.out.println("Enter Your Contact");
			contact=sc.nextLine();
		}
		
		cust=new CustomerModel();
		cust.setCust_name(cust_Name);
		cust.setEmail(cust_email);
		cust.setContact(contact);
>>>>>>> 7ea9f542d5f28e33853b0f662ff650643cc268b7:src/main/java/com/project/staticMethods/CustomerOperations.java
		
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
		placeOrder.setList(menuList);
		placeOrder.setCustModel(cust);
		
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
	
	
	public static void availableTable() {
		
			List<TableModel> tableModel=custService.availableTable();
		
			if(tableModel!=null) {
				System.out.println("============================================");
				System.out.println("<<========= Table Available for Yours=========>>");
				System.out.println("Table No.    Capacity     Status");
				for(TableModel model : tableModel) {
					System.out.println(model.getTable_number()+"\t\t"+model.getCapacity()+"\t"+model.getTable_status());
				}
			}else {
				System.out.println("table not present");
			}
				
	}
	
}