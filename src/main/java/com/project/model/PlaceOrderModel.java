package com.project.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderModel {
	private CustomerModel custModel;
	private List<MenuModel> list;
	private int table_number;
	
}
