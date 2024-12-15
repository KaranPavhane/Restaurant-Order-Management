package com.project.repository;

import java.util.ArrayList;
import java.util.List;

import com.project.commons.DBConfig;
import com.project.model.CategeryModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;

public class CustomerRepoImpl extends DBConfig implements ICustomerRepo{

	
	@Override
	public List<CategeryModel> getAllCategery() {
		List<CategeryModel> list=null;
		try {
			ps=con.prepareStatement("SELECT * FROM CATEGERY_MASTER");
			rs=ps.executeQuery();
			list=new ArrayList<CategeryModel>();
			while(rs.next()) {
				CategeryModel model=new CategeryModel();
				model.setCategery_id(rs.getInt("categery_id"));
				model.setCategery_name(rs.getString("categery_name"));
				
				list.add(model);
				
			}
			return list;
		}catch(Exception ex) {
			System.out.println("SQL error : "+ex);
		}
		
		return list;
	}
	
	public int getCategeryIdByName(String cat_Name) {
		try {
				ps=con.prepareStatement("select Categery_id from categery_master where Categery_name=?");
				ps.setString(1, cat_Name);
				rs=ps.executeQuery();
				if(rs.next()) {
					int categery_Id=rs.getInt("Categery_id");
					if(categery_Id>0) {
						return categery_Id;
					}else {
						return -1;
					}
				}
			
		}catch(Exception ex) {
			System.out.println("Error is : "+ex);
		}
		return 0;
		
	}

	@Override
	public List<MenuModel> getMenuByCategery(String cat_Name) {
		List<MenuModel> list=null;
		int categery_Id =this.getCategeryIdByName(cat_Name);
		System.out.println("Categery is :: "+categery_Id);
	
		try {
			ps=con.prepareStatement("Select * from menu_master where categery_id=?");
			ps.setInt(1, categery_Id);
			rs=ps.executeQuery();
			list=new ArrayList<MenuModel>();
			while(rs.next()) {
				MenuModel model=new MenuModel();
				model.setMenu_id(rs.getInt("menu_id"));
				model.setMenu_name(rs.getString("menu_Name"));
				model.setPrice(rs.getInt("price"));
				model.setDescription(rs.getString("Description"));
				
				list.add(model);
			}
			return list;
		}catch(Exception ex) {
			System.out.println("Error is :"+ex);
		}
		return null;
	}

	@Override
	public boolean isPlacedNewOrder(PlaceOrderModel placeOrder) {

		try {
			String cust_Name=placeOrder.getCust_Name();
			int contact =placeOrder.getContact();
			List<MenuModel> menuModel=placeOrder.getList();
			
			ps=con.prepareStatement("insert into customer_master values ('0',?,?)");
			ps.setString(1, cust_Name);
			ps.setInt(2, contact);
			int value=ps.executeUpdate();
			if(value>0) {
				int custId=getCustomerIdByName(contact);
				System.out.println("CustId :: "+custId);
				ps=con.prepareStatement("insert into order_master values ('0',?)");
				ps.setInt(1, custId);
				value=ps.executeUpdate();
				if(value>0) {
					int orderId=this.getOrderIdByCustomerId(custId);
					System.out.println("Order id : "+orderId);
					for(MenuModel model:menuModel) {
						String menu_name=model.getMenu_name();
						int menu_id=getMenuIdByMenuName(menu_name);
						int qty=model.getQty();
						System.out.println("Menu Name : "+menu_name);
						System.out.println("Menu Id : "+menu_id);
						System.out.println("Menu QTY : "+qty);
						
						ps=con.prepareStatement("insert into order_menu_join values (?,?,?)");
						ps.setInt(1, orderId);
						ps.setInt(2, menu_id);
						ps.setInt(3, qty);
						value=ps.executeUpdate();
					}
					if(value>0) {
						return true;
					}
				}
				
			}else {
				System.out.println("Customer not added ");
			}
				
		}catch(Exception ex) {
			System.out.println("Error in PlaceOrder method : "+ex);
		}
		return false;
	}
	
	public int getCustomerIdByName(int contact) {
		
		try {
			ps=con.prepareStatement("select cust_id from customer_master where contact=?");
			ps.setInt(1, contact);
			rs=ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("Error in getCustomerIdByName : "+ex);
		}
		return 0;
	}
	
	public int getOrderIdByCustomerId(int custId) {
		
		try {
			ps=con.prepareStatement("select order_id from order_master where cust_id=?");
			ps.setInt(1, custId);
			rs=ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch(Exception ex) {
			System.out.println("Error in getOrderIdByCustomerId : "+ex);
		}
		return 0;
	}
	
	public int getMenuIdByMenuName(String menu_name) {
	
		try {
			ps=con.prepareStatement("select menu_id from menu_master where menu_name=?");
			ps.setString(1, menu_name);
			rs=ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("Error in getMenuIdByMenuName :: "+ex);
		}
		
		
		return 0;
	}
}
