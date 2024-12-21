package com.project.controller;

import java.util.List;
import java.util.Scanner;

import com.project.model.AdminModel;
import com.project.model.CategeryModel;
import com.project.model.MenuModel;
import com.project.model.StaffModel;
import com.project.model.TableModel;
import com.project.repository.IStaffRepository;
import com.project.service.AdminServiceImpl;
import com.project.service.CategeryServiceImpl;
import com.project.service.IAdminService;
import com.project.service.ICategeryService;
import com.project.service.IMenuService;
import com.project.service.IStaffService;
import com.project.service.ITableService;
import com.project.service.MenuServiceImpl;
import com.project.service.StaffServiceImpl;
import com.project.service.TableServiceImpl;

public class AdminPannelOperations {

	static IAdminService adminService = new AdminServiceImpl();
	static ICategeryService categeryService = new CategeryServiceImpl();
	static IMenuService menuService = new MenuServiceImpl();
	static IStaffService staffService = new StaffServiceImpl();
	static ITableService tableService = new TableServiceImpl();

	static Scanner scn = new Scanner(System.in);

	private static void addAdmin() {
		scn.nextLine();
		System.out.println("Enter User Name :: ");
		String username = scn.nextLine();
		System.out.println("Enter Password :: ");
		String password = scn.nextLine();
		System.out.println("Enter Date of Birth [YYYY-MM-DD] :: ");
		String date = scn.nextLine();

		AdminModel admin = new AdminModel();
		admin.setAdmin_username(username);
		admin.setAdmin_pass(password);
		admin.setDob(date);

		boolean flag = adminService.isAddNewAdmin(admin);
		if (flag) {
			System.out.println("Admin Added Successfully...");
		} else {
			System.out.println("Admin Not Added.");
		}
	}

	public static void loginAsAdmin() {
		System.out.println();
		System.out.println("---Enter Admin Username and Password---");
		System.out.println("--------------------------------------------");
		System.out.println();
		System.out.println("Enter User Name :: ");
		String username = scn.nextLine();
		System.out.println("Enter Password :: ");
		String password = scn.nextLine();

		if (adminService.loginAdmin(username, password)) {
			System.out.println("Admin Login Successfully :-> You Can Manage Your Restaurant...");
			System.out.println();
			AdminCurd();
		} else {
			System.out.println("Enter Admin Currect Username and Password.");
		}
	}

	
	
	//main Reastaurent operations
	public static void AdminCurd() {

	    do {
	        System.out.println("<<<-- Enter 0 FOR ADD NEW ADMIN -->>> ");
	        System.out.println("<<<-- Enter 1 FOR STAFF MANAGE -->>> ");
	        System.out.println("<<<-- Enter 2 FOR TABLES MANAGE -->>> ");
	        System.out.println("<<<-- Enter 3 FOR CATEGRY MANAGEMENT -->>> ");
	        System.out.println("<<<-- Enter 4 FOR MENU MANAGEMENT -->>> ");
	        System.out.println("<<<-- Enter 5 FOR EXIT THE APPLICATION -->>> ");
	        System.out.println();

	        System.out.println("Enter Your Choice :: ");
	        int choice = scn.nextInt();
	        System.out.println();

	        switch (choice) {

	            case 0:
	                // for adding new admin
	                addAdmin();
	                System.out.println();
	                break;

	            case 1:
	                staffManage(); 
	                System.out.println();
	                break;

	            case 2:
	                manageRestaurentTables(); 
	                System.out.println();
	                break;

	            case 3:
	                manageFoodCategery(); 
	                System.out.println();
	                break;

	                
	            case 4:
	                manageFootItems(); 
	                System.out.println();
	                break;

	                
	            case 5:
	                System.out.println("Exiting Admin Menu. Goodbye!");
	                return;

	                
	            default:
	                System.out.println("Invalid Choice! Please try again.");
	        }
	    } while (true);
	}

	
	// Manage all staff
	private static void staffManage() {
	    do {
	        System.out.println("1.Add New Staff");
	        System.out.println("2.Show All Staff Available in Restaurant");
	        System.out.println("3.Delete Staff By Name");
	        System.out.println("4.Update Staff By Name");
	        System.out.println("5.Back to Main Menu");
	        System.out.println("-------------------------");
	        System.out.println();

	        System.out.println("Enter Your Choice :: ");
	        int choice = scn.nextInt();

	        switch (choice) {

	            case 1:
	                addNewStaff();
	                System.out.println();
	                break;

	            case 2:
	                showAllStaff();
	                System.out.println();
	                break;

	            case 3:
	                deleteStaffByName();
	                System.out.println();
	                break;

	            case 4:
	                updateStaffByName();
	                System.out.println();
	                break;

	            case 5:
	                return; 

	            default:
	                System.out.println("Invalid Choice! Please try again.");
	        }
	    } while (true);
	}

	
	
	// manage restaurent tables
	private static void manageRestaurentTables() {

		do {

			System.out.println("1.Add new Table");
			System.out.println("2.show All Tables in Restaurent");
			System.out.println("3.Delete Table By Table Number");
			System.out.println("4.Update Table By Table Number");
	        System.out.println("5.Back to Main Menu");
			System.out.println("-------------------------");
			System.out.println();

			System.out.println("Enter Your Choice :: ");
			int choice = scn.nextInt();

			switch (choice) {

			case 1:
				// add new table
				addNewTable();
				System.out.println();
				break;

			case 2:
				// show all tables in restaurent
				showAllTablesInRestaurent();
				System.out.println();
				break;

			case 3:
				// delete tables by its table number
				deleteTableByItsTableNumber();
				System.out.println();
				break;

			case 4:
				// update table by its table number
				updateTableByItsTableNumber();
				System.out.println();
				break;
				
			case 5:
				return;

			}

		} while (true);
	}

	private static void manageFoodCategery() {

		do {

			System.out.println("1.Add Category");
			System.out.println("2.Show Categories");
			System.out.println("3.Delete Category");
			System.out.println("4.Update Category");
	        System.out.println("5.Back to Main Menu");
			System.out.println("-------------------------");
			System.out.println();

			System.out.println("Enter Your Choice :: ");
			int choice = scn.nextInt();
			
			switch(choice) {
			
			case 1:
				// add categery
				addCategery();
				System.out.println();
				break;

			case 2:
				// show all categeries
				showAllCategeries();
				System.out.println();
				break;

			case 3:
				// delete categery
				deleteCategery();
				System.out.println();
				break;

			case 4:
				// update categery
				updateCategery();
				System.out.println();
				break;
				
			case 5:
				return;
			
			
			}

		} while (true);

	}

	public static void manageFootItems() {

		int choice;
		System.out.println();

		
		do {

			System.out.println("1.Add Menus In Given Categery");
			System.out.println("2.Show All Menus In Given Categery");
			System.out.println("3.Delete Menu In Given Categery");
			System.out.println("4. Update Menu price In Given Categery");
	        System.out.println("5.Back to Main Menu");
			System.out.println("-------------------------");

			System.out.println();
			System.out.println("Enter Your Choice : ");
			choice = scn.nextInt();

			switch (choice) {

			

			case 1:
				// add menu in given categery
				addMenuesGivenByCategery();
				System.out.println();
				break;

			case 2:
				// show all menus given categery
				showMenusGivenCategery();
				System.out.println();
				break;

			case 3:
				// delete menu by given categery_id and menu_name
				deleteMenuByGivenMenuName();
				System.out.println();
				break;

			case 4:
				// update menu price by given categery_id given categery_name
				updateMenuPriceByGivenMenuName();
				System.out.println();
				break;
				
			case 5:
				return;

			}

		} while (true);

	}
	
	

	// add new staff
	private static void addNewStaff() {
		scn.nextLine();
		System.out.println("Enter Staff Name :: ");
		String name = scn.nextLine();
		System.out.println("Email :: ");
		String email = scn.nextLine();
		System.out.println("Contact Number :: ");
		String contact = scn.nextLine();
		System.out.println("Salary :: ");
		int salary = scn.nextInt();
//		scn.nextLine();
//		System.out.println("Joined Date :: ");
//		String joinedDate = scn.nextLine();

		
		StaffModel staff = new StaffModel();
		staff.setStaff_name(name);
		staff.setStaff_email(email);
		staff.setContact(contact);
		staff.setSalary(salary);
		
		boolean flag = staffService.addNewStaff(staff);
		
		
		if (flag) {
			System.out.println("Staff Details Added Successully...");
		} else {
			System.out.println("Staff Not Added.");
		}
	}

	// show all staff details
	private static void showAllStaff() {
		List<StaffModel> staff = staffService.showAllStaffInRestaurent();
		System.out.println("Available Staff in Restaurent is :: ");
		System.out.println("-------------------------------------------------------------");
		for (StaffModel staffModel : staff) {
			System.out.println(staffModel.getStaff_id() + "  " + staffModel.getStaff_name() + "  "
					+ staffModel.getStaff_email() + "  " + staffModel.getContact() + "  " + staffModel.getSalary()
					+ "  " + staffModel.getDate_joined());
		}
		System.out.println();
	}

	// delete staff by name
	private static void deleteStaffByName() {

		showAllStaff();
		scn.nextLine();
		System.out.println("Enter Staff Name To Deletd :: ");
		String staffName = scn.nextLine();

		boolean flag = staffService.deleteStaffByName(staffName);

		if (flag) {
			System.out.println("Staff Deleted Successfully...");
		} else {
			System.out.println("Staff is Not Deleted.");
		}

	}

	// update staff by name
	private static void updateStaffByName() {

		showAllStaff();

		scn.nextLine();
		System.out.println("Enter New Staff Name :: ");
		String NewStaffName = scn.nextLine();
		System.out.println("Email :: ");
		String email = scn.nextLine();
		System.out.println("Contact Number :: ");
		String contact = scn.nextLine();
		System.out.println("Salary :: ");
		int salary = scn.nextInt();
		scn.nextLine();
		System.out.println("Staff Old Name to Find Staff :: ");
		String oldStaffName = scn.nextLine();

		boolean flag = staffService.updateStaffByName(NewStaffName, email, contact, salary, oldStaffName);

		if (flag) {
			System.out.println("Staff Updated Successfully...");
		} else {
			System.out.println("Staff Not Updated.");
		}
	}

	private static void addNewTable() {

		showAllStaff();
		scn.nextLine();
		System.out.println("Enter Table Number :: ");
		int table_number = scn.nextInt();
		System.out.println("Enter Table Capacity :: ");
		int table_capacity = scn.nextInt();
		scn.nextLine();
		System.out.println("Enter Table Status :: ");
		String table_status = scn.nextLine();
		System.out.println("Enter Staff Id to Handle Table :: ");
		int Staff_id = scn.nextInt();

		TableModel tableModel = new TableModel('0', table_number, table_capacity, table_status, Staff_id);
		boolean flag = tableService.addNewTableInRestorent(tableModel);

		if (flag) {
			System.out.println("Table Added Successfully... In Restaurent ");
		} else {
			System.out.println("Table Not Added.");
		}
	}

	private static void showAllTablesInRestaurent() {

		showAllStaff();
		scn.nextLine();

		System.out.println("Enter Staff Id :: ");
		int staff_id = scn.nextInt();
		List<TableModel> tableList = tableService.showAllTablesByStaffId(staff_id);
		System.out.println("Restaurent Table Information :: ");
		System.out.println("---------------------------------");
		for (TableModel tableModel : tableList) {
			System.out.println(tableModel.getTable_id() + "  " + tableModel.getTable_number() + "  "
					+ tableModel.getCapacity() + "  " + tableModel.getTable_status());
		}
		System.out.println();

	}

	private static void deleteTableByItsTableNumber() {

		showAllTablesInRestaurent();
		scn.nextLine();

		System.out.println("Enter Table Number :: ");
		int table_number = scn.nextInt();

		boolean flag = tableService.deleteTableByTableNumber(table_number);
		if (flag) {
			System.out.println("Table Deleted By Staff Id...");
		} else {
			System.out.println("Table Not Delete.");
		}

	}

	private static void updateTableByItsTableNumber() {
		
		showAllTablesInRestaurent();
		scn.nextLine();
		
		System.out.println("Enter Table Number :: ");
		int table_number = scn.nextInt();
		System.out.println("Enter Table Capacity :: ");
		int table_capacity = scn.nextInt();
		scn.nextLine();
		
		System.out.println("Enter Old Table Number :: ");
		int old_table_number = scn.nextInt();
		
		boolean flag = tableService.updateTableByTableNumber(table_number, table_capacity, old_table_number);
		
		if(flag) {
			System.out.println("Table Updated Successfully...");
		}else {
			System.out.println("Table Not Updated.");
		}
			
			
	}

	// for adding categeries
	private static void addCategery() {
		scn.nextLine();
		System.out.println("Enter Categery Name :: ");
		String categery_name = scn.nextLine();

		CategeryModel categery = new CategeryModel();
		categery.setCategery_name(categery_name);

		boolean flag = categeryService.addNewCategery(categery);
		if (flag) {
			System.out.println("Categery Added Successfully...");
		} else {
			System.out.println("Categery Not Added.");
		}

	}

	// show all Categeries
	private static void showAllCategeries() {
		List<CategeryModel> categeries = categeryService.showAllCategeries();
		System.out.println("Categeries are in Restaurant are :: ");
		System.out.println("-----------------------------------------");
		for (CategeryModel categeryModel : categeries) {
			System.out.println(categeryModel.getCategery_id() + "  " + categeryModel.getCategery_name());
		}
	}

	// for deleting categery
	private static void deleteCategery() {

		showAllCategeries();

		System.out.println("Enter Categery Name to Delete :: ");
		String categery_name = scn.nextLine();

		boolean flag = categeryService.deleteCategeryByName(categery_name);
		if (flag) {
			System.out.println("Categery Deleted Successfully...");
		} else {
			System.out.println("Categery Not Found to Deleted.");
		}

	}

	// for updating categery
	private static void updateCategery() {

		showAllCategeries();

		scn.nextLine();
		System.out.println("Enter New Categery Name :: ");
		String new_categery_name = scn.nextLine();
		System.out.println("Enter Old Categery Name to Update :: ");
		String old_categery_name = scn.nextLine();

		boolean flag = categeryService.updateCategeryName(new_categery_name, old_categery_name);

		if (flag) {
			System.out.println("Categery is Updated Successfully...");
		} else {
			System.out.println("Categery is Not Update.");
		}

	}

	// for adding menus by given categery
	private static void addMenuesGivenByCategery() {

		showAllCategeries();

		System.out.println("Choice Categery id to Add Menu :: ");
		int categeryId = scn.nextInt();
		scn.nextLine();
		System.out.println("Menu Name :: ");
		String menu_name = scn.nextLine();
		System.out.println("Menu Description :: ");
		String description = scn.nextLine();
		System.out.println("Menu Price :: ");
		int menu_price = scn.nextInt();
		MenuModel menuModel = new MenuModel();
		menuModel.setMenu_name(menu_name);
		menuModel.setDescription(description);
		menuModel.setPrice(menu_price);
		menuModel.setCategery_id(categeryId);
		boolean flag = menuService.addNewMenu(menuModel);
		if (flag) {
			System.out.println("Menu Added Successfully in Given Categery...");
		} else {
			System.out.println("Menu Not Add.");
		}

	}

	// showing all menus by given categery wise
	private static void showMenusGivenCategery() {

		showAllCategeries();

		System.out.println("Choice Categery Id to Display All Menus :: ");
		int categeryId = scn.nextInt();
		List<MenuModel> menus = menuService.showAllmenuList(categeryId);
		System.out.println("Menus are in Restaurent ");
		System.out.println("--------------------------");
		for (MenuModel menuModel : menus) {
			System.out.println(menuModel.getMenu_id() + " | " + menuModel.getMenu_name() + " | " + menuModel.getPrice()
					+ " | " + menuModel.getDescription());
		}

	}

	// delete menu by give menu name
	private static void deleteMenuByGivenMenuName() {

		showMenusGivenCategery();

		System.out.println("Choice Categery Id to Delete Menus :: ");
		int categeryId = scn.nextInt();

		scn.nextLine();
		System.out.println("Enter Menu Name to Delete :: ");
		String menu_name = scn.nextLine();

		boolean flag = menuService.deleteMenuMyMenuName(categeryId, menu_name);
		if (flag) {
			System.out.println("Menu Deleted Successfully...");
		} else {
			System.out.println("Menu Not Delete.");
		}

	}

	// update menu by given menu name
	private static void updateMenuPriceByGivenMenuName() {

		showMenusGivenCategery();

		System.out.println("Enter New Price :: ");
		int menu_price = scn.nextInt();
		System.out.println("Enter Categery To Find That Menu :: ");
		int categery_id = scn.nextInt();
		scn.nextLine();
		System.out.println("Enter Menu Name to Find that Price :: ");
		String menu_name = scn.nextLine();

		boolean flag = menuService.updateMenuPriceByMenuName(menu_price, categery_id, menu_name);

		if (flag) {
			System.out.println("Menu Price is Updated Successfully...");
		} else {
			System.out.println("Menu Not Updated.");
		}

	}

}
