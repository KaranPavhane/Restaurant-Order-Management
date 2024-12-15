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
	public List<MenuModel> showAllmenuList(int categery_id) {
		
		return menuService.showAllmenuList(categery_id);
	}

	@Override
	public boolean deleteMenuMyMenuName(int categery_id, String menu_name) {
		
		return menuService.deleteMenuByMenuName(categery_id, menu_name);
	}

}