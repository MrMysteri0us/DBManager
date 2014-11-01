package de.mrmysteri0us.dbmanager;

import de.mrmysteri0us.dbmanager.config.Config;

/**
 * Created by robin on 01/11/2014
 * TODO -> TESTING REQUIRED!
 */
public class DBManager {
    private DBManagerPlugin plugin;
    private Config          config;

    public DBManager() {
        plugin = DBManagerPlugin.getInstance();
        config = plugin.getConfig();
    }

    public String getDatabase() {
        return config.getValue("database");
    }

    public String getUsername() {
        return config.getValue("username");
    }

    public String getPassword() {
        return config.getValue("password");
    }

    public String getHost(String pluginName) {
        return config.getValue("host");
    }

    public int getPort(String pluginName) {
        return config.getInt("port");
    }
}
