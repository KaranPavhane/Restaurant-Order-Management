package com.project.repository;

import java.util.List;

import com.project.model.TableModel;

public interface ITableRepository {
	
	public boolean addNewTableInRestorent(TableModel tableModel);
	public List<TableModel> showAllTablesByStaffId(int staff_id);
	public boolean deleteTableByTableNumber(int staff_id, int table_number);
}