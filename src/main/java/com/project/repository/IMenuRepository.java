package com.project.repository;

import java.util.List;

import com.project.model.MenuModel;

public interface IMenuRepository {
		
	public boolean addNewMenu(MenuModel menuModel);
	public List<MenuModel> showAllmenuList(int categery_id);
	public boolean deleteMenuByMenuName(int categery_id, String menu_name);
	public boolean updateMenuPriceByMenuName(String menu_name, int categery_id, int menuPrice);
}
