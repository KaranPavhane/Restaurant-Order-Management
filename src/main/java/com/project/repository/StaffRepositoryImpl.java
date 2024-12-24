package com.project.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.project.commons.DBConfig;
import com.project.model.StaffModel;

public class StaffRepositoryImpl extends DBConfig implements IStaffRepository {

	private static final String Add_NEW_STAFF = "INSERT INTO STAFF_MASTER (staff_name, staff_email, contact, salary) VALUES (?, ?, ?, ?)";
	private static final String SHOW_ALL_STAFF_IN_RESTAURENT = "SELECT * FROM STAFF_MASTER";
	private static final String SEARCH_STAFFNAME_WORD_WITH = "SELECT * FROM STAFF_MASTER WHERE STAFF_NAME LIKE ?";	
	private static final String DELETE_STAFF_BY_NAME = "DELETE FROM STAFF_MASTER WHERE STAFF_NAME=?";
	private static final String UPDATE_STAFF_BY_NAME = "UPDATE STAFF_MASTER SET STAFF_NAME=?, STAFF_EMAIL=?, CONTACT=?, SALARY=? WHERE STAFF_NAME=?";
	
	
	
	private static final Logger logger = (Logger) LogManager.getLogger(StaffRepositoryImpl.class);

	
	
	@Override
	public boolean addNewStaff(StaffModel staffModel) {
		
		try {
			
			ps = con.prepareStatement(Add_NEW_STAFF);
			ps.setString(1, staffModel.getStaff_name());
			ps.setString(2, staffModel.getStaff_email());
			ps.setString(3, staffModel.getContact());
			ps.setInt(4, staffModel.getSalary());
			
			int value = ps.executeUpdate();
			
			return value>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}
		
		return false;
	}


	@Override
	public List<StaffModel> showAllStaffInRestaurent() {
		
		List<StaffModel> staffList = new ArrayList<StaffModel>();
		try {
			
			ps = con.prepareStatement(SHOW_ALL_STAFF_IN_RESTAURENT);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StaffModel staff = new StaffModel();
				staff.setStaff_id(rs.getInt("staff_id"));
				staff.setStaff_name(rs.getString("staff_name"));
				staff.setStaff_email(rs.getString("staff_email"));
				staff.setContact(rs.getString("contact"));
				staff.setSalary(rs.getInt("salary"));
				staff.setDate_joined(rs.getString("date_joined"));
				staffList.add(staff);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}
		
		return staffList;
	}


	
	@Override
	public List<StaffModel> getStaffNameByGivenWord(String word) {
		
		List<StaffModel> staffList = new ArrayList<StaffModel>();
		
		try {
			
			ps = con.prepareStatement(SEARCH_STAFFNAME_WORD_WITH);
			ps.setString(1, "%"+ word + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StaffModel staffModel = new StaffModel();
				staffModel.setStaff_id(rs.getInt("staff_id"));
				staffModel.setStaff_name(rs.getString("staff_name"));
				staffModel.setStaff_email(rs.getString("staff_email"));
				staffModel.setContact(rs.getString("contact")); 
				staffModel.setSalary(rs.getInt("salary")); 
				staffModel.setDate_joined(rs.getString("date_joined")); 
				staffList.add(staffModel);
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}
		
		return staffList;
	}
	
	
	@Override
	public boolean deleteStaffByName(String name) {
		
		try {
			
			ps = con.prepareStatement(DELETE_STAFF_BY_NAME);
			ps.setString(1, name);
			int value = ps.executeUpdate();
			
			return value>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}
		
		
		return false;
	}


	@Override
	public boolean updateStaffByName(String newStaffName, String staffEmail, String staffContact, int salary, String oldStaffName) {
		
		try {
			
			ps = con.prepareStatement(UPDATE_STAFF_BY_NAME);
			ps.setString(1, newStaffName);
			ps.setString(2, staffEmail);
			ps.setString(3, staffContact);
			ps.setInt(4, salary);
			ps.setString(5, oldStaffName);
			
			int value = ps.executeUpdate();
			
			return value>0?true:false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}
		
		return false;
	}


	

}
