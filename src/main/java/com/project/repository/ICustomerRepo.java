package com.project.repository;

import java.util.List;

import com.project.model.CategeryModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;
import com.project.model.TableModel;



public interface ICustomerRepo {

	public String getStaffNameByTableId(int table_id);
	
	public CustomerModel getCustomerDetailsByEmail(String email);
	
	public List<CategeryModel> getAllCategery();
	
	public List<MenuModel> getMenuByCategery(String cat_name);
	
	public int isPlacedNewOrder(PlaceOrderModel placeOrder);
	
	public List<TableModel> availableTable();
}
