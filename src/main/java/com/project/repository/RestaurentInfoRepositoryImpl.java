package com.project.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.project.commons.DBConfig;

public class RestaurentInfoRepositoryImpl extends DBConfig implements IRestaurestInfoRepository {

	
	
	private static final String GET_ALL_RESTAURENT_DATA = "CALL GETALLRESTAURENTDATA()";
	
	
	private static final Logger logger = (Logger) LogManager.getLogger(RestaurentInfoRepositoryImpl.class);

	
	
	@Override
	public List<Map<String, Object>> displayAllInfo() {
		
		List<Map<String, Object>> restoList = new ArrayList<Map<String,Object>>();
		
		try {
			
			ps = con.prepareCall(GET_ALL_RESTAURENT_DATA);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> restaurentData = new HashMap<String, Object>();
				restaurentData.put("custId", Integer.valueOf(rs.getInt("cust_id")));
				restaurentData.put("custName", rs.getString("cust_name"));
				restaurentData.put("contact", rs.getString("contact"));
				restaurentData.put("email", rs.getString("email"));
				restaurentData.put("categoryName", rs.getString("categery_name"));
				restaurentData.put("menuName", rs.getString("menu_name"));
				restaurentData.put("price", Double.valueOf(rs.getDouble("price")));
				restaurentData.put("tableNumber", Integer.valueOf(rs.getInt("table_number")));
				restaurentData.put("cgst", Double.valueOf(rs.getDouble("CGST"))); 
				restaurentData.put("sgst", Double.valueOf(rs.getDouble("SGST"))); 
				restaurentData.put("discount", Double.valueOf(rs.getDouble("discount"))); 
				restaurentData.put("grandTotal", Double.valueOf(rs.getDouble("grand_total"))); 
				restaurentData.put("billDate", rs.getString("bill_date"));
				
				restoList.add(restaurentData);

				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception...");
		}catch(Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}
		
		
		return restoList;
	}

}
