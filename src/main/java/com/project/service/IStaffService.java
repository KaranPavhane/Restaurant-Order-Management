package com.project.service;

import java.util.List;

import com.project.model.StaffModel;

public interface IStaffService {
	
	public boolean addNewStaff(StaffModel staffModel);
	public List<StaffModel> showAllStaffInRestaurent();
	public List<StaffModel> getStaffNameByGivenWord(String startWith);
	public boolean deleteStaffByName(String name);
	public boolean updateStaffByName(String newStaffName, String staffEmail, String staffContact, int salary, String oldStaffName);

}
