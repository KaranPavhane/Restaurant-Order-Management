package com.project.service;

import java.util.List;

import com.project.model.TableModel;
import com.project.repository.ITableRepository;
import com.project.repository.TableRepositoryImpl;

public class TableServiceImpl implements  ITableService {
	
	ITableRepository tableRepo = new TableRepositoryImpl();

	@Override
	public boolean addNewTableInRestorent(TableModel tableModel) {
		
		return tableRepo.addNewTableInRestorent(tableModel);
	}

	@Override
	public List<TableModel> showAllTablesByStaffId() {
		
		return tableRepo.showAllTablesByStaffId();
	}

	@Override
	public boolean deleteTableByTableNumber(int table_number) {
		
		return tableRepo.deleteTableByTableNumber(table_number);
	}

	@Override
	public boolean updateTableByTableNumber(int table_number, int capacity, int old_table_number) {
		
		return tableRepo.updateTableByTableNumber(table_number, capacity, old_table_number);
	}
	
	
}
