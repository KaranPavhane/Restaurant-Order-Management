package com.project.service;

import java.util.List;

import com.project.model.CategeryModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;

public interface ICustomerService {
	public List<CategeryModel> getAllCategery();
	
	public List<MenuModel> getMenuByCategery(String cat_name);
	
	public boolean isPlacedNewOrder(PlaceOrderModel placeOrder);
}
