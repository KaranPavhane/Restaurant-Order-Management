package com.project.staticMethods;

import java.util.List;
import java.util.Scanner;

import com.project.model.AdminModel;
import com.project.model.CategeryModel;
import com.project.model.MenuModel;
import com.project.service.AdminServiceImpl;
import com.project.service.CategeryServiceImpl;
import com.project.service.IAdminService;
import com.project.service.ICategeryService;
import com.project.service.IMenuService;
import com.project.service.MenuServiceImpl;

public class AdminPannelOperations {

	static IAdminService adminService = new AdminServiceImpl();
	static ICategeryService categeryService = new CategeryServiceImpl();
	static IMenuService menuService = new MenuServiceImpl();

	static Scanner scn = new Scanner(System.in);

	public static void addAdmin() {
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
		System.out.println("---Enter Admin Username and Password---");
		System.out.println("--------------------------------------------");
		System.out.println("Enter User Name :: ");
		String username = scn.nextLine();
		System.out.println("Enter Password :: ");
		String password = scn.nextLine();

		if (adminService.loginAdmin(username, password)) {
			System.out.println("Admin Login Successfully :-> You Can Manage Your Restaurant...");
			manageCategories();
		} else {
			System.out.println("Enter Admin Currect Username and Password.");
		}
	}

	public static void manageCategories() {

		int choice;

		// here admin username and password is match then it can perform this call
		// operations

		do {
			System.out.println("---Manage Your Restaurant Categeries and Menus---");
			System.out.println("0.If We What to Add New Admin");
			System.out.println("1.Add Category");
			System.out.println("2.Show Categories");
			System.out.println("3.Delete Category");
			System.out.println("4.Update Category");
			System.out.println("-------------------------");
			System.out.println("5.Add Menus In Given Categery");
			System.out.println("6.Show All Menus In Given Categery");
			System.out.println("7.Delete Menu In Given Categery");
			System.out.println("8.Update Menu price In Given Categery");

			System.out.println();
			System.out.println("Enter Your Choice : ");
			choice = scn.nextInt();

			switch (choice) {

			case 0: {
				// for adding new admin
				addAdmin();
				System.out.println();
				break;

			}

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
				// add menu in given categery
				addMenuesGivenByCategery();
				System.out.println();
				break;

			case 6:
				// show all menus given categery
				showMenusGivenCategery();
				System.out.println();
				break;
				
			case 7:
				//delete menu by given categery_id and menu_name
				deleteMenuByGivenMenuName();
				System.out.println();
				break;
				
			case 8:
				//update menu price by given categery_id given categery_name
				updateMenuPriceByGivenMenuName();
				System.out.println();
				break;

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
	
	private static void deleteMenuByGivenMenuName() {
				
		showMenusGivenCategery();
		
		System.out.println("Choice Categery Id to Delete Menus :: ");
		int categeryId = scn.nextInt();
		
		scn.nextLine();
		System.out.println("Enter Menu Name to Delete :: ");
		String menu_name = scn.nextLine();
		
		boolean flag = menuService.deleteMenuMyMenuName(categeryId, menu_name);
		if(flag) {
			System.out.println("Menu Deleted Successfully...");
		}else {
			System.out.println("Menu Not Delete.");
		}
		
	}
	
	private static void updateMenuPriceByGivenMenuName() {
		
	}

}
