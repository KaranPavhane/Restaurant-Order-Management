package com.project.service;

import com.project.model.BillModel;
import com.project.model.CustomerModel;
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
	

}
