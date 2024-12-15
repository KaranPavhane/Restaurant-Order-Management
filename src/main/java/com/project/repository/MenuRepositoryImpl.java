package com.project.repository;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project.commons.DBConfig;
import com.project.commons.LoggerApp;
import com.project.model.MenuModel;

public class MenuRepositoryImpl extends DBConfig implements IMenuRepository {

	Logger logger = LoggerApp.getLogger();
	
	private static final String ADD_MENU_BY_PROCEDURE_QUERY = "call add_menu_procedure(?, ?, ?, ?)";
	private static final String SHOW_ALL_MENUS_GIVEN_CATEGERY_ID = "SELECT * FROM MENU_MASTER WHERE CATEGERY_ID=?"; 
	private static final String DELETE_MENU_BY_MENU_NAME = "DELETE FROM  MENU_MASTER WHERE CATEGERY_ID=? AND MENU_NAME=?";
	
	@Override
	public boolean addNewMenu(MenuModel menuModel) {
		
		try {
			CallableStatement cs = con.prepareCall(ADD_MENU_BY_PROCEDURE_QUERY);
			cs.setString(1, menuModel.getMenu_name());
			cs.setInt(2, menuModel.getPrice());
			cs.setString(3, menuModel.getDescription());
			cs.setInt(4, menuModel.getCategery_id());
			
			int value = cs.executeUpdate();
			return value>0?true:false;
			
		}catch(SQLException e) {
			logger.error("MenuRepositoryImpl :: "+ e);
		}catch(Exception e) {
			logger.fatal("MenuRepositoryImpl :: "+ e);
		}
		
		return false;
	}


	@Override
	public List<MenuModel> showAllmenuList(int categery_id) {
		
		List<MenuModel> menuList = new ArrayList<MenuModel>();
		try {
			ps = con.prepareStatement(SHOW_ALL_MENUS_GIVEN_CATEGERY_ID);
			ps.setInt(1, categery_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				MenuModel menu = new MenuModel();
				menu.setMenu_id(rs.getInt("menu_id"));
				menu.setMenu_name(rs.getString("menu_name"));
				menu.setPrice(rs.getInt("price"));
				menu.setDescription(rs.getString("disception"));
				menuList.add(menu);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			logger.error("MenuRepositoryImpl :: "+ e);
		}catch(Exception e) {
			logger.fatal("MenuRepositoryImpl :: "+ e);
		}
		
		return menuList;
	}


	@Override
	public boolean deleteMenuByMenuName(int categery_id, String menu_name) {
		
		showAllmenuList(categery_id);
		
		try {
			ps = con.prepareStatement(DELETE_MENU_BY_MENU_NAME);
			ps.setInt(1, categery_id);
			ps.setString(2, menu_name);
			
			int value = ps.executeUpdate();
			
			return value>0?true:false;
			
		}catch(SQLException e) {
			e.printStackTrace();
			logger.error("MenuRepositoryImpl :: "+ e);
		}catch(Exception e) {
			logger.fatal("MenuRepositoryImpl :: "+ e);
		}
		
		return false;
	}


	@Override
	public boolean updateMenuPriceByMenuName(String menu_name, int categery_id, int menuPrice) {
		
		return false;
	}

}
