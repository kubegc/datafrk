/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.kubesys.datafrk.core.DataContext;
import io.github.kubesys.datafrk.core.operators.QueryData;
import io.github.kubesys.datafrk.postgres.operators.QueryPostgresDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class QueryPostgresDataBuilderTest  {

	public static void main(String[] args) throws Exception {
		
		DataContext context = DataContextBuilder.createDataContext();
//		count(context);
		query(context);
	}

	public static void count(DataContext context) throws Exception, SQLException {
		String sql = new QueryPostgresDataBuilder()
				.selectCount("abc")
				.build().toSQL();
		
		ResultSet rs = (ResultSet) context.currentDatabase().get("abc").query(new QueryData(sql));
		
		while (rs.next()) {
			System.out.println(rs.getInt("count"));
		}
	}

	public static void query(DataContext context) throws Exception, SQLException {
		String sql = new QueryPostgresDataBuilder()
				.selectAll("abc")
				.where("name").like("abc")
				.and("data.metadata.name", true).like("abc")
				.orderBy("updated", true)
				.limit(2, 1)
				.build().toSQL();
		
		System.out.println(sql);
	}
	
}
