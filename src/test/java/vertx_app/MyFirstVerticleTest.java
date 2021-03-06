package vertx_app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dev.isengard.app.MainVerticle;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class MyFirstVerticleTest {
	
	private Vertx vertx;
	
	@Before
	public void setup(TestContext context){
		vertx = Vertx.vertx();
		
		vertx.deployVerticle(MainVerticle.class.getName(), context.asyncAssertSuccess());
		
	}
	
	@After
	public void tearDown(TestContext context){
		vertx.close(context.asyncAssertSuccess());
	}
	
	@Test
	public void testStart(TestContext context){
		final Async async = context.async();
		
		vertx.createHttpClient().getNow(8085, "localhost", "/",
			     response -> {
			      response.handler(body -> {
			        context.assertTrue(body.toString().contains("Hello"));
			        async.complete();
			      });
			    });
	}

}
