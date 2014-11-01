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

    public String getUsername(String database) {
        return config.getValue(database + ".username");
    }

    public String getPassword(String database) {
        return config.getValue(database + ".password");
    }

    public String getHost(String database) {
        return config.getValue(database + ".host");
    }

    public int getPort(String database) {
        return config.getInt(database + ".port");
    }
}
