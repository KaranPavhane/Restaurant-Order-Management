package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.repository.IRestaurestInfoRepository;
import com.project.repository.RestaurentInfoRepositoryImpl;

public class RestaurentInfoServiceImpl implements IRestaurentInfoService {

	private IRestaurestInfoRepository restInfoRepo = new RestaurentInfoRepositoryImpl();
	
	
	
	@Override
	public List<Map<String, Object>> displayDailyInfo() {
		
		return restInfoRepo.displayDailyInfo();
	}

	@Override
	public List<Map<String, Object>> displayWeeklyInfo() {
		
		return restInfoRepo.displayWeeklyInfo();
	}
	
	@Override
	public List<Map<String, Object>> displayMonthlyInfo() {
		
		return restInfoRepo.displayMonthlyInfo();
	}

	

	@Override
	public List<Map<String, Object>> displayYearlyInfo() {
		
		return restInfoRepo.displayYearlyInfo();
	}
	
	@Override
	public List<Map<String, Object>> displayAllInfo() {
		
		return restInfoRepo.displayAllInfo();
	}

	

}
