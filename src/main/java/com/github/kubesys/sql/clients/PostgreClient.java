/*

 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.sql.clients;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.github.kubesys.sql.KubeSQLClient;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.2.0
 * @since 2020/4/23
 *
 */
public class PostgreClient extends KubeSQLClient {

	public static final String CHECK_DATABASE = "SELECT u.datname  FROM pg_catalog.pg_database u where u.datname='#DATBASE#'";

	public static final String CREATE_DATABASE = "CREATE DATABASE #DATBASE# ENCODING = 'UTF8'";

	public static final String CHECK_TABLE = "select count(*) from pg_class where relname = '#TABLE#'";

	public static final String CREATE_TABLE = "CREATE TABLE #TABLE# (name varchar(512), namespace varchar(128), time int8,"
																+ "data json, primary key(name, namespace))";

	private static final String CONDITION = " data#ITEM# like '%#VALUE#%' AND ";

	private static final String LIMIT = " LIMIT #LIMIT# OFFSET #OFFSET#";
	
	public static final String DEF_DRV = "org.postgresql.Driver";

	public static final String DEF_URL = "jdbc:postgresql://kube-database.kube-system:5432/postgres"
																+ "?useUnicode=true&characterEncoding=UTF8&connectTimeout=2000"
																+ "&socketTimeout=6000&autoReconnect=true&&serverTimezone=Asia/Shanghai";

	public static final String DEF_USER = "postgres";

	public PostgreClient(DruidPooledConnection conn) throws Exception {
		super(conn);
	}

	public PostgreClient(DruidPooledConnection conn, String database) throws Exception {
		super(conn, database);
	}

	@Override
	public String checkDatabaseSql() {
		return CHECK_DATABASE;
	}

	@Override
	public String createDatabaseSql() {
		return CREATE_DATABASE;
	}

	@Override
	public String checkTableSql() {
		return CHECK_TABLE;
	}

	@Override
	public String createTableSql() {
		return CREATE_TABLE;
	}

	@Override
	public String defaultDriver() {
		return DEF_DRV;
	}

	@Override
	public String defaultUrl() {
		return DEF_URL;
	}

	@Override
	public String defaultUser() {
		return DEF_USER;
	}

	@Override
	public String getUrlPrefix() {
		return "jdbc:postgresql://";
	}

	@Override
	public String queryConditions() {
		return CONDITION;
	}

	@Override
	public String queryLimit(int limit, int page) {
		int l = (limit <= 0) ? 10 : limit;
		int p = (page <= 1) ? 1 : page;
		return LIMIT.replace("#LIMIT#", String.valueOf(limit)).replace("#OFFSET#", String.valueOf((p - 1) * l));
	}

}
