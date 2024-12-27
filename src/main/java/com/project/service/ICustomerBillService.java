package com.project.service;

import java.util.List;

import com.project.model.BillModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;

public interface ICustomerBillService {

	public BillModel getBillDetails(int bill_id);
	
	public CustomerModel getCustDetail(int cust_id);
	
	public List<MenuModel> getMenusByOrderId(int order_id);
}
