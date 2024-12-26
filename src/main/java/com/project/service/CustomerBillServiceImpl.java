package com.project.service;

import java.util.List;

import com.project.model.BillModel;
import com.project.model.CustomerModel;
import com.project.model.MenuModel;
import com.project.repository.CustomerBillRepoImpl;
import com.project.repository.ICustomerBillRepo;

public class CustomerBillServiceImpl implements ICustomerBillService{
	ICustomerBillRepo billRepo=new CustomerBillRepoImpl();

	@Override
	public BillModel getBillDetails(int bill_id) {
		// TODO Auto-generated method stub
		return billRepo.getBillDetails(bill_id);
	}

	@Override
	public CustomerModel getCustDetail(int cust_id) {
		// TODO Auto-generated method stub
		return billRepo.getCustDetail(cust_id);
	}

	@Override
	public List<MenuModel> getMenusByOrderId(int order_id) {
		// TODO Auto-generated method stub
		return billRepo.getMenusByOrderId(order_id);
	}
	

}
