/**
 * Copyright (2020, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.frk;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;


/**
 * @author wuheng09@gmail.com
 *
 */
public abstract class SQLOperator {

	protected final Connection conn;
	
	protected final String table;
	
	public SQLOperator(Connection conn, String table) {
		super();
		this.conn = conn;
		this.table = table;
		this.dropTable();
		this.createTable();
	}

	public void dropTable() {
		try {
			PreparedStatement ps = createStatement("DROP TABLE IF EXISTS \"" + table +"\"");
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTable() {
		try {
			PreparedStatement ps = createStatement(createSql());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement createStatement(String sql) throws Exception {
		return conn.prepareStatement(sql);
	}
	
	public static Connection createConn(String driver, String urlPrefix, 
			String user, String pwd, String db) throws Exception {
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(
				urlPrefix + db, user, pwd);
	}
	
	public void exec(PreparedStatement ps, List<Object> list) throws Exception {
		for (int i = 1; i <= list.size(); i++) {
			Object obj = list.get(i - 1);
			if (obj instanceof String) {
				ps.setString(i, (String) obj);
			} else if (obj instanceof Integer) {
				ps.setInt(i, (int) obj);
			} else if (obj instanceof Timestamp) {
				ps.setTimestamp(i, (Timestamp) obj);
			} else {
				System.out.println("Unsupport!");
			}
		}
		
		ps.executeUpdate();
		
	}
	
	public abstract String createSql();
	
	public abstract String insertSql();
	
}
