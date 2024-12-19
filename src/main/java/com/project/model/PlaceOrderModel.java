package com.project.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderModel {
<<<<<<< HEAD
	private String cust_Name;
	private String contact;
=======
	private CustomerModel custModel;
>>>>>>> 7ea9f542d5f28e33853b0f662ff650643cc268b7
	private List<MenuModel> list;
	
	
}
