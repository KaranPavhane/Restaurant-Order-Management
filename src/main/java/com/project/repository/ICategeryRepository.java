package com.project.repository;

import java.util.List;

import com.project.model.CategeryModel;

public interface ICategeryRepository {
	
	public boolean addNewCategery(CategeryModel categery);
	public List<CategeryModel> showAllCategeries();
	public List<CategeryModel> getCategeryByGivenWord(String wrod);
	public boolean deleteCategeryByName(String categery_name);
	public boolean updateCategeryName(String oldName, String newName);
	
}
