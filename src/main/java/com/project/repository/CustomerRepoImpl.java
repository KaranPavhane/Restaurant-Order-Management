package com.project.repository;

import java.util.ArrayList;
import java.util.List;

import com.project.commons.DBConfig;
import com.project.model.CategeryModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;
import com.project.model.TableModel;

public class CustomerRepoImpl extends DBConfig implements ICustomerRepo {

// ====================================================================================================================================================

	public String getStaffNameByTableId(int table_id) {

		try {
			int staff_id = -1;
			ps = con.prepareStatement("Select staff_id from resto_table where table_Number=?");
			ps.setInt(1, table_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				staff_id = rs.getInt("staff_id");
			}
			return getStaffNameByStaffId(staff_id);
		} catch (Exception ex) {
			System.out.println("Exception in getStaffNameByTableId method : " + ex);
		}
		return null;
	}

	public String getStaffNameByStaffId(int staff_id) {

		try {
			ps = con.prepareStatement("select staff_name from staff_master where staff_id=?");
			ps.setInt(1, staff_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString("staff_name");
			}

		} catch (Exception ex) {
			System.out.println("Error in getStaffName : " + ex);
		}
		return null;
	}

// ====================================================================================================================================================

	public CustomerModel getCustomerDetailsByEmail(String email) {

		try {
			ps = con.prepareStatement("select * from customer_master where email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			CustomerModel custModel = null;
			while (rs.next()) {
				custModel = new CustomerModel();
				custModel.setCust_id(rs.getInt("cust_id"));
				custModel.setCust_name(rs.getString("cust_name"));
				custModel.setEmail(rs.getString("email"));
				custModel.setContact(rs.getString("contact"));
			}
			return custModel;

		} catch (Exception ex) {
			System.out.println("Exception in getCustomerDetails ::" + ex);
			return null;
		}

	}
// =================================================================================================================================

	@Override
	public List<CategeryModel> getAllCategery() {
		List<CategeryModel> list = null;
		try {
			ps = con.prepareStatement("SELECT * FROM CATEGERY_MASTER");
			rs = ps.executeQuery();
			list = new ArrayList<CategeryModel>();
			while (rs.next()) {
				CategeryModel model = new CategeryModel();
				model.setCategery_id(rs.getInt("categery_id"));
				model.setCategery_name(rs.getString("categery_name"));
				list.add(model);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("SQL error : " + ex);
		}

		return list;
	}

	public int getCategeryIdByName(String cat_Name) {
		try {
			ps = con.prepareStatement("select Categery_id from categery_master where Categery_name=?");
			ps.setString(1, cat_Name);
			rs = ps.executeQuery();
			if (rs.next()) {
				int categery_Id = rs.getInt("Categery_id");
				if (categery_Id > 0) {
					return categery_Id;
				} else {
					return -1;
				}
			}

		} catch (Exception ex) {
			System.out.println("Error is : " + ex);
		}
		return 0;

	}
// =================================================================================================================================

	@Override
	public List<MenuModel> getMenuByCategery(String cat_Name) {
		List<MenuModel> list = null;
		int categery_Id = this.getCategeryIdByName(cat_Name);
		//System.out.println("Categery is :: " + categery_Id);
		try {
			ps = con.prepareStatement("Select * from menu_master where categery_id=?");
			ps.setInt(1, categery_Id);
			rs = ps.executeQuery();
			list = new ArrayList<MenuModel>();
			while (rs.next()) {
				MenuModel model = new MenuModel();
				model.setMenu_id(rs.getInt("menu_id"));
				model.setMenu_name(rs.getString("menu_Name"));
				model.setPrice(rs.getInt("price"));
				model.setDescription(rs.getString("Description"));

				list.add(model);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("Error is :" + ex);
		}
		return null;
	}

// =================================================================================================================================

	@Override
	public int isPlacedNewOrder(PlaceOrderModel placeOrder) {

		try {
			CustomerModel cust = placeOrder.getCustModel();
			List<MenuModel> menuModel = placeOrder.getList();
		//	String custName = cust.getCust_name();
			String contact = cust.getContact();
			//String email = cust.getEmail();
			int grandTotal = 0;
			int table_number = placeOrder.getTable_number();
			int v = insertCustomerDetails(cust);
			System.out.println("value return by customer is :: " + v);
//			ps = con.prepareStatement("insert into customer_master values ('0',?,?,?)");
//			ps.setString(1, custName);
//			ps.setString(2, contact);
//			ps.setString(3, email);
//			int value = ps.executeUpdate();

			if (v > 0) {
				int custId = getCustomerIdByName(contact);
				ps = con.prepareStatement("insert into order_master values ('0',?)");
				ps.setInt(1, custId);
				int value = ps.executeUpdate();
				if (value > 0) {

					int orderId = this.getOrderIdByCustomerId(custId);
					for (MenuModel model : menuModel) {
						String menu_name = model.getMenu_name();
						int menu_id = getMenuIdByMenuName(menu_name);
						int menu_price = getMenuPriceByMenuId(menu_id);
						int qty = model.getQty();
						grandTotal = grandTotal + (menu_price * qty);

						ps = con.prepareStatement("insert into order_menu_join values (?,?,?,?)");
						ps.setInt(1, orderId);
						ps.setInt(2, menu_id);
						ps.setInt(3, qty);
						ps.setString(4, "Pending");
						value = ps.executeUpdate();

					}
					if (value > 0) {
						ps = con.prepareStatement("update resto_table set status='Occupied' where table_number=?");
						ps.setInt(1, table_number);
						value = ps.executeUpdate();
						if (value > 0) {
							int table_id = getTableIdByTableNumber(table_number);
							grandTotal = grandTotal - (grandTotal * 10 / 100);

							ps = con.prepareStatement(
									"insert into bill_master (bill_id,order_id,cust_id,grand_total,table_id) values('0',?,?,?,?)");
							ps.setInt(1, orderId);
							ps.setInt(2, custId);
							ps.setInt(3, grandTotal);
							ps.setInt(4, table_id);
							value = ps.executeUpdate();
							if (value > 0) {

								ps = con.prepareStatement("select max(bill_id) from bill_master");
								rs = ps.executeQuery();
								while (rs.next()) {
									return rs.getInt(1);
								}
							}
						}
					}
				}
			} else {
				System.out.println("Customer not added ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error in PlaceOrder method : " + ex);
		}
		return 0;
	}

// =================================================================================================================================

	public int insertCustomerDetails(CustomerModel custModel) {

		try {
			String email = checkCustomerPresent(custModel);
			if (!custModel.getEmail().equals(email)) {
				ps = con.prepareStatement("insert into customer_master values ('0',?,?,?)");
				ps.setString(1, custModel.getCust_name());
				ps.setString(2, custModel.getContact());
				ps.setString(3, custModel.getEmail());
				int value = ps.executeUpdate();
				if (value > 0) {
					return value;
				}
			}
		} catch (Exception ex) {
			System.out.println("Error is in insertCustomerDetails :: " + ex);
		}

		return 1;
	}

	public String checkCustomerPresent(CustomerModel custModel) {
		try {
			String email = custModel.getEmail();
			ps = con.prepareStatement("select * from customer_master where email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString("email");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in checkCustomerPresent :: " + e);
		}

		return null;

	}

	public int getCustomerIdByName(String contact) {

		try {
			ps = con.prepareStatement("select cust_id from customer_master where contact=?");
			ps.setString(1, contact);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error in getCustomerIdByName : " + ex);
		}
		return 0;
	}

	public int getOrderIdByCustomerId(int custId) {

		try {
			ps = con.prepareStatement("select order_id from order_master where cust_id=?");
			ps.setInt(1, custId);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception ex) {
			System.out.println("Error in getOrderIdByCustomerId : " + ex);
		}
		return 0;
	}

	public int getMenuIdByMenuName(String menu_name) {

		try {
			ps = con.prepareStatement("select menu_id from menu_master where menu_name=?");
			ps.setString(1, menu_name);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error in getMenuIdByMenuName :: " + ex);
		}
		return 0;
	}

	public int getTableIdByTableNumber(int table_number) {

		try {
			ps = con.prepareStatement("select table_id from resto_table where table_number=?");
			ps.setInt(1, table_number);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception ex) {
			System.out.println("Error is in getTableIdByTableNumber : " + ex);
		}
		return 0;
	}

	public int getMenuPriceByMenuId(int menuId) {

		try {
			ps = con.prepareStatement("select price from menu_master where menu_id =?");
			ps.setInt(1, menuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("price");
			}

		} catch (Exception ex) {
			System.out.println("Error in getMenuPriceByMenuId :" + ex);
		}
		return 1;

	}
// =================================================================================================================================

	@Override
	public List<TableModel> availableTable() {

		List<TableModel> tableList = null;
		try {
			ps = con.prepareStatement("Select * from resto_table where status=?");
			ps.setString(1, "available");

			rs = ps.executeQuery();
			tableList = new ArrayList<TableModel>();
			while (rs.next()) {
				TableModel model = new TableModel();
				model.setTable_id(rs.getInt("table_id"));
				model.setTable_number(rs.getInt("table_number"));
				model.setCapacity(rs.getInt("capacity"));
				model.setTable_status(rs.getString("status"));
				model.setStaff_id(rs.getInt("staff_id"));

				tableList.add(model);
			}
			return tableList;
		} catch (Exception ex) {
			System.out.println("Error in availableTable method :: " + ex);
			return tableList;
		}
	}
// =================================================================================================================================

}
