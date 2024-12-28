package com.project.repository;

import java.util.ArrayList;
import java.util.List;

import com.project.commons.DBConfig;
import com.project.model.BillModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;
import com.project.model.OrderMenuJoinModel;

public class CustomerBillRepoImpl extends DBConfig implements ICustomerBillRepo {
	
	public BillModel getBillDetails(int bill_id) {
		BillModel billModel=null;
		try {
			ps=con.prepareStatement("select * from Bill_master where bill_id=?");
			ps.setInt(1, bill_id);
			rs=ps.executeQuery();
			billModel=new BillModel();
			int table_id=0;
			while(rs.next()) {
				billModel.setBill_id(rs.getInt(1));
				billModel.setOrder_id(rs.getInt(2));
				billModel.setCust_id(rs.getInt(3));
				billModel.setCGST(rs.getInt(4));
				billModel.setSGST(rs.getInt(5));
				billModel.setDiscount(rs.getInt(6));
				billModel.setGrand_total(rs.getInt(7));
				billModel.setBill_date(rs.getString(8));
				billModel.setTable_id(rs.getInt(9));
				table_id=rs.getInt(9);
			}
			ps=con.prepareStatement("update resto_table set status ='available' where table_id=?");
			ps.setInt(1, table_id);
			int val=ps.executeUpdate();
			if(val>0) {
				return billModel;
			}
			
		}catch(Exception ex) {
			System.out.println("Error is in getBillDetails : "+ex);
		}
		return billModel;
	}

	public CustomerModel getCustDetail(int cust_id) {
		
		CustomerModel custModel=null;
		try {
			ps=con.prepareStatement("select * from customer_master where cust_id=?");
			ps.setInt(1, cust_id);
			rs=ps.executeQuery();
			custModel=new CustomerModel();
			while(rs.next()) {
				custModel.setCust_id(rs.getInt(1));
				custModel.setCust_name(rs.getString(2));
				custModel.setContact(rs.getString(3));
				custModel.setEmail(rs.getString(4));	
			}
		}catch(Exception ex) {
			System.out.println("Error is in getCustDetail : "+ex);
		}
		return custModel;	
	}
	
	public List<MenuModel> getMenusByOrderId(int order_id) {
		List<MenuModel> menuListModel=null;

		try {
			ps=con.prepareStatement("select * from order_menu_join moj inner join menu_master mm on moj.menu_id=mm.menu_id where moj.order_id=? && moj.bill_status='pending'");
			ps.setInt(1, order_id);
			rs=ps.executeQuery();
			menuListModel=new ArrayList<MenuModel>();
			
			while(rs.next()) {
				int ordId=rs.getInt("order_id");
//				System.out.println("Order ID :: "+ordId);
				List<OrderMenuJoinModel> orderMenuJoin=getMenuIdByOrderId(ordId);
				for(OrderMenuJoinModel model:orderMenuJoin) {
					int menuId=model.getMenu_id();
					int qty=model.getQty();
					
					billStatus(menuId,order_id);
					MenuModel menuModel=getMenuDetialsByMenuId(menuId);
					
					MenuModel menu=new MenuModel();
					menu.setMenu_id(menuModel.getMenu_id());
					menu.setMenu_name(menuModel.getMenu_name());
					menu.setDescription(menuModel.getDescription());
					menu.setPrice(menuModel.getPrice());
					menu.setQty(qty);
				//	System.out.println(menuModel.getMenu_id()+"\t"+menuModel.getMenu_name()+"\t"+menuModel.getDescription()+"\t"+menuModel.getPrice()+"\t"+qty);
					menuListModel.add(menu);
					
				}	
			}
			
			return menuListModel;	
		}catch(Exception ex) {
			System.out.println("Error is in getMenusByOrderId :: "+ex);
		}
		return menuListModel;
		
	}
	
	public void billStatus(int menu_id, int order_id) {
		try {
			ps=con.prepareStatement("update order_menu_join set bill_status='completed' where order_id=? && menu_id=?");
			ps.setInt(1, order_id);
			ps.setInt(2, menu_id);
			ps.executeUpdate();	
		}catch(Exception ex) {
			System.out.println("Error is in billStatus :: "+ex);
		}	
	}
	
	
	public List<OrderMenuJoinModel> getMenuIdByOrderId(int ordId) {
		List<OrderMenuJoinModel> orderMenuModel=null;
		try {
			ps=con.prepareStatement("select * from order_menu_join where order_id=? && bill_status='pending'");
			ps.setInt(1, ordId);
			rs=ps.executeQuery();
			orderMenuModel=new ArrayList<OrderMenuJoinModel>();
			while(rs.next()) {
				OrderMenuJoinModel model=new OrderMenuJoinModel();
				model.setOrder_id(rs.getInt(1));
				model.setMenu_id(rs.getInt(2));
				model.setQty(rs.getInt(3));
				orderMenuModel.add(model);
			}
			return orderMenuModel;
			
		}catch(Exception ex) {
			System.out.println("error in OrderMenuJoinModel : "+ex);
		}
		
		return orderMenuModel;
		
	}
	
	public MenuModel getMenuDetialsByMenuId(int menuId) {
		MenuModel menuModel=null;
		
		try {
			ps=con.prepareStatement("Select * from menu_master where menu_id=?");
			ps.setInt(1, menuId);
			rs=ps.executeQuery();
			menuModel=new MenuModel();
			while(rs.next()) {
				menuModel.setMenu_id(rs.getInt(1));
				menuModel.setMenu_name(rs.getString(2));
				menuModel.setPrice(rs.getInt(3));
				menuModel.setDescription(rs.getString(4));
				menuModel.setCategery_id(rs.getInt(5));
				
			}
			return menuModel;
			
			
		}catch(Exception ex) {
			System.out.println("Error in getMenuDetialsByMenuId :: "+ex);
		}
		
		
		return menuModel;
		
	}
	
}











