package com.project.service;

import com.project.model.BillModel;
import com.project.model.CustomerModel;

public interface ICustomerBillService {

	public BillModel getBillDetails(int bill_id);
	
	public CustomerModel getCustDetail(int cust_id);
}
