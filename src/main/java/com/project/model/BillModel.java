package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BillModel {

	private int bill_id;
	private int order_id;
	private int cust_id;
	private int CGST;
	private int SGST;
	private int discount;
	private int grand_total;
	private int table_id;
	private String  bill_date;
	
}
