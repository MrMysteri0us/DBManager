package de.mrmysteri0us.dbmanager.mysql;

import de.mrmysteri0us.dbmanager.DBManager;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by robin on 03/11/2014
 */
public class MySQLManager {
    private DBManager dbManager;

    public MySQLManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void setup() throws Exception {
        Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        DriverManager.registerDriver(driver);
    }

    public Connection getConnection(Object plugin) throws SQLException {
        String url = "";
        String database = dbManager.getDatabase(plugin);
        String username = dbManager.getUsername(plugin);
        String password = dbManager.getPassword(plugin);
        String host = dbManager.getHost(plugin);
        int port = dbManager.getPort(plugin);

        url = "jdbc:mysql://" + host + ":" + port + "?" + database;
        return DriverManager.getConnection(url, username, password);
    }
}
