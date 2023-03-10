package pl.bdygasinski;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api/front/v1")
@DataSourceDefinition(name = "java:global/Cinema",
className = "org.postgresql.ds.PGSimpleDataSource", user = "username", password = "password",
portNumber = 5432, databaseName = "cinema")
public class App extends Application {
}
