package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel {
	private int menu_id;
	private String menu_name;
	private int price;
	private String  Description;
	private int categery_id;
	private int qty;
	
}
