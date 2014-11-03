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
package de.mrmysteri0us.dbmanager;

import de.mrmysteri0us.dbmanager.config.Config;
import de.mrmysteri0us.dbmanager.mysql.MySQLManager;
import org.spongepowered.api.plugin.Plugin;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robin on 01/11/2014
 */
public class DBManager {
    private static DBManager        instance;
    private Config                  config;
    private Map<String, Config>     exceptionMap;
    private Map<String, Connection> connectionMap;
    private MySQLManager            mysqlManager;

    private DBManager() throws Exception {
        DBManagerPlugin plugin = DBManagerPlugin.getInstance();
        config = plugin.getConfig();
        exceptionMap = plugin.getExceptionMap();
        connectionMap = new HashMap<String, Connection>();
        mysqlManager = new MySQLManager(this);
        mysqlManager.setup();

        if(shouldUseMysql(null)) {
            connectionMap.put("default", mysqlManager.getConnection(null));
        }
    }

    public static DBManager getInstance() throws Exception {
        if(instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    public String getDatabase(Object plugin) {
        String pluginName = getPluginName(plugin);

        if(exceptionMap.containsKey(pluginName) && pluginName != null) {
            return exceptionMap.get(pluginName).getValue("database");
        }

        return config.getValue("database");
    }

    public String getUsername(Object plugin) {
        String pluginName = getPluginName(plugin);

        if(exceptionMap.containsKey(pluginName) && pluginName != null) {
            return exceptionMap.get(pluginName).getValue("username");
        }

        return config.getValue("username");
    }

    public String getPassword(Object plugin) {
        String pluginName = getPluginName(plugin);

        if(exceptionMap.containsKey(pluginName) && pluginName != null) {
            return exceptionMap.get(pluginName).getValue("password");
        }

        return config.getValue("password");
    }

    public String getHost(Object plugin) {
        String pluginName = getPluginName(plugin);

        if(exceptionMap.containsKey(pluginName) && pluginName != null) {
            return exceptionMap.get(pluginName).getValue("host");
        }

        return config.getValue("host");
    }

    public int getPort(Object plugin) {
        String pluginName = getPluginName(plugin);

        if (exceptionMap.containsKey(pluginName) && pluginName != null) {
            return exceptionMap.get(pluginName).getInt("port");
        }

        return config.getInt("port");
    }

    public Connection getConnection(Object plugin) throws Exception {
        if(!shouldUseMysql(plugin)) {
            Exception up = new Exception("Plugin tries to access .getConnection(), but is not configured to use MySQL.");
            throw up;
        }

        String pluginName = getPluginName(plugin);

        if(exceptionMap.containsKey(pluginName)) {
            if(!connectionMap.containsKey(pluginName)) {
                connectionMap.put(pluginName, mysqlManager.getConnection(plugin));
            }

            return connectionMap.get(pluginName);
        }

        return connectionMap.get("default");
    }

    public boolean shouldUseMysql(Object plugin) {
        String pluginName = getPluginName(plugin);

        if (exceptionMap.containsKey(pluginName) && pluginName != null) {
            return exceptionMap.get(pluginName).getBoolean("mysql");
        }

        return config.getBoolean("mysql");
    }

    private String getPluginName(Object plugin) {
        if(plugin == null) {
            return null;
        }

        Class pluginClass = plugin.getClass();

        if(pluginClass.isAnnotationPresent(Plugin.class)) {
            Plugin annotation = (Plugin) pluginClass.getAnnotation(Plugin.class);
            return annotation.name();
        }

        return null;
    }
}
