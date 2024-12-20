package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffModel {

	private int staff_id;
	private String staff_name;
	private String staff_email;
	private String contact;
	private int salary;
	private String date_joined;
	
}
