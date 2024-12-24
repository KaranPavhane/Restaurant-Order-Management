package com.project.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.project.model.AdminModel;
import com.project.model.CategeryModel;
import com.project.model.MenuModel;
import com.project.model.StaffModel;
import com.project.model.TableModel;
import com.project.repository.StaffRepositoryImpl;
import com.project.service.AdminServiceImpl;
import com.project.service.CategeryServiceImpl;
import com.project.service.IAdminService;
import com.project.service.ICategeryService;
import com.project.service.IMenuService;
import com.project.service.IRestaurentInfoService;
import com.project.service.IStaffService;
import com.project.service.ITableService;
import com.project.service.MenuServiceImpl;
import com.project.service.RestaurentInfoServiceImpl;
import com.project.service.StaffServiceImpl;
import com.project.service.TableServiceImpl;

public class AdminPannelOperations {

	static IAdminService adminService = new AdminServiceImpl();
	static ICategeryService categeryService = new CategeryServiceImpl();
	static IMenuService menuService = new MenuServiceImpl();
	static IStaffService staffService = new StaffServiceImpl();
	static ITableService tableService = new TableServiceImpl();
	static IRestaurentInfoService restoInfoService = new RestaurentInfoServiceImpl();

	private static final Logger logger = (Logger) LogManager.getLogger(StaffRepositoryImpl.class);

	static Scanner scn = new Scanner(System.in);

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
			logger.info("Admin Login Successfully :-> You Can Manage Your Restaurant...");
			System.out.println();
			AdminCurd();
		} else {
			System.out.println("Login Faild Try Again..!!");
			logger.error("-->Login Faild Please Check Username and password.!!!");
		}
	}

//========================================================================================================================	

	// main Reastaurent operations
	public static void AdminCurd() {

		System.out.println("-------> Restaurent Order Management System <---------");

		do {
			System.out.println("<<<-- Enter 0 FOR ADD NEW ADMIN -->>> ");
			System.out.println("<<<-- Enter 1 FOR STAFF MANAGE -->>> ");
			System.out.println("<<<-- Enter 2 FOR TABLES MANAGE -->>> ");
			System.out.println("<<<-- Enter 3 FOR CATEGRY MANAGEMENT -->>> ");
			System.out.println("<<<-- Enter 4 FOR MENU MANAGEMENT -->>> ");
			System.out.println("<<<-- Enter 5 SHOW ALL RESTAURENT DETAILS -->>> ");
			System.out.println("<<<-- Enter 6 FOR EXIT THE APPLICATION -->>> ");
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
				showAllCustomers();
				System.out.println();
				break;

			case 6:
				System.out.println("Exiting Admin Pannel... See You Again 🙏🙏");
				logger.debug("Exiting Admin Pannel... Goodbye!");
				return;

			default:
				System.out.println("Invaid Choice...");
				logger.error("Invalid Choice! Please try again.");

			}
		} while (true);
	}

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

//==========================================================================================================================

	// Manage all staff
	private static void staffManage() {
		do {
			System.out.println("1.Add New Staff");
			System.out.println("2.Show All Staff Available in Restaurant");
			System.out.println("3 Search Staff Name Start Wish ...");
			System.out.println("4.Delete Staff By Name");
			System.out.println("5.Update Staff By Name");
			System.out.println("6.Back to Main Menu");
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
				searchStaffNameByWord();
				System.out.println();
				break;

			case 4:
				deleteStaffByName();
				System.out.println();
				break;

			case 5:
				updateStaffByName();
				System.out.println();
				break;

			case 6:
				return;

			default:
				System.out.println("Invaid Choice...");
				logger.error("Invalid Choice! Please try again.");
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
			System.out.println(staffModel.getStaff_id() + " \t | " + staffModel.getStaff_name() + " \t | "
					+ staffModel.getStaff_email() + " \t\t | " + staffModel.getContact() + " \t\t|"
					+ staffModel.getSalary() + " \t | " + staffModel.getDate_joined());
		}
		System.out.println();
	}

	private static void searchStaffNameByWord() {

//		showAllStaff();
		scn.nextLine();
		System.out.println("Staff Word Find With :: ");
		String word = scn.nextLine();
		List<StaffModel> staffList = staffService.getStaffNameByGivenWord(word);

		if (staffList.isEmpty()) {
			System.out.println("Staff Are Not Found...");
		} else {
			System.out.println("Staff Founds :: ");
			System.out.println("---------------------------------------------------------------");
			for (StaffModel staffModel : staffList) {
				System.out.println(staffModel.getStaff_id() + " \t | " + staffModel.getStaff_name() + " \t | "
						+ staffModel.getStaff_email() + " \t\t | " + staffModel.getContact() + " \t\t|"
						+ staffModel.getSalary() + " \t | " + staffModel.getDate_joined());
			}
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

//========================================================================================================================	

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

			default:
				System.out.println("Invaid Choice...");
				logger.error("Invalid Choice! Please try again.");

			}

		} while (true);
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

//		showAllStaff();
		scn.nextLine();

//		System.out.println("Enter Staff Id :: ");
//		int staff_id = scn.nextInt();
		List<TableModel> tableList = tableService.showAllTablesByStaffId();
		System.out.println("Restaurent Table Information :: ");
		System.out.println("---------------------------------");
		for (TableModel tableModel : tableList) {
			System.out.println(tableModel.getTable_id() + " \t | " + tableModel.getTable_number() + " \t | "
					+ tableModel.getCapacity() + " \t | " + tableModel.getTable_status());
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

		if (flag) {
			System.out.println("Table Updated Successfully...");
		} else {
			System.out.println("Table Not Updated.");
		}

	}

//========================================================================================================================		

	private static void manageFoodCategery() {

		do {

			System.out.println("1.Add Category");
			System.out.println("2.Show Categories");
			System.out.println("3.Search Category By Word ...");
			System.out.println("4.Delete Category");
			System.out.println("5.Update Category");
			System.out.println("6.Back to Main Menu");
			System.out.println("-------------------------");
			System.out.println();

			System.out.println("Enter Your Choice :: ");
			int choice = scn.nextInt();

			switch (choice) {

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
				serachCategeryByWord();
				System.out.println();
				break;

			case 4:
				// delete categery
				deleteCategery();
				System.out.println();
				break;

			case 5:
				// update categery
				updateCategery();
				System.out.println();
				break;

			case 6:
				return;

			default:
				System.out.println("Invaid Choice...");
				logger.error("Invalid Choice! Please try again.");

			}

		} while (true);

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
			System.out.println(categeryModel.getCategery_id() + " \t | " + categeryModel.getCategery_name());
		}
	}

	private static void serachCategeryByWord() {

//		showAllCategeries();
		scn.nextLine();
		System.out.println("Categery Word to Find :: ");
		String word = scn.nextLine();
		List<CategeryModel> categeryList = categeryService.getCategeryByGivenWord(word);

		if (categeryList.isEmpty()) {
			System.out.println("Categeries Are Not Found...");
		} else {
			System.out.println("Categeries Founds :: ");
			System.out.println("-------------------------------");
			for (CategeryModel categeryModel : categeryList) {

				System.out.println(categeryModel.getCategery_id() + " \t | " + categeryModel.getCategery_name());
			}
		}
		System.out.println();
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

//========================================================================================================================	

	public static void manageFootItems() {

		int choice;
		System.out.println();

		do {

			System.out.println("1.Add Menus In Given Categery");
			System.out.println("2.Show All Menus In Given Categery");
			System.out.println("3.Search Menu By Word ...");
			System.out.println("4.Delete Menu In Given Categery");
			System.out.println("5. Update Menu price In Given Categery");
			System.out.println("6.Back to Main Menu");
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
				searchMenuByWrod();
				System.out.println();
				break;

			case 4:
				// delete menu by given categery_id and menu_name
				deleteMenuByGivenMenuName();
				System.out.println();
				break;

			case 5:
				// update menu price by given categery_id given categery_name
				updateMenuPriceByGivenMenuName();
				System.out.println();
				break;

			case 6:
				return;

			default:
				System.out.println("Invaid Choice...");
				logger.error("Invalid Choice! Please try again.");

			}

		} while (true);

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

//		showAllCategeries();

//		System.out.println("Choice Categery Id to Display All Menus :: ");
//		int categeryId = scn.nextInt();

		List<MenuModel> menus = menuService.showAllmenuList();
		System.out.println("Menus are in Restaurent ");
		System.out.println("---------------------------------------------------");
		for (MenuModel menuModel : menus) {
			System.out.println(menuModel.getMenu_id() + " \t |" + menuModel.getMenu_name() + "\t \t        |  "
					+ menuModel.getPrice() + "   \t |" + menuModel.getDescription());
		}
		System.out.println();

	}

	private static void searchMenuByWrod() {

//		showMenusGivenCategery();
		scn.nextLine();
		System.out.println("Menu Word to Find :: ");
		String word = scn.nextLine();
		List<MenuModel> menuList = menuService.getMenuNamesByGivenWord(word);

		if (menuList.isEmpty()) {
			System.out.println("Menus Are Not Found...");
		} else {
			System.out.println("Menus Founds :: ");
			System.out.println("-------------------------------");
			for (MenuModel menuModel : menuList) {
				System.out.println(menuModel.getMenu_id() + " \t |" + menuModel.getMenu_name() + "\t | "
						+ menuModel.getPrice() + " \t | " + menuModel.getDescription());
			}
			System.out.println();
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

	
	
//========================================================================================================================	

	
	
	private static void showAllCustomers() {
		
		
		List<Map<String, Object>> displayAllInfo = restoInfoService.displayAllInfo();
		
		
		for(Map<String, Object> restorentData : displayAllInfo) {
			
			System.out.println("Custmoer Id ::\t\t "+ restorentData.get("custId"));
			System.out.println("Custmoer Name ::\t "+ restorentData.get("custName"));
			System.out.println("Custmoer Contact ::\t "+ restorentData.get("contact"));
			System.out.println("Custmoer Email ::\t "+ restorentData.get("email"));
			System.out.println("Categery Name ::\t "+ restorentData.get("categoryName"));
			System.out.println("Menu Name ::\t\t "+ restorentData.get("menuName"));
			System.out.println("Menu Price ::\t\t "+ restorentData.get("price"));
			System.out.println("Table Number ::\t\t "+ restorentData.get("tableNumber"));
			System.out.println("CGST ::\t\t\t "+ restorentData.get("cgst"));
			System.out.println("SGST ::\t\t\t "+ restorentData.get("sgst"));
			System.out.println("Discount ::\t\t "+ restorentData.get("discount"));
			System.out.println("Grand Total ::\t\t "+ restorentData.get("grandTotal"));
			System.out.println("Bill Date ::\t\t "+ restorentData.get("billDate"));
			System.out.println();
			
			System.out.println("===========================================================");
			System.out.println();
			
		}

		System.out.println();
		
		
	}

}

/*

delimiter //

CREATE PROCEDURE getAllRestaurentData()
BEGIN
    SELECT
        c.cust_id, c.cust_name, c.contact, c.email,
        cat.categery_name,
        m.menu_name, m.price,
        t.table_number,
        b.CGST, b.SGST, b.discount, b.grand_total, b.bill_date
    FROM customer_master c
    INNER JOIN order_master o ON c.cust_id = o.cust_id
    INNER JOIN bill_master b ON o.order_id = b.order_id
    INNER JOIN resto_table t ON b.table_id = t.table_id
    INNER JOIN order_menu_join omj ON o.order_id = omj.order_id
    INNER JOIN menu_master m ON omj.menu_id = m.menu_id
    INNER JOIN categery_master cat ON m.categery_id = cat.categery_id;
END //

delimiter ;




CALL getAllRestaurentData();


*/
