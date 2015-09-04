package com.dev.isengard.repository.config;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

public class JDBCConfig{
	
	JsonObject config = new JsonObject();
	
	JDBCClient client = JDBCClient.createShared(Vertx.vertx(), config);	
	
	

}
