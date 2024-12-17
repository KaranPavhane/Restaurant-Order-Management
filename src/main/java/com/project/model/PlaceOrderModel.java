package com.project.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderModel {
	private String cust_Name;
	private String contact;
	private List<MenuModel> list;

	
}
