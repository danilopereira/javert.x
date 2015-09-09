package com.dev.isengard.repository.config;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

public class JDBCConfig{
	
	public static final JsonObject config = new JsonObject()
			.put("url", "jdbc:mysql://localhost:3306/cep")
			.put("user", "root")
			.put("password", "")
			.put("driver_class", "com.mysql.jdbc.Driver")
			.put("max_pool_size", 30);
	
	public static final JDBCClient client = JDBCClient.createShared(Vertx.vertx(), config);	

}
