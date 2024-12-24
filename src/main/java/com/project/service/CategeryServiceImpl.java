package com.project.service;

import java.util.List;

import com.project.model.CategeryModel;
import com.project.repository.CategeryRepositoryImpl;
import com.project.repository.ICategeryRepository;

public class CategeryServiceImpl implements ICategeryService{
	
	ICategeryRepository iCategery = new CategeryRepositoryImpl();
	
	@Override
	public boolean addNewCategery(CategeryModel categery) {
		return iCategery.addNewCategery(categery);
	}

	@Override
	public boolean deleteCategeryByName(String categery_name) {
		return iCategery.deleteCategeryByName(categery_name);
	}
	
	@Override
	public List<CategeryModel> getCategeryByGivenWord(String word) {
		return iCategery.getCategeryByGivenWord(word);
	}

	@Override
	public List<CategeryModel> showAllCategeries() {
		return iCategery.showAllCategeries();
	}

	@Override
	public boolean updateCategeryName(String oldName, String newName) {
		return iCategery.updateCategeryName(oldName, newName);
	}

	

}
