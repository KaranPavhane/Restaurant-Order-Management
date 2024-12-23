package com.project.service;

import java.util.List;

import com.project.model.MenuModel;

public interface IMenuService {
	
	public boolean addNewMenu(MenuModel menuModel);
	public List<MenuModel> showAllmenuList();
	public List<MenuModel> getMenuNamesByGivenWord(String word);
	public boolean deleteMenuMyMenuName(int categery_id, String menu_name);
	public boolean updateMenuPriceByMenuName(int menu_price, int categery_id, String menu_name);

}
