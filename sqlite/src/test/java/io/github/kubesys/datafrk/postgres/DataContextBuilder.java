/*

 * Copyright (2021, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk.postgres;

import java.util.Properties;

import org.postgresql.Driver;

import io.github.kubesys.datafrk.druid.DruidDataContext;

/**
 * @author wuheng@iscas.ac.cn
 * @since 2.0.0
 *
 */
public class DataContextBuilder {

	static Properties props = new Properties(); 
	
	static {
		props.put("druid.driverClassName", Driver.class.getName()); 
		props.put("druid.url", "jdbc:postgresql://39.100.71.73:30306/postgres?useUnicode=true&characterEncoding=UTF8&connectTimeout=2000&socketTimeout=6000&autoReconnect=true&&serverTimezone=Asia/Shanghai"); 
		props.put("druid.username", "postgres"); 
		props.put("druid.password", "onceas");
		props.put("druid.initialSize", 10); 
		props.put("druid.maxActive", 100);
		props.put("druid.maxWait", 0);
	}
	
	public static DruidDataContext createDataContext() throws Exception {
		return new PostgresDataContext(props);
	}
}
