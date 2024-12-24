package com.project.service;

import java.util.List;

import com.project.model.MenuModel;
import com.project.repository.IMenuRepository;
import com.project.repository.MenuRepositoryImpl;

public class MenuServiceImpl implements IMenuService {

	IMenuRepository menuService = new MenuRepositoryImpl();

	@Override
	public boolean addNewMenu(MenuModel menuModel) {
		
		return menuService.addNewMenu(menuModel);
	}

	@Override
	public List<MenuModel> showAllmenuList() {
		
		return menuService.showAllmenuList();
	}
	
	@Override
	public List<MenuModel> getMenuNamesByGivenWord(String word) {
		
		return menuService.getMenuNamesByGivenWord(word);
	}
	

	@Override
	public boolean deleteMenuMyMenuName(int categery_id, String menu_name) {
		
		return menuService.deleteMenuByMenuName(categery_id, menu_name);
	}

	@Override
	public boolean updateMenuPriceByMenuName(int menu_price, int categery_id, String menu_name) {
		
		return menuService.updateMenuPriceByMenuName(menu_price, categery_id, menu_name);
	}



}
