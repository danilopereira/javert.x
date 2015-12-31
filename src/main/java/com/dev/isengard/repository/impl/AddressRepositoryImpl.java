package com.dev.isengard.repository.impl;

import com.dev.isengard.repository.api.AddressRepositoryApi;
import com.dev.isengard.repository.config.JDBCConfig;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;

public class AddressRepositoryImpl implements AddressRepositoryApi{

	@Override
	public void findByCep(RoutingContext routingContext) {
		JDBCClient client = JDBCConfig.client;
		final String cep = routingContext.request().getParam("cep");
		if(cep == null){
			routingContext.response().setStatusCode(400).end();
		}
		else{
			HttpServerResponse r = routingContext.response();
			client.getConnection(r2->{
				if(r2.succeeded()){
					SQLConnection connection = r2.result();
					String cepPrimary = cep.substring(0, 5);
					String cepFinal = cep.substring(0, 5) + "-" + cep.substring(5, 8);
					connection.query("SELECT UF FROM uf WHERE '"+cepPrimary+"' BETWEEN Cep1 AND Cep2", res->{
						ResultSet rs = res.result();
						JsonArray ufS = rs.getResults().get(0);
						String uf = ufS.toString().toLowerCase().substring(2, 4);
						connection.query("SELECT * FROM "+uf+" WHERE cep = '"+cepFinal+"'", resFinal->{
							ResultSet result = resFinal.result();
							r.putHeader("content-type", "application/json; charset=utf-8").end(result.getResults().toString());
						});
					});
				}
			});

		}
	}


}
