package com.project.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConfig {
	DBConfiguration config =DBConfiguration.getInstance();
	protected Connection con=DBConfiguration.getConnection();
	protected PreparedStatement ps=DBConfiguration.getStatement();
	protected ResultSet rs=DBConfiguration.getResult();
	
}
