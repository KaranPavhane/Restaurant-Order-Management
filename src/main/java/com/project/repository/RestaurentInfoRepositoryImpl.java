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

	private static final String GET_DAILY_RESTAURENT_DATA = "CALL getAllRestaurentDataDaily()";
	private static final String GET_WEEKLY_RESTAURENT_DATA = "CALL getAllRestaurentDataWeekly()";
	private static final String GET_MONTHLY_RESTAURENT_DATA = "CALL getAllRestaurentDataMonthly()";
	private static final String GET_YEARLY_RESTAURENT_DATA = "CALL getAllRestaurentDataYearly()";
	private static final String GET_ALL_RESTAURENT_DATA = "CALL GETALLRESTAURENTDATA()";

	private static final String GET_RESTAURENT_CUSTOMER_DATA_BETWEEN_TWO_DATES = "SELECT C.CUST_ID, C.CUST_NAME, T.TABLE_NUMBER, B.BILL_DATE FROM CUSTOMER_MASTER C INNER JOIN ORDER_MASTER O ON C.CUST_ID = O.CUST_ID INNER JOIN BILL_MASTER B ON O.ORDER_ID = B.ORDER_ID INNER JOIN RESTO_TABLE T ON B.TABLE_ID = T.TABLE_ID WHERE B.BILL_DATE BETWEEN ? AND ?";
	private static final String GET_RESTAURENT_CUSTOMER_HEIGHEST_TABLENUMBER_DATA = "SELECT T.TABLE_NUMBER, COUNT(C.CUST_ID) AS TOTAL_CUSTOMERS FROM CUSTOMER_MASTER C INNER JOIN ORDER_MASTER O ON C.CUST_ID = O.CUST_ID INNER JOIN BILL_MASTER B ON O.ORDER_ID = B.ORDER_ID INNER JOIN RESTO_TABLE T ON B.TABLE_ID = T.TABLE_ID GROUP BY T.TABLE_NUMBER ORDER BY TOTAL_CUSTOMERS DESC LIMIT 1";

	private static final Logger logger = (Logger) LogManager.getLogger(RestaurentInfoRepositoryImpl.class);

	// all customer data dayly
	@Override
	public List<Map<String, Object>> displayDailyInfo() {
		List<Map<String, Object>> restoList = new ArrayList<>();

		try {
			ps = con.prepareCall(GET_DAILY_RESTAURENT_DATA);
			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> restaurentData = new HashMap<>();
				restaurentData.put("custId", rs.getInt("cust_id"));
				restaurentData.put("custName", rs.getString("cust_name"));
				restaurentData.put("contact", rs.getString("contact"));
				restaurentData.put("email", rs.getString("email"));
				restaurentData.put("categoryName", rs.getString("categery_name"));
				restaurentData.put("menuName", rs.getString("menu_name"));
				restaurentData.put("price", rs.getDouble("price"));
				restaurentData.put("tableNumber", rs.getInt("table_number"));
				restaurentData.put("cgst", rs.getDouble("CGST"));
				restaurentData.put("sgst", rs.getDouble("SGST"));
				restaurentData.put("discount", rs.getDouble("discount"));
				restaurentData.put("grandTotal", rs.getDouble("grand_total"));
				restaurentData.put("billDate", rs.getTimestamp("bill_date"));

				restoList.add(restaurentData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception Fetching Daily Data.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception Fetching Daily Data.");
		}

		return restoList;
	}

	// all customer weekly data

	@Override
	public List<Map<String, Object>> displayWeeklyInfo() {
		List<Map<String, Object>> restoList = new ArrayList<>();

		try {
			ps = con.prepareCall(GET_WEEKLY_RESTAURENT_DATA);
			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> restaurentData = new HashMap<>();
				restaurentData.put("custId", rs.getInt("cust_id"));
				restaurentData.put("custName", rs.getString("cust_name"));
				restaurentData.put("contact", rs.getString("contact"));
				restaurentData.put("email", rs.getString("email"));
				restaurentData.put("categoryName", rs.getString("categery_name"));
				restaurentData.put("menuName", rs.getString("menu_name"));
				restaurentData.put("price", rs.getDouble("price"));
				restaurentData.put("tableNumber", rs.getInt("table_number"));
				restaurentData.put("cgst", rs.getDouble("CGST"));
				restaurentData.put("sgst", rs.getDouble("SGST"));
				restaurentData.put("discount", rs.getDouble("discount"));
				restaurentData.put("grandTotal", rs.getDouble("grand_total"));
				restaurentData.put("billDate", rs.getString("bill_date"));

				restoList.add(restaurentData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return restoList;
	}

	@Override
	public List<Map<String, Object>> displayMonthlyInfo() {
		List<Map<String, Object>> restoList = new ArrayList<>();

		try {
			ps = con.prepareCall(GET_MONTHLY_RESTAURENT_DATA);
			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> restaurentData = new HashMap<>();
				restaurentData.put("custId", rs.getInt("cust_id"));
				restaurentData.put("custName", rs.getString("cust_name"));
				restaurentData.put("contact", rs.getString("contact"));
				restaurentData.put("email", rs.getString("email"));
				restaurentData.put("categoryName", rs.getString("categery_name"));
				restaurentData.put("menuName", rs.getString("menu_name"));
				restaurentData.put("price", rs.getDouble("price"));
				restaurentData.put("tableNumber", rs.getInt("table_number"));
				restaurentData.put("cgst", rs.getDouble("CGST"));
				restaurentData.put("sgst", rs.getDouble("SGST"));
				restaurentData.put("discount", rs.getDouble("discount"));
				restaurentData.put("grandTotal", rs.getDouble("grand_total"));
				restaurentData.put("billDate", rs.getTimestamp("bill_date"));

				restoList.add(restaurentData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception Fetching Monthly Data.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception Fetching Monthly Data.");
		}

		return restoList;
	}

	// yearly customer data
	@Override
	public List<Map<String, Object>> displayYearlyInfo() {
		List<Map<String, Object>> restoList = new ArrayList<>();

		try {
			ps = con.prepareCall(GET_YEARLY_RESTAURENT_DATA);
			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> restaurentData = new HashMap<>();
				restaurentData.put("custId", rs.getInt("cust_id"));
				restaurentData.put("custName", rs.getString("cust_name"));
				restaurentData.put("contact", rs.getString("contact"));
				restaurentData.put("email", rs.getString("email"));
				restaurentData.put("categoryName", rs.getString("categery_name"));
				restaurentData.put("menuName", rs.getString("menu_name"));
				restaurentData.put("price", rs.getDouble("price"));
				restaurentData.put("tableNumber", rs.getInt("table_number"));
				restaurentData.put("cgst", rs.getDouble("CGST"));
				restaurentData.put("sgst", rs.getDouble("SGST"));
				restaurentData.put("discount", rs.getDouble("discount"));
				restaurentData.put("grandTotal", rs.getDouble("grand_total"));
				restaurentData.put("billDate", rs.getTimestamp("bill_date"));

				restoList.add(restaurentData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception Fetching Yearly Data.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception Fetching Yearly Data.");
		}

		return restoList;
	}

	// all restaurent customer data
	@Override
	public List<Map<String, Object>> displayAllInfo() {

		List<Map<String, Object>> restoList = new ArrayList<Map<String, Object>>();

		try {

			ps = con.prepareCall(GET_ALL_RESTAURENT_DATA);
			rs = ps.executeQuery();

			while (rs.next()) {

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

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return restoList;
	}

	public List<Map<String, Object>> getCustomersBetweenDates(String startDate, String endDate) {

		List<Map<String, Object>> customerList = new ArrayList<>();

		try {

			ps = con.prepareStatement(GET_RESTAURENT_CUSTOMER_DATA_BETWEEN_TWO_DATES);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> customerData = new HashMap<>();
				customerData.put("custId", rs.getInt("cust_id"));
				customerData.put("custName", rs.getString("cust_name"));
				customerData.put("tableNumber", rs.getInt("table_number"));
				customerData.put("billDate", rs.getString("bill_date"));

				customerList.add(customerData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception in Fetching Time Between Dates...");
		}

		return customerList;
	}

	public Map<String, Object> getTableWithHighestCustomers() {
		Map<String, Object> result = new HashMap<>();

		try {

			ps = con.prepareStatement(GET_RESTAURENT_CUSTOMER_HEIGHEST_TABLENUMBER_DATA);
			rs = ps.executeQuery();

			if (rs.next()) {
				result.put("tableNumber", rs.getInt("table_number"));
				result.put("totalCustomers", rs.getInt("total_customers"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQL Exception Fetching Table With Highest Customers...");
		}

		return result;
	}

}
