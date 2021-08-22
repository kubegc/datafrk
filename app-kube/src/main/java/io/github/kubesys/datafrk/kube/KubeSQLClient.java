/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.kube;

import io.github.kubesys.datafrk.druid.DruidDataContext;
import io.github.kubesys.datafrk.postgres.operators.InsertPostgresDataBuilder;
import io.github.kubesys.datafrk.postgres.operators.RemovePostgresDataBuilder;
import io.github.kubesys.datafrk.postgres.operators.UpdatePostgresDataBuilder;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class KubeSQLClient {

	public static final String LABEL_NAME      = "name";
	
	public static final String LABEL_NAMESPACE = "namespace";
	
	public static final String LABEL_APIGROUP  = "apigroup";
	
	public static final String LABEL_CREATED   = "created";
	
	public static final String LABEL_UPDATED   = "updated";
	
	public static final String LABEL_DATA      = "data";
	
	protected final DruidDataContext context;
	
	public KubeSQLClient(DruidDataContext context) {
		super();
		this.context = context;
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
	 * @param group                                  group
	 * @param created                                created
	 * @param updated                                updated
	 * @param json                                   json
	 * @return                                       true or false
	 * @throws Exception                             exception
	 */
	public boolean insertObject(String table, String name, String namespace, String group, String created, String updated, String json) throws Exception {
		if (!context.currentDatabase().get(table).insert(
				new InsertPostgresDataBuilder()
				.insertTo(table)
				.beginValue(name)
				.andValue(namespace)
				.andValue(group)
				.andValue(created)
				.andValue(updated)
				.endValue(json, true)
				.build())) {
			return updateObject(table, name, namespace, group, updated, json);
		}
		return true;
	}
	
	/**
	 * @param table                                  table
	 * @param name                                   name
	 * @param namespace                              namespace
	 * @param group                                  group
	 * @param updated                                updated
	 * @param json                                   json
	 * @return                                       true or false
	 * @throws Exception                             exception
	 */
	public boolean updateObject(String table, String name, String namespace, String group, String updated, String json) throws Exception {
		return context.currentDatabase().get(table).update(
				new UpdatePostgresDataBuilder()
				.update(table)
				.set(LABEL_UPDATED).eq(updated)
				.andSet(LABEL_DATA).eq(json, true)
				.where(LABEL_NAME).eq(name)
				.and(LABEL_NAMESPACE).eq(namespace)
				.and(LABEL_APIGROUP).eq(group)
				.build());
	}
	
	/**
	 * @param table                                  table
	 * @param name                                   name
	 * @param namespace                              namespace
	 * @param group                                  group
	 * @param json                                   json
	 * @return                                       true or false
	 * @throws Exception                             exception
	 */
	public boolean deleteObject(String table, String name, String namespace, String group, String json) throws Exception {
		return context.currentDatabase().get(table).delete(
				new RemovePostgresDataBuilder()
				.delete(table)
				.where(LABEL_NAME).eq(name)
				.and(LABEL_NAMESPACE).eq(namespace)
				.and(LABEL_APIGROUP).eq(group)
				.build());
	}
}
