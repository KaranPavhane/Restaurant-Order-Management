package com.project.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.project.model.BillModel;
import com.project.model.CategeryModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;
import com.project.model.TableModel;
import com.project.service.CustomerBillServiceImpl;
import com.project.service.CustomerServiceImpl;
import com.project.service.ICustomerBillService;
import com.project.service.ICustomerService;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
public class CustomerOperations {

	static ICustomerService custService = new CustomerServiceImpl();
	static ICustomerBillService billService = new CustomerBillServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public static void custFunction() {
		System.out.println("======================================");
		do {
			System.out.println("Enter 1 for Place Order");
			System.out.println("Enter 2 for Bill ");
			System.out.println("Enter 3 for exit ");
			System.out.println("Select Your Choice ");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				callCustomerOperations();
				break;

			case 2:
				callCustomerBillOperation();
				break;

			case 3:
				System.out.println("Thank You Sir");
				
			}
		} while (true);
	}

//=================================================================================================================================================
	public static void callCustomerOperations() {
		availableTable(); // for showing the available tables
		System.out.println("Enter Table No ");
		int table_number = sc.nextInt();
		String staff_name = custService.getStaffNameByTableId(table_number);
		System.out.println("Hello Sir My Self :: " + staff_name + " Plzz Fill-up the Following Information");
		sc.nextLine();

		String cust_Name = "";
		String contact = "";
		System.out.println("Enter Your Email ");
		String cust_email = sc.nextLine();

		CustomerModel cust = custService.getCustomerDetailsByEmail(cust_email);
		if (cust != null) {

			System.out.println("Customer present :: " + cust.getCust_name());
			cust_Name = cust.getCust_name();
			contact = cust.getContact();
		} else {
			System.out.println("<<============== Sir Please Enter Your Details ========>>");
			System.out.println("Enter Your Name ");
			cust_Name = sc.nextLine();
			System.out.println("Enter Your Contact");
			contact = sc.nextLine();
		}

		cust = new CustomerModel();
		cust.setCust_name(cust_Name);
		cust.setEmail(cust_email);
		cust.setContact(contact);

		System.out.println("<<<=== SELECT YOUR CATEGERIES ===>>>");
		callViewAllCategery();
		String cat_Name = sc.nextLine();
		getMenuByCategery(cat_Name);

		List<MenuModel> menuList = new ArrayList<MenuModel>();
		do {
			System.out.println("<<<==== PLACE YOUR ORDER ====>>>");

			System.out.println("Enter Menu Name ");
			String menu_name = sc.nextLine();
			System.out.println("Enter Quantity");
			int qty = sc.nextInt();

			MenuModel menuModel = new MenuModel();
			menuModel.setMenu_name(menu_name);
			menuModel.setQty(qty);

			menuList.add(menuModel);
			System.out.println("DO YOU WANT TO ADD MORE ITEMS (yes/no)");
			sc.nextLine();

			if (sc.nextLine().equals("no")) {
				System.out.println("Thank You Sir...😊");
				break;
			}
		} while (true);

		PlaceOrderModel placeOrder = new PlaceOrderModel();
		placeOrder.setList(menuList);
		placeOrder.setCustModel(cust);
		placeOrder.setTable_number(table_number);

		int bill = custService.isPlacedNewOrder(placeOrder);
		if (bill > 0) {
			System.out.println("Your Order Comes Within 10 Minutes \n");
			System.out.println("You Must Need Remember This Order Id at the time of Billing :: " + bill);
		} else {
			System.out.println("Some Thing Issuee....");
		}
	}

// call ViewAll Category
	public static void callViewAllCategery() {
		List<CategeryModel> list = custService.getAllCategery();
		System.out.println("======================================");
		for (CategeryModel model : list) {
			System.out.println(model.getCategery_id() + "\t" + model.getCategery_name());
		}
		System.out.println("======================================");
	}

// call get menu by category
	public static void getMenuByCategery(String cat_Name) {
		List<MenuModel> list = custService.getMenuByCategery(cat_Name);
		System.out.println("======================================");
		for (MenuModel model : list) {
			System.out.println(model.getMenu_id() + "\t" + model.getMenu_name() + "\t" + model.getPrice() + "\t"
					+ model.getDescription());
		}
		System.out.println("======================================");
	}

	public static void availableTable() {

		List<TableModel> tableModel = custService.availableTable();

		if (tableModel != null) {
			System.out.println("============================================");
			System.out.println("<<========= Table Available for Yours=========>>");
			System.out.println("Table No.    Capacity     Status");
			for (TableModel model : tableModel) {
				System.out.println(
						model.getTable_number() + "\t\t" + model.getCapacity() + "\t" + model.getTable_status());
			}
		} else {
			System.out.println("table not present");
		}
	}

// =========================================================================================================

	public static void callCustomerBillOperation() {

		StringBuffer str = new StringBuffer();
		System.out.println("Enter Your order Id  ");
		int bill_id = sc.nextInt();
		// str.append("---------------------------------------------------\n");
		str.append("\n<<<============== 🙏 WELL COME TO MAULI RESTAURANT 🙏 ============>>>\n\n");

		BillModel billModel = billService.getBillDetails(bill_id);

		int cust_id = billModel.getCust_id();
		int order_id = billModel.getOrder_id();
		int cgst = billModel.getCGST();
		int sgst = billModel.getSGST();
		int discount = billModel.getDiscount();
		int grandTotal = billModel.getGrand_total();
	//	int table_id = billModel.getTable_id();
		String bill_date = billModel.getBill_date();
		str.append("Bill ID :: " + bill_id + "                               Date: " + bill_date + "\n");

//		System.out.println(bill_id+"\t"+cust_id+"\t"+order_id+"\t"+cgst+"\t"+sgst+"\t"+discount+"\t"+grandTotal+"\t"+table_id+"\t"+bill_date);

		CustomerModel custModel = getCustomerDetailsById(cust_id);
//		System.out.println(custModel.getCust_id()+"\t"+custModel.getCust_name()+"\t"+custModel.getContact()+"\t"+custModel.getEmail());
		str.append("Customer Name : " + custModel.getCust_name() + "\n");
		str.append("Customer Contact : " + custModel.getContact() + "\n");
		str.append("Customer Email : " + custModel.getEmail() + "\n");
		str.append("\n\n====================Your Order ===========================\n\n");
		str.append("Sr.No.\tMenu Name          Qty\t\tprice\n");

		List<MenuModel> menuList = billService.getMenusByOrderId(order_id);
		int i = 1;
		int price = 0;
		for (MenuModel menu : menuList) {
//			System.out.println(menu.getMenu_id()+"\t"+menu.getMenu_name()+"\t"+menu.getPrice()+"\t"+menu.getQty()+"\t"+menu.getDescription());
			str.append(i + "\t" + menu.getMenu_name() + "                " + menu.getQty() + "\t\t " + menu.getPrice()
					+ "\n");
			i++;
			price = price + (menu.getPrice() * menu.getQty());
		}
		str.append("-------------------------------------------------------------\n");
		str.append("                                    Total Price :: " + price);

		str.append("\n                                       SGST tax :: " + sgst + "%");
		str.append("\n                                       CGST Tax :: " + cgst + "%");
		str.append("\n                                       Discount :: " + discount + "%");
		str.append("\n                                   ----------------------------");
		str.append("\n                                    Grand Total :: " + grandTotal);

		str.append("\n\n<<<=========== Thank You Sir Visit Again 🙏😊 =============>>>");

		System.out.println(str);
<<<<<<< Updated upstream

		String to = custModel.getEmail();
		String from = "pavhane21@gmail.com";
		String subject = "<<<======= MAULI RESTAURANT ========>>>";
		String text = new String(str);
		boolean b=SendMailToCustomer(to,from,subject,text);
		if(b) {
			System.out.println("You Can also Check Your Email For Bill Details....!!😊🙏");
		}else {
			System.out.println("not Valid Email");
		}
=======
		
		
		String to=custModel.getEmail();
		String from="pavhane21@gmail.com"; 
		String subject="<<<======== Your Bill ============>>>";
		String text=new String(str);
		boolean isSendMail=sendEmailToCustomer(to,from, subject, text);
		if(isSendMail) {
			System.out.println("Email Send Successfully......");
		}else {
			System.out.println("Email not send....");
		}
		
		
		
>>>>>>> Stashed changes
	}

	public static CustomerModel getCustomerDetailsById(int cust_id) {

		CustomerModel custModel = billService.getCustDetail(cust_id);
		return custModel;
	}
<<<<<<< Updated upstream

	public static boolean SendMailToCustomer(String to, String from, String subject, String text) {

		// SMTP server configuration
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");

		// Gmail credentials
		String username = "pavhane21@gmail.com"; // Your Gmail address
		String password = "iesz jbni cayd eccs"; // Replace with App-Specific Password

		// Create a session with authentication
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// session.setDebug(true); // Enable SMTP debugging for detailed logs

		try {
			// Create a MimeMessage object
			MimeMessage message = new MimeMessage(session);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setText(text);

			// Send the email
			Transport.send(message);
			//System.out.println("Email sent successfully.");
			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
		

	}

=======
	
	
	public static boolean sendEmailToCustomer(String to, String from, String subject, String text) {
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "pavhane21@gmail.com"; // Your Gmail address
        String password = "iesz jbni cayd eccs"; // Replace with App-Specific Password

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		
//        session.setDebug(true); // Enable SMTP debugging for detailed logs

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully.");
            return true;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
		
	}
	
	
	
	
>>>>>>> Stashed changes
}
