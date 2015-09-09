package com.dev.isengard.app;
import com.dev.isengard.repository.api.AddressRepositoryApi;
import com.dev.isengard.repository.impl.AddressRepositoryImpl;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
public class MainVerticle extends AbstractVerticle{
	
	
	@Override
	public void start() throws Exception {
		HttpServer server = vertx.createHttpServer();
		
		Router router = Router.router(vertx);	
		
		AddressRepositoryApi addressRepo = new AddressRepositoryImpl();
		
		router.get("/cep/:cep").handler(addressRepo::findByCep);
		
		router.get("/hello").handler(routinContext->{routinContext.response().putHeader("content-type", "text/plain").end("Hello World!");});

		server.requestHandler(router::accept).listen(8085);
	}
}
