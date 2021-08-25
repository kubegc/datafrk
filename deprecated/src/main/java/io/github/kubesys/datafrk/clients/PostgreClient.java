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
public class PostgreClient extends KubeSQLClient {

	public static final String CHECK_POSTGRE_DATABASE  = "SELECT u.datname  FROM pg_catalog.pg_database u where u.datname='#DATBASE#'";

	public static final String CREATE_POSTGRE_DATABASE = "CREATE DATABASE #DATBASE# ENCODING = 'UTF8'";

	public static final String CHECK_POSTGRE_TABLE     = "select count(*) as num from pg_class where relname = '#TABLE#'";

	public static final String CREATE_POSTGRE_TABLE    = "CREATE TABLE #TABLE# (name varchar(512), namespace varchar(128), apigroup varchar(128), created timestamp, updated timestamp, "
																+ "data json, primary key(name, namespace, apigroup))";

	public static final String INSERT_POSTGRES_OBJECT  = "INSERT INTO #TABLE# VALUES ('#NAME#', '#NAMESPACE#', '#GROUP#', '#CREATED#', '#UPDATED#' , '#JSON#'::json)";
	
	public static final String UPDATE_POSTGRES_OBJECT  = "UPDATE #TABLE# SET updated = '#UPDATED#', data = '#JSON#'::json WHERE name = '#NAME#' and namespace = '#NAMESPACE#' and apigroup = '#GROUP#'";
	
	private static final String POSTGRE_CONDITION      = " data#ITEM# like '%#VALUE#%' AND ";

	private static final String POSTGRE_LIMIT          = " LIMIT #LIMIT# OFFSET #OFFSET#";
	
	public static final String DEF_POSTGRE_DRV         = "org.postgresql.Driver";

	// https://jdbc.postgresql.org/documentation/94/connect.html
	public static final String DEF_POSTGRE_URL         = "jdbc:postgresql://kube-database.kube-system:5432/postgres"
																+ "?useUnicode=true&characterEncoding=UTF8&connectTimeout=02000"
																+ "&socketTimeout=06000&autoReconnect=true&&serverTimezone=Asia/Shanghai";

	public static final String DEF_POSTGRE_USER        = "postgres";

	public PostgreClient(DruidPooledConnection conn) throws Exception {
		super(conn);
	}

	public PostgreClient(DruidPooledConnection conn, String database) throws Exception {
		super(conn, database);
	}

	@Override
	public String checkDatabaseSql() {
		return CHECK_POSTGRE_DATABASE;
	}

	@Override
	public String createDatabaseSql() {
		return CREATE_POSTGRE_DATABASE;
	}

	@Override
	public String checkTableSql() {
		return CHECK_POSTGRE_TABLE;
	}

	@Override
	public String createTableSql() {
		return CREATE_POSTGRE_TABLE;
	}

	@Override
	public String defaultDriver() {
		return DEF_POSTGRE_DRV;
	}

	@Override
	public String defaultUrl() {
		return DEF_POSTGRE_URL;
	}

	@Override
	public String defaultUser() {
		return DEF_POSTGRE_USER;
	}

	@Override
	public String getUrlPrefix() {
		return "jdbc:postgresql://";
	}

	@Override
	public String queryConditions() {
		return POSTGRE_CONDITION;
	}

	@Override
	public String queryLimit(int limit, int page) {
		int l = (limit <= 0) ? 10 : limit;
		int p = (page <= 1) ? 1 : page;
		return POSTGRE_LIMIT.replace("#LIMIT#", String.valueOf(limit)).replace("#OFFSET#", String.valueOf((p - 1) * l));
	}

	@Override
	public String insertObjectSql() {
		return INSERT_POSTGRES_OBJECT;
	}

	@Override
	public String updateObjectSql() {
		return UPDATE_POSTGRES_OBJECT;
	}

}
