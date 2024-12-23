package com.project.service;

import java.util.List;

import com.project.model.TableModel;

public interface ITableService {
	
	public boolean addNewTableInRestorent(TableModel tableModel);
	public List<TableModel> showAllTablesByStaffId(int staff_id);
	public boolean deleteTableByTableNumber(int table_number);
	public boolean updateTableByTableNumber(int table_number, int capacity, int old_table_number);

}
