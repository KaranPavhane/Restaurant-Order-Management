package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminModel {

	private int admin_id;
	private String admin_username;
	private String admin_pass;
	private String dob;
	
}
