package com.project.repository;

import java.util.List;

import com.project.model.MenuModel;

public interface IMenuRepository {
		
	public boolean addNewMenu(MenuModel menuModel);
	public List<MenuModel> showAllmenuList();
	public List<MenuModel> getMenuNamesByGivenWord(String word);
	public boolean deleteMenuByMenuName(int categery_id, String menu_name);
	public boolean updateMenuPriceByMenuName(int menu_price, int categery_id, String menu_name);
}
