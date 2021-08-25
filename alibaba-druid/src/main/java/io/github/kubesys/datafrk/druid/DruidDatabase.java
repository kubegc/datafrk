/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import io.github.kubesys.datafrk.core.Database;
import io.github.kubesys.datafrk.core.Table;
import io.github.kubesys.datafrk.core.operators.CheckTable;
import io.github.kubesys.datafrk.core.operators.CreateTable;
import io.github.kubesys.datafrk.core.operators.DropTable;
import io.github.kubesys.datafrk.druid.operators.CheckDruidTable;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public abstract class DruidDatabase implements Database {

	protected final Logger m_logger = Logger.getLogger(DruidDatabase.class.getName());
	
	protected final DruidExecutor executor;
	
	protected final Map<String, Table<?>> map = new HashMap<>();
	
	public DruidDatabase(DruidExecutor executor) {
		super();
		this.executor = executor;
	}

	@Override
	public String schema() {
		try {
			return "CREATE database " + this.executor.getConnection().getCatalog();
		} catch (SQLException e) {
			m_logger.severe(e.toString());
			return null;
		}
	}

	@Override
	public boolean createTable(CreateTable createTable) {
		return this.executor.execWithStatus(createTable.toSQL());
	}

	@Override
	public boolean checkTable(CheckTable checkTable) {
		ResultSet rs = null;
		try {
			((CheckDruidTable) checkTable).setDatabase(executor.getConnection().getCatalog());
			rs = this.executor.execWithResult(checkTable.toSQL());
			rs.next();
			return rs.getInt("num") != 0;
		} catch (Exception ex) {
			return false;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public boolean dropTable(DropTable dropTable) {
		return this.executor.execWithStatus(dropTable.toSQL());
	}

	
	@Override
	public void close() throws Exception {
		this.executor.close();
	}

	@Override
	public Table<?> get(String name) {
		this.tables();
		return map.get(name);
	}
	
	
	public abstract String listTableSQL();
	
	public abstract String listTableLabel();
	
}
