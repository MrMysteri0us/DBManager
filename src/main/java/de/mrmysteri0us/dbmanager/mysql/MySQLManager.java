/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 MrMysteri0us
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
        String url;
        String database = dbManager.getDatabase(plugin);
        String username = dbManager.getUsername(plugin);
        String password = dbManager.getPassword(plugin);
        String host = dbManager.getHost(plugin);
        int port = dbManager.getPort(plugin);

        url = "jdbc:mysql://" + host + ":" + port + "?" + database;
        return DriverManager.getConnection(url, username, password);
    }
}
