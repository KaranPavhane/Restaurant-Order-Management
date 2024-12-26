package com.project.repository;

import java.util.List;

import com.project.model.BillModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;

public interface ICustomerBillRepo {
	public BillModel getBillDetails(int bill_id);
	
	public CustomerModel getCustDetail(int cust_id);
	
	public List<MenuModel> getMenusByOrderId(int order_id);
}
