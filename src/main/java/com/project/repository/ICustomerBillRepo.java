package com.project.repository;

import com.project.model.BillModel;
import com.project.model.CustomerModel;

public interface ICustomerBillRepo {
	public BillModel getBillDetails(int bill_id);
	
	public CustomerModel getCustDetail(int cust_id);
}
