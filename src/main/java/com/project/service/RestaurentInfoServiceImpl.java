package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.repository.IRestaurestInfoRepository;
import com.project.repository.RestaurentInfoRepositoryImpl;

public class RestaurentInfoServiceImpl implements IRestaurentInfoService {

	private IRestaurestInfoRepository restInfoRepo = new RestaurentInfoRepositoryImpl();
	
	@Override
	public List<Map<String, Object>> displayAllInfo() {
		
		return restInfoRepo.displayAllInfo();
	}

}
