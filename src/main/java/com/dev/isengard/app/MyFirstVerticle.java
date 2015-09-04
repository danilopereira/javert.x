package com.dev.isengard.app;
import com.dev.isengard.repository.config.JDBCConfig;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
public class MyFirstVerticle extends AbstractVerticle{
	@Override
	public void start() throws Exception {
		Router router = Router.router(vertx);	

		Route route = router.route(HttpMethod.GET, "/cep/:cep");
		HttpServer server = vertx.createHttpServer();
		route.handler(rountinContext ->{
			String cep = rountinContext.request().getParam("cep");
			String cepQuery = cep.substring(0, 5);
			String cepTest = cepQuery + "-" + cep.substring(5);

			HttpServerResponse r = rountinContext.response();


			JDBCClient client = new JDBCConfig().client;

			client.getConnection(r2->{
				if(r2.succeeded()){
					SQLConnection connection = r2.result();

					connection.query("SELECT UF FROM uf WHERE '"+cepQuery+"' BETWEEN Cep1 AND Cep2", res->{
						ResultSet rs = res.result();
						JsonArray ufS = rs.getResults().get(0);
						String uf = ufS.toString().toLowerCase().substring(2, 4);
						connection.query("SELECT * FROM "+uf+" WHERE cep = '"+cepTest+"'", resFinal->{
							System.out.println(cepTest);
							ResultSet result = resFinal.result();
							r.putHeader("content-type", "application/json").end(result.toJson().toString());
						});


					});
				}
			});

		});

		server.requestHandler(router::accept).listen(8085);



	}
}
