package com.project.repository;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.project.commons.DBConfig;
import com.project.model.AdminModel;



public class AdminRepoImpl extends DBConfig implements  IAdminRepo{
	
	private static final String ADD_ADMIN_QUERY = "INSERT INTO ADMIN_MASTER VALUES ('0',?,?,?)";
	private static final String LOGIN_ADMIN_QUERY = "SELECT * FROM ADMIN_MASTER WHERE USERNAME=? AND PASSWORD=?";
	
	
	private static final Logger logger = (Logger) LogManager.getLogger(AdminRepoImpl.class);

	
	
	@Override
	public boolean isAddNewAdmin(AdminModel admin) {
		try {
			ps=con.prepareStatement(ADD_ADMIN_QUERY);
			
			ps.setString(1, admin.getAdmin_username());
			ps.setString(2, admin.getAdmin_pass());
			ps.setString(3, admin.getDob());
			
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
	public boolean loginAdmin(String username, String password) {
		
		try {
			
			ps = con.prepareStatement(LOGIN_ADMIN_QUERY);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			return rs.next();
			
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
