package de.mrmysteri0us.dbmanager;

import de.mrmysteri0us.dbmanager.config.Config;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.event.SpongeEventHandler;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robin on 01/11/2014
 */

@Plugin(id = "DBManager", name = "DBManager", version = "1.1_0")
public class DBManagerPlugin {
    private static DBManagerPlugin instance;
    private File                pluginDirectory;
    private Logger              log;
    private Config              config;
    private Config              configExceptions;
    private Map<String, Config> exceptions;

    @SpongeEventHandler
    public void onInit(PreInitializationEvent event) {
        log = event.getPluginLog();
        log.info("Plugin loading.");
        instance = this;
        pluginDirectory = event.getConfigurationDirectory();
        exceptions = new HashMap<String, Config>();
        config = new Config(new File(pluginDirectory, "config.yml"));
        configExceptions = new Config(new File(pluginDirectory, "exceptions.yml"));

        try {
            config.load();
            configExceptions.load();

            for(String s : configExceptions.getMap().keySet()) {
                exceptions.put(s, new Config(new File(pluginDirectory, configExceptions.getMap().get(s))));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        log.info("Plugin enabled.");
    }

    @SpongeEventHandler
    public void onStop(ServerStoppingEvent event) {
        log.info("Plugin disabled.");
    }

    public static DBManagerPlugin getInstance() {
        return instance;
    }

    public Logger getLog() {
        return log;
    }

    public Config getConfig() {
        return config;
    }

    public Config getConfigExceptions() {
        return configExceptions;
    }

    public Map<String, Config> getExceptions() {
        return exceptions;
    }
}
