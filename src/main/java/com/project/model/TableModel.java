package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableModel {
	private int table_id;
	private int table_number;
	private int capacity;
	private String table_status;
	private int staff_id;
	
	
}
