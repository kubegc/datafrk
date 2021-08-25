/*

 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.clients;

import com.alibaba.druid.pool.DruidPooledConnection;

import io.github.kubesys.datafrk.KubeSQLClient;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.2.0
 * @since 2020/4/23
 *
 */
public class MysqlClient extends KubeSQLClient {

	public static final String CHECK_MYSQL_DATABASE  = "SELECT * FROM information_schema.SCHEMATA where SCHEMA_NAME='#DATBASE#'";
	
	public static final String CREATE_MYSQL_DATABASE = "CREATE DATABASE #DATBASE# CHARACTER SET utf8 COLLATE utf8_general_ci";
	
	public static final String CHECK_MYSQL_TABLE     = "SELECT DISTINCT t.table_name, n.SCHEMA_NAME FROM "
												+ "information_schema.TABLES t, information_schema.SCHEMATA n "
												+ "WHERE t.table_name = '#TABLE#' AND n.SCHEMA_NAME = '#DATBASE#'";
	
	public static final String CREATE_MYSQL_TABLE    = "CREATE TABLE #TABLE# (name varchar(512), namespace varchar(128), apigroup varchar(128), created datetime, updated datetime, "
												+ "data json, primary key(name, namespace, apigroup)) DEFAULT CHARSET=utf8";
	
	
	public static final String INSERT_MYSQL_OBJECT   = "INSERT INTO #TABLE# VALUES ('#NAME#', '#NAMESPACE#', '#GROUP#', '#CREATED#', '#UPDATED#' , '#JSON#')";
	
	public static final String UPDATE_MYSQL_OBJECT   = "UPDATE #TABLE# SET updated = '#UPDATED#', data = '#JSON#' WHERE name = '#NAME#' and namespace = '#NAMESPACE#' and apigroup = '#GROUP#'";
	
	private static final String MYSQL_CONDITION      = " JSON_EXTRACT(data, '$.#ITEM#') like '%#VALUE#%' AND ";
	
	private static final String MYSQL_LIMIT          = " LIMIT #FROM#, #TO#";
	
	public static final String DEF_MYSQL_DRV         = "com.mysql.cj.jdbc.Driver";

	public static final String DEF_MYSQL_URL         = "jdbc:mysql://kube-database.kube-system:3306?useUnicode=true&characterEncoding=UTF8&connectTimeout=2000&socketTimeout=6000&autoReconnect=true&&serverTimezone=Asia/Shanghai";

	public static final String DEF_MYSQL_USER        = "root";

	public MysqlClient(DruidPooledConnection conn) throws Exception {
		super(conn);
	}

	public MysqlClient(DruidPooledConnection conn, String database) throws Exception {
		super(conn, database);
	}

	@Override
	public String checkDatabaseSql() {
		return CHECK_MYSQL_DATABASE;
	}

	@Override
	public String createDatabaseSql() {
		return CREATE_MYSQL_DATABASE;
	}

	@Override
	public String checkTableSql() {
		return CHECK_MYSQL_TABLE;
	}

	@Override
	public String createTableSql() {
		return CREATE_MYSQL_TABLE;
	}

	@Override
	public String defaultDriver() {
		return DEF_MYSQL_DRV;
	}

	@Override
	public String defaultUrl() {
		return DEF_MYSQL_URL;
	}

	@Override
	public String defaultUser() {
		return DEF_MYSQL_USER;
	}

	@Override
	public String queryConditions() {
		return MYSQL_CONDITION;
	}

	
	@Override
	public String getUrlPrefix() {
		return "jdbc:mysql://";
	}

	@Override
	public String queryLimit(int limit, int page) {
		int l = (limit <= 0) ? 10 : limit;
		int p = (page <= 1) ? 1 : page;
		return MYSQL_LIMIT.replace("#FROM#", String.valueOf((p - 1) * l)).replace("#TO#", String.valueOf(l));
	}

	@Override
	public String insertObjectSql() {
		return INSERT_MYSQL_OBJECT;
	}

	@Override
	public String updateObjectSql() {
		return UPDATE_MYSQL_OBJECT;
	}


}
