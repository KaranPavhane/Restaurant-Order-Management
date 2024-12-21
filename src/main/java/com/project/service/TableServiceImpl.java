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
	public List<TableModel> showAllTablesByStaffId(int staff_id) {
		
		return tableRepo.showAllTablesByStaffId(staff_id);
	}

	@Override
	public boolean deleteTableByTableNumber(int staff_id, int table_number) {
		
		return tableRepo.deleteTableByTableNumber(staff_id, table_number);
	}
	
	
}
