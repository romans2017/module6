package ua.module6.projectsystem.connectors;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.connectors.dbcontrollers.MssqlController;
import ua.module6.projectsystem.connectors.dbcontrollers.PostgresController;
import ua.module6.projectsystem.services.PropertiesService;

import java.util.Objects;
import java.util.Properties;

public class ConnectorManager {

    public static DbConnector connectTo(DbType dbType) {
        Properties properties = new Properties();
        if (dbType == DbType.POSTGRES) {
            properties = PropertiesService.readProperties("postgres.properties");
        } else if (dbType == DbType.MSSQL) {
            properties = PropertiesService.readProperties("mssql.properties");
        }
        if (!PropertiesService.isValidDbProperties(properties)) {
            return null;
        }
        if (Objects.equals(properties.getProperty("db.type"), "postgres")) {
            return new PostgresController(properties);
        } else if (Objects.equals(properties.getProperty("db.type"), "mssql")) {
            return new MssqlController(properties);
        } else {
            return null;
        }
    }

}
