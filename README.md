# javert.x
How to make your First Java Project on Vert.x

This is my First Vert.x Project using Java.

The unique requirement is Java 8 SDK

Baby-Steps

1. Create a Maven Project (skip archtype)
2. Configue pom.xml
  ```xml
  <dependencies>
  		<dependency>
  			<groupId>io.vertx</groupId>
  		<artifactId>vertx-core</artifactId>
  			<version>3.0.0</version>
  	</dependency>
  </dependencies>
  
  <build>
  		<plugins>
  			<plugin>
  				<artifactId>maven-compiler-plugin</artifactId>
  			<version>3.3</version>
  				<configuration>
  					<source>1.8</source>
  					<target>1.8</target>
  			</configuration>
  			</plugin>	
  	</plugins>
  </build>
  ```
3. In the Teminal:
```
  mvn eclipse:eclipse
  mvn clean install
```
4. Create your class and extends of <b>AbstractVerticle</b> and override the start() method
  ```java
  package com.dev.isengard.app;
  import io.vertx.core.AbstractVerticle;
  import io.vertx.core.Future;
  public class MyFirstVerticle extends AbstractVerticle{
	  @Override
	  public void start(Future<Void> fut) throws Exception {
		  vertx
		  .createHttpServer()
		  .requestHandler(r -> {
			  r.response().end("<h1>Hello from my first Vert.x 3 application</h1>");
		  })
		  .listen(8085, result ->{
			  if (result.succeeded()){
				  fut.complete();
			  }
			  else fut.fail(result.cause());
		  });
	  }
	}

  ```
5. Lets to the terminal again and you will insert this command:
```
  mvn clean compile
```
####Lets Packing
1. add this plugin in pom.xml, inside the <plugins> tag
```xml
  <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>2.3</version>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>shade</goal>
						</goals>
						<configuration>
							<transformers>
								<transformer
									implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
									<manifestEntries>
										<Main-Class>io.vertx.core.Starter</Main-Class>
										<Main-Verticle>com.dev.isengard.app.MyFirstVerticle</Main-Verticle>
									</manifestEntries>
								</transformer>
							</transformers>
							<artifactSet />
							<outputFile>${project.build.directory}/${project.artifactId}-${project.version}-fat.jar</outputFile>
						</configuration>
					</execution>
				</executions>
			</plugin>
  ```
2. In Terminal, insert the comand:
  ```
  mvn clean package
  ```
  This comand will generate the fat.jar file
  
3. run your project
  ```
    java -jar target/vertx_app-0.0.1-SNAPSHOT-fat.jar
  ```
  After success, your project will be running at localhost:8085
