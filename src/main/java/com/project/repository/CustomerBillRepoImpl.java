package com.project.repository;

import com.project.commons.DBConfig;
import com.project.model.BillModel;
import com.project.model.CustomerModel;
import com.project.model.PlaceOrderModel;

public class CustomerBillRepoImpl extends DBConfig implements ICustomerBillRepo {

	
	public BillModel getBillDetails(int bill_id) {
		BillModel billModel=null;
		try {
			ps=con.prepareStatement("select * from Bill_master where bill_id=?");
			ps.setInt(1, bill_id);
			rs=ps.executeQuery();
			billModel=new BillModel();
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
			}
			return billModel;
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
	
	public PlaceOrderModel getMenusByOrderId(int order_id) {
		PlaceOrderModel placeOrderModel=null;
		
		
		
		
		
		
		
		return placeOrderModel;
		
	}
	
	
	
}











