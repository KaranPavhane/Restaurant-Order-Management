package com.project.controller;

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
	
	public static void custFunction() {
		System.out.println("======================================");
		do {
			System.out.println("Enter 1 for Place Order");
			System.out.println("Enter 2 for Bill ");
			System.out.println("Enter 3 for exit ");
			System.out.println("Select Your Choice ");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:
				callCustomerOperations();
				break;
				
			case 2:
				callCustomerBillOperation();
				break;
				
			case 3:
				System.exit(0);
				break;
			}
		}while(true);
	}
	
	public static void callCustomerOperations() {
		availableTable();  										// for showing the available tables
		System.out.println("Enter Table No ");
		int table_number=sc.nextInt();
		String staff_name=custService.getStaffNameByTableId(table_number);
		System.out.println("Hello Sir My Self :: "+staff_name+" Plzz Fill-up the Following Information");
		sc.nextLine();
		
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
		placeOrder.setTable_number(table_number);
		
		int bill =custService.isPlacedNewOrder(placeOrder);
		if(bill>0) {
			System.out.println("Your Order Comes Within 10 Minutes ");
			System.out.println("You Must Need Remember This Order Id at the time of Billing :: "+bill);
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
	
	
	
// =========================================================================================================
	
	public static void callCustomerBillOperation() {
		System.out.println("Enter Your order Id  ");
		int order_id=sc.nextInt();
		
		
	}
}
