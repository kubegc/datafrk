/*

 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.2.0
 * @since   2020/4/23
 *
 */
public abstract class KubeSQLClient {
	
	public static final Logger m_logger = Logger.getLogger(KubeSQLClient.class.getName());

	public static final String DEFAULT_DB       = System.getenv("jdbcDB") == null 
													? "kube" : System.getenv("jdbcDB");
	
	public static final String LABEL_DATABASE   = "#DATBASE#";
	
	public static final String LABEL_TABLE      = "#TABLE#";
	
	public static final String LABEL_NAME       = "#NAME#";
	
	public static final String LABEL_NAMESPACE  = "#NAMESPACE#";
	
	public static final String LABEL_TIME       = "#TIME#";
	
	public static final String LABEL_JSON       = "#JSON#";
	
	public static final String DELETE_DATABASE  = "DROP DATABASE #DATBASE#";
	
	public static final String DELETE_TABLE     = "DROP TABLE #TABLE#";
	
	public static final String INSERT_OBJECT    = "INSERT INTO #TABLE# VALUES ('#NAME#', '#NAMESPACE#', '#TIME#' , '#JSON#'::json)";
	
	public static final String UPDATE_OBJECT    = "UPDATE #TABLE# SET time = '#TIME#', data = '#JSON#'::json WHERE name = '#NAME#' and namespace = '#NAMESPACE#'";
	
	public static final String DELETE_OBJECT    = "DELETE FROM #TABLE# WHERE name = '#NAME#' and namespace = '#NAMESPACE#'";
	
	private static final String SELECT = "SELECT #TARGET# FROM #TABLE#";

	private static final String WHERE = " WHERE ";

	private static final String TARGET_DATA = "*";

	private static final String TARGET_COUNT = "count(*) as count";
	
	private static final String METADATA = "metadata";
	
	
	/****************************************************************************
	 * 
	 * 
	 *                         Basic
	 * 
	 * 
	 *****************************************************************************/
	
	/**
	 * conn
	 */
	protected DruidPooledConnection conn;
	
	/**
	 * db
	 */
	protected final String database;

	public KubeSQLClient(DruidPooledConnection conn) throws Exception {
		this(conn, DEFAULT_DB);
	}
	
	public KubeSQLClient(DruidPooledConnection conn, String database) throws Exception {
		super();
		this.conn = conn;
		this.database = database;
		if (!hasDatabase()) {
			createDatabase();
		}
		String url = conn.getMetaData().getURL();
		this.conn.close();
		updateConn(url, database);
	}

	void updateConn(String oldUrl, String database) throws SQLException {
		int stx = oldUrl.indexOf("/", getUrlPrefix().length());
		int etx = oldUrl.indexOf("?");
		String newUrl = etx == - 1 ? oldUrl.substring(0, stx + 1) + database :
				oldUrl.substring(0, stx + 1) + database + oldUrl.substring(etx);
		this.conn = createDataSourceFromResource(newUrl).getConnection();
	}

	/**
	 * @return                conn
	 */
	public DruidPooledConnection getConn() {
		return conn;
	}
	
	/****************************************************************************
	 * 
	 * 
	 *                         Database, Table
	 * 
	 * 
	 *****************************************************************************/
	
	/**
	 * @return                    true or false
	 * @throws Exception          exception
	 */
	public synchronized boolean hasDatabase() throws Exception {
		return execWithResultCheck(null, checkDatabaseSql().replace(LABEL_DATABASE, database));
	}
	
	/** 
	 * create database
	 * 
	 * @return                             true or false
	 * @throws Exception                   mysql exception
	 */
	public synchronized boolean createDatabase() throws Exception {
		return exec(null, createDatabaseSql().replace(LABEL_DATABASE, database));
	}
	
	/**
	 * @return delete database
	 * @throws Exception mysql exception
	 */
	public synchronized boolean dropDatabase() throws Exception {
		try {
			return exec(null, DELETE_DATABASE.replace(LABEL_DATABASE, database));
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * @param tableName               name
	 * @return                        true if the table exists, otherwise return false
	 * @throws Exception              mysql exception
	 */
	public synchronized boolean hasTable(String tableName) throws Exception {
		return execWithResultCheck(database, checkTableSql().replace(LABEL_DATABASE, database)
											.replace(LABEL_TABLE, tableName));
	}

	/**
	 * @param tableName               name
	 * @return                        true if the table exists, otherwise return false
	 * @throws Exception              mysql exception
	 */
	public synchronized boolean createTable(String tableName) throws Exception {
		if (tableName.contains("/")) {
			return false;
		}
		return exec(database, createTableSql().replace(LABEL_TABLE, tableName));
	}
	
	/**
	 * @param tableName               name
	 * @return                        true if the table exists, otherwise return false
	 * @throws Exception              mysql exception
	 */
	public synchronized boolean dropTable(String tableName) throws Exception {
		try {
			return exec(database, DELETE_TABLE.replace(LABEL_TABLE, tableName));
		} catch (Exception ex) {
			return false;
		}
	}
	
	/****************************************************************************
	 * 
	 * 
	 *                         Insert, Update, Delete objects
	 * 
	 * 
	 *****************************************************************************/
	/**
	 * @param table                                  table
	 * @param name                                   name
	 * @param namespace                              namespace
	 * @param time                                   time
	 * @param json                                   json
	 * @return                                       true or false
	 * @throws Exception                             exception
	 */
	public boolean insertObject(String table, String name, String namespace, long time, String json) throws Exception {
		if(!exec(database, INSERT_OBJECT
					.replace(KubeSQLClient.LABEL_TABLE, table)
					.replace(KubeSQLClient.LABEL_NAME, name)
					.replace(KubeSQLClient.LABEL_NAMESPACE, namespace)
					.replace(KubeSQLClient.LABEL_TIME, String.valueOf(time))
					.replace(KubeSQLClient.LABEL_JSON, json))) {
			return updateObject(table, name, namespace, time, json);
		}
		return true;
	}
	
	/**
	 * @param table                                  table
	 * @param name                                   name
	 * @param namespace                              namespace
	 * @param time                                   time
	 * @param json                                   json
	 * @return                                       true or false
	 * @throws Exception                             exception
	 */
	public boolean updateObject(String table, String name, String namespace, long time, String json) throws Exception {
		return exec(database, UPDATE_OBJECT
					.replace(KubeSQLClient.LABEL_TABLE, table)
					.replace(KubeSQLClient.LABEL_NAME, name)
					.replace(KubeSQLClient.LABEL_NAMESPACE, namespace)
					.replace(KubeSQLClient.LABEL_TIME, String.valueOf(time))
					.replace(KubeSQLClient.LABEL_JSON, json));		
	}
	
	/**
	 * @param table                                  table
	 * @param name                                   name
	 * @param namespace                              namespace
	 * @param json                                   json
	 * @return                                       true or false
	 * @throws Exception                             exception
	 */
	public boolean deleteObject(String table, String name, String namespace, String json) throws Exception {
		return exec(database, DELETE_OBJECT
					.replace(KubeSQLClient.LABEL_TABLE, table)
					.replace(KubeSQLClient.LABEL_NAME, name)
					.replace(KubeSQLClient.LABEL_NAMESPACE, namespace)
					.replace(KubeSQLClient.LABEL_JSON, json));		
	}
	
	
	/****************************************************************************
	 * 
	 * 
	 *                         Common
	 * 
	 * 
	 *****************************************************************************/
	
	/**
	 * @param dbName                          dbName
	 * @param sql                             sql
	 * @return                                true or false
	 * @throws Exception                      exception
	 */
	public boolean exec(String dbName, String sql) throws Exception {
		
		if (dbName != null) {
			conn.setCatalog(dbName);
		}
		
		PreparedStatement pstmt = null;
				
		
		try {
			pstmt = conn.prepareStatement(sql);
			return pstmt.execute();
		} catch (Exception ex) {
			m_logger.severe("caused by " + sql + ":" + ex);
			return false;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	/**
	 * @param dbName                          dbName
	 * @param sql                             sql
	 * @return                                true or false
	 * @throws Exception                      exception
	 */
	public boolean execWithResultCheck(String dbName, String sql) throws Exception {
		if (dbName != null) {
			conn.setCatalog(dbName);
		}
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception ex) {
			return false;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	/**
	 * @param sql                             sql
	 * @return                                true or false
	 * @throws Exception                      exception
	 */
	public ResultSet execWithResult(String sql) throws Exception {
		return execWithResult(database, sql); 
	}
	
	/**
	 * @param dbName                          dbName
	 * @param sql                             sql
	 * @return                                true or false
	 * @throws Exception                      exception
	 */
	public ResultSet execWithResult(String dbName, String sql) throws Exception {
		if (dbName != null) {
			conn.setCatalog(dbName);
		}
		
		try {
			return conn.prepareStatement(sql).executeQuery();
		} catch (Exception ex) {
			return null;
		} 
	}
	
	/****************************************************************************
	 * 
	 * 
	 *                         Common
	 * 
	 * 
	 *****************************************************************************/
	public JsonNode query(String table, String kind, int limit, int page, Map<String, String> labels) throws Exception {
		
		StringBuilder sqlBase = createSqlBase(table, labels);
		return getResults(kind, limit, page, sqlBase);
	}

	private ObjectNode getResults(String kind, int limit, int page, StringBuilder sqlBase) throws Exception {
		
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("kind", kind + "List");
		node.put("apiVersion", "v1");
		ObjectNode meta = new ObjectMapper().createObjectNode();
		{
			meta.put("totalCount", getRows(sqlBase));
			meta.put("continue", String.valueOf(page + 1));
		}
		node.set(METADATA, meta);
		node.set("items", getItems(limit, page, sqlBase));
		return node;
	}

	private ArrayNode getItems(int limit, int page, StringBuilder sqlBase) throws Exception {
		
		sqlBase.append(" order by time desc ").append(queryLimit(limit, page));
		String dataSql = sqlBase.toString().replace("#TARGET#", TARGET_DATA);
		ResultSet rsd = execWithResult(this.database, dataSql);
		ArrayNode items = new ObjectMapper().createArrayNode();
		
		while (rsd.next()) {
			items.add(new ObjectMapper().readTree(rsd.getString("data")));
		}
		rsd.close();
		return items;
	}

	private int getRows(StringBuilder sqlBase) throws Exception, SQLException {
		ResultSet rsc = execWithResult(this.database, sqlBase.toString()
						.replace("#TARGET#", TARGET_COUNT));
		rsc.next();
		
		int total = rsc.getInt("count");
		rsc.close();
		return total;
	}

	private StringBuilder createSqlBase(String table, Map<String, String> labels) {
		StringBuilder sqlBase = new StringBuilder();

		sqlBase.append(SELECT.replace("#TABLE#", table));
		
		if (labels != null && !labels.isEmpty()) {
			
			sqlBase.append(WHERE);
			for (String key : labels.keySet()) {
				
				StringBuilder sb = new StringBuilder();
				String[] items = key.split("#");
				int size = items.length;
				for (int i = 0; i < size; i++) {
					if (i != size - 1) {
						sb.append("->'" + items[i] + "'");
					} else {
						sb.append("->>'" + items[i] + "'");
					}
				}
				
				sqlBase.append(queryConditions().replace("#ITEM#", sb.toString()).replace("#VALUE#", labels.get(key)));
			}

			sqlBase.delete(sqlBase.length() - 4, sqlBase.length());
		}
		
		return sqlBase;
	}
	
	/**
	 * @return                               datasource
	 */
	public DruidDataSource createDataSourceFromResource() {
        Properties props = new Properties();
        props.put("druid.driverClassName", defaultDriver());
        props.put("druid.url", System.getenv("jdbcUrl") == null ? defaultUrl() : System.getenv("jdbcUrl"));
        props.put("druid.username", System.getenv("jdbcUser") == null ? defaultUser() : System.getenv("jdbcUser"));
        props.put("druid.password", System.getenv("jdbcPassword") == null ? defaultPassword() : System.getenv("jdbcPassword"));
        props.put("druid.initialSize", 10);
        props.put("druid.maxActive", 100);
        props.put("druid.maxWait", 0);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(props);
        return dataSource;
    }
	
	/**
	 * @param url                            url
	 * @return                               datasource
	 */
	public DruidDataSource createDataSourceFromResource(String url) {
        Properties props = new Properties();
        props.put("druid.driverClassName", defaultDriver());
        props.put("druid.url", url);
        props.put("druid.username", System.getenv("jdbcUser") == null ? defaultUser() : System.getenv("jdbcUser"));
        props.put("druid.password", System.getenv("jdbcPassword") == null ? defaultPassword() : System.getenv("jdbcPassword"));
        props.put("druid.initialSize", 10);
        props.put("druid.maxActive", 100);
        props.put("druid.maxWait", 0);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(props);
        return dataSource;
    }
	
	public static DruidDataSource createDataSourceFromResource(String url, String driver, String username, String password) {
        Properties props = new Properties();
        props.put("druid.driverClassName", driver);
        props.put("druid.url", url);
        props.put("druid.username", username);
        props.put("druid.password", password);
        props.put("druid.initialSize", 10);
        props.put("druid.maxActive", 100);
        props.put("druid.maxWait", 0);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(props);
        return dataSource;
    }

	public abstract String checkDatabaseSql();
	
	public abstract String createDatabaseSql();
	
	public abstract String checkTableSql();
	
	public abstract String createTableSql();
	
	public abstract String defaultDriver();
	
	public abstract String defaultUrl();
	
	public abstract String defaultUser();
	
	public abstract String defaultPassword();
	
	public abstract String queryConditions();
	
	public abstract String queryLimit(int limit, int page);
	
	public abstract String getUrlPrefix();
	
}
