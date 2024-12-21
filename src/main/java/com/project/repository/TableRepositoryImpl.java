package com.project.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.commons.DBConfig;
import com.project.model.TableModel;

public class TableRepositoryImpl extends DBConfig implements ITableRepository {

	private static final String ADD_TABLE_IN_RESTAURENT = "INSERT INTO RESTO_TABLE VALUES('0', ?, ?, ?, ?)";
	private static final String SHOW_ALL_TABLES_BY_STAFFID = "SELECT TABLE_ID, TABLE_NUMBER, CAPACITY, STATUS FROM RESTO_TABLE WHERE STAFF_ID=?";
	private static final String DELETE_TABLE_BY_TABLENUMBER = "DELETE FROM RESTO_TABLE WHERE TABLE_NUMBER=?";
	private static final String UPDATE_TABLE_BY_TABLENUMBER = "UPDATE RESTO_TABLE SET TABLE_NUMBER=?, CAPACITY=? WHERE TABLE_NUMBER=?";
	
	
	@Override
	public boolean addNewTableInRestorent(TableModel tableModel) {
		
		try {
			
			ps = con.prepareStatement(ADD_TABLE_IN_RESTAURENT);
			ps.setInt(1, tableModel.getTable_number());
			ps.setInt(2, tableModel.getCapacity());
			ps.setString(3, tableModel.getTable_status());
			ps.setInt(4, tableModel.getStaff_id());
			int value = ps.executeUpdate();
			
			return value>0?true:false;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public List<TableModel> showAllTablesByStaffId(int staff_id) {
		
		List<TableModel> tableList = new ArrayList<TableModel>();
		
		try {
			
			ps = con.prepareStatement(SHOW_ALL_TABLES_BY_STAFFID);
			ps.setInt(1, staff_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				TableModel tableModel = new TableModel();
				tableModel.setTable_id(rs.getInt("table_id"));
				tableModel.setTable_number(rs.getInt("table_Number"));
				tableModel.setCapacity(rs.getInt("capacity"));
				tableModel.setTable_status(rs.getString("status"));
				tableList.add(tableModel);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return tableList;
	}


	@Override
	public boolean deleteTableByTableNumber(int table_number) {
		
		try {
			
			ps = con.prepareStatement(DELETE_TABLE_BY_TABLENUMBER);
			ps.setInt(1, table_number);
			int value = ps.executeUpdate();
			
			return value>0?true:false;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	


	@Override
	public boolean updateTableByTableNumber(int table_number, int capacity, int old_table_number) {
		
		try {
			
			ps = con.prepareStatement(UPDATE_TABLE_BY_TABLENUMBER);
			ps.setInt(1, table_number);
			ps.setInt(2, capacity);
			ps.setInt(3, old_table_number);
			
			int value = ps.executeUpdate();
			
			return value>0?true:false;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
