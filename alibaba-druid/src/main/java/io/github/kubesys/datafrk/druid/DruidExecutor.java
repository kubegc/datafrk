/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.druid;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.alibaba.druid.pool.DruidPooledConnection;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DruidExecutor {

	protected final Logger m_logger = Logger.getLogger(DruidExecutor.class.getName());
	
	protected final DruidPooledConnection conn;

	public DruidExecutor(DruidPooledConnection conn) {
		super();
		this.conn = conn;
	}

	public DruidPooledConnection getConnection() {
		return conn;
	}

	public boolean execWithStatus(String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			return true;
		} catch (SQLException ex) {
			m_logger.severe(ex.toString());
			return false;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ResultSet execWithResult(String sql) {
		PreparedStatement pstmt = null;
		try {
			return conn.prepareStatement(sql).executeQuery();
		} catch (SQLException ex) {
			m_logger.severe(ex.toString());
			return null;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<String> execWithValue(String sql, int id) {
		List<String> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				list.add(rs.getString(id));
			}
		} catch (SQLException ex) {
			m_logger.severe(ex.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<String> execWithValue(String sql, String label) {
		List<String> list = new ArrayList<>();
	PreparedStatement pstmt = null;
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				list.add(rs.getString(label));
			}
		} catch (SQLException ex) {
			m_logger.severe(ex.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public void close() throws Exception {
		this.conn.close();
	}
}
