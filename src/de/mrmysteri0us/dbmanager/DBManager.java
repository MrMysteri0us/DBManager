package de.mrmysteri0us.dbmanager;

import de.mrmysteri0us.dbmanager.config.Config;

import java.util.Map;

/**
 * Created by robin on 01/11/2014
 */
public class DBManager {
    private DBManagerPlugin     plugin;
    private Config              config;
    private Map<String, Config> exceptions;

    public DBManager() {
        plugin = DBManagerPlugin.getInstance();
        config = plugin.getConfig();
        exceptions = plugin.getExceptions();
    }

    public String getDatabase(String pluginName) {
        if(exceptions.containsKey(pluginName)) {
            return exceptions.get(pluginName).getValue("database");
        }

        return config.getValue("database");
    }

    public String getUsername(String pluginName) {
        if(exceptions.containsKey(pluginName)) {
            return exceptions.get(pluginName).getValue("username");
        }

        return config.getValue("username");
    }

    public String getPassword(String pluginName) {
        if(exceptions.containsKey(pluginName)) {
            return exceptions.get(pluginName).getValue("password");
        }

        return config.getValue("password");
    }

    public String getHost(String pluginName) {
        if(exceptions.containsKey(pluginName)) {
            return exceptions.get(pluginName).getValue("host");
        }

        return config.getValue("host");
    }

    public int getPort(String pluginName) {
        if(exceptions.containsKey(pluginName)) {
            return exceptions.get(pluginName).getInt("port");
        }

        return config.getInt("port");
    }
}
