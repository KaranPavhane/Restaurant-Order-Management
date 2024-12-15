package com.project.service;

import java.util.List;

import com.project.model.CategeryModel;
import com.project.model.MenuModel;
import com.project.model.PlaceOrderModel;
import com.project.repository.CustomerRepoImpl;
import com.project.repository.ICustomerRepo;

public class CustomerServiceImpl implements ICustomerService {
	ICustomerRepo custRepo=new CustomerRepoImpl();
	@Override
	public List<CategeryModel> getAllCategery() {
		return custRepo.getAllCategery();
	}
	@Override
	public List<MenuModel> getMenuByCategery(String cat_name) {
		// TODO Auto-generated method stub
		return custRepo.getMenuByCategery(cat_name);
	}
	@Override
	public boolean isPlacedNewOrder(PlaceOrderModel placeOrder) {
		// TODO Auto-generated method stub
		return custRepo.isPlacedNewOrder(placeOrder);
	}
	
}