/*

 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.kubesys.datafrk;

import java.math.BigDecimal;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * 
 * @version 1.2.0
 * @since   2020/4/23
 *
 */
public class KubeSQLUtil {
	
	public static String toSqlSnippet (String field) {
		String[] values = field.split("\\.");
		
		if (values.length == 1) {
			return "->>'" + field + "'";
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length - 1; i++) {
			sb.append("->'").append(values[i]).append("'");
		}
		return sb.append("->>'").append(values[values.length - 1])
									.append("'").toString();
	}
	
	public static float toNum (String value) {
		float num = 0;
		if (value.endsWith("Ki")) {
			num = Float.parseFloat(value.substring(0, value.length() - 2)) / 1024 / 1024;
		} else if (value.endsWith("Mi")) {
			num = Float.parseFloat(value.substring(0, value.length() - 2)) / 1024;
		} else if (value.endsWith("Gi")) {
			num = Float.parseFloat(value.substring(0, value.length() - 2));
		} else if (value.endsWith("Ti")) {
			num = Float.parseFloat(value.substring(0, value.length() - 2)) * 1024;
		} else if (value.endsWith("Pi")) {
			num = Float.parseFloat(value.substring(0, value.length() - 2)) * 1024 * 1024;
		} else {
			num = Float.parseFloat(value);
		}

		return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
}
