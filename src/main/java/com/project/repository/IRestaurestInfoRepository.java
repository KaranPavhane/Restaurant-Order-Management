package com.project.repository;

import java.util.List;
import java.util.Map;

public interface IRestaurestInfoRepository {
	
	public List<Map<String, Object>> displayAllInfo();
	
	public List<Map<String, Object>> displayWeeklyInfo();
	
	public List<Map<String, Object>> displayMonthlyInfo();
	
	public List<Map<String, Object>> displayDailyInfo();
	
	public List<Map<String, Object>> displayYearlyInfo();

}
