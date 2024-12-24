package com.project.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.project.commons.DBConfig;
import com.project.model.CategeryModel;

public class CategeryRepositoryImpl extends DBConfig implements ICategeryRepository {

	private static final String ADD_NEW_CATEGERY_QUERY = "INSERT INTO CATEGERY_MASTER VALUES('0', ?)";
	private static final String SHOW_ALL_CATEGERIES_QUERY = "SELECT * FROM CATEGERY_MASTER";
	private static final String SEARCH_CATEGERY_BY_GIVENWORD = "SELECT * FROM CATEGERY_MASTER WHERE CATEGERY_NAME LIKE ?";
	private static final String DELETE_CATEGERY_QUERY = "DELETE FROM CATEGERY_MASTER WHERE CATEGERY_NAME=?";
	private static final String UPDATE_CATEGERIES_QUERY = "UPDATE CATEGERY_MASTER SET CATEGERY_NAME=? WHERE CATEGERY_ID=?";
	private static final String GET_CATEGERY_ID_QUERY = "SELECT CATEGERY_ID FROM CATEGERY_MASTER WHERE CATEGERY_NAME=?";
	
	
	private static final Logger logger = (Logger) LogManager.getLogger(CategeryRepositoryImpl.class);

	@Override
	public boolean addNewCategery(CategeryModel categery) {

		try {
			ps = con.prepareStatement(ADD_NEW_CATEGERY_QUERY);
			ps.setString(1, categery.getCategery_name());
			int value = ps.executeUpdate();

			return value > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return false;
	}

	@Override
	public List<CategeryModel> showAllCategeries() {
		List<CategeryModel> categeryList = new ArrayList<CategeryModel>();
		try {
			ps = con.prepareStatement(SHOW_ALL_CATEGERIES_QUERY);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategeryModel categery = new CategeryModel();
				categery.setCategery_id(rs.getInt("categery_id"));
				categery.setCategery_name(rs.getString("categery_name"));
				categeryList.add(categery);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return categeryList;
	}

	@Override
	public List<CategeryModel> getCategeryByGivenWord(String word) {

		List<CategeryModel> categeryList = new ArrayList<CategeryModel>();
		try {

			ps = con.prepareStatement(SEARCH_CATEGERY_BY_GIVENWORD);
			ps.setString(1, "%" + word + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				CategeryModel categeryModel = new CategeryModel();
				categeryModel.setCategery_id(rs.getInt("categery_id"));
				categeryModel.setCategery_name(rs.getString("categery_name"));
				categeryList.add(categeryModel);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return categeryList;
	}
	

	@Override
	public boolean deleteCategeryByName(String categery_name) {

		try {
			ps = con.prepareStatement(DELETE_CATEGERY_QUERY);
			ps.setString(1, categery_name);

			int value = ps.executeUpdate();

			return value > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return false;
	}
	

	@Override
	public boolean updateCategeryName(String newName, String oldName) {

		int cat_id = getCategeryNameByCategeryID(oldName);
		System.out.println("Categery Id is :: " + cat_id);

		if (cat_id == -1) {
			return false;
		}

		try {
			ps = con.prepareStatement(UPDATE_CATEGERIES_QUERY);
			ps.setString(1, newName);
			ps.setInt(2, cat_id);

			int value = ps.executeUpdate();

			return value > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return false;
	}

	private int getCategeryNameByCategeryID(String oldName) {

		System.out.println("Categery Name :: " + oldName);

		int id = -1;
		try {
			ps = con.prepareStatement(GET_CATEGERY_ID_QUERY);
			ps.setString(1, oldName);
			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt("categery_id");
			} else {
				System.out.println("Categery_Id Not Found with  " + oldName + "");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unknown Exception...");
		}

		return id;

	}

}
