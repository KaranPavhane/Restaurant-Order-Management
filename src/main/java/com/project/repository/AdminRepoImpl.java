package com.project.repository;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.project.commons.DBConfig;
import com.project.commons.LoggerApp;
import com.project.model.AdminModel;

public class AdminRepoImpl extends DBConfig implements  AdminRepo{

	Logger logger=LoggerApp.getLogger();
	@Override
	public boolean isAddNewAdmin(AdminModel admin) {
		
		try {
			ps=con.prepareStatement("insert into admin_master values ('0',?,?,?)");
			logger.info("admin username : "+admin.getAdmin_username());
			logger.info(admin.getAdmin_pass());
			logger.info(admin.getDob());
			ps.setString(1, admin.getAdmin_username());
			ps.setString(2, admin.getAdmin_pass());
			ps.setString(3, admin.getDob());
			ps.executeUpdate();
			return true;
			
		}catch(SQLException ex) {
			logger.error("SQL Error"+ex);
		}catch(Exception ex) {
			logger.fatal("Exception : "+ex);
		}
		
		return false;
	}

}
