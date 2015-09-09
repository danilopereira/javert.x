package com.dev.isengard.repository.api;

import io.vertx.ext.web.RoutingContext;

public interface AddressRepositoryApi {
	
	void findByCep(RoutingContext routingContext);

}
