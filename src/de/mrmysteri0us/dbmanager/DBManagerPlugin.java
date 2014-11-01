package de.mrmysteri0us.dbmanager;

import de.mrmysteri0us.dbmanager.config.Config;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.event.SpongeEventHandler;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by robin on 01/11/2014
 */

@Plugin(id = "DBManager", name = "DBManager", version = "1.0")
public class DBManagerPlugin {
    private static DBManagerPlugin instance;
    private Logger log;
    private Config config;

    @SpongeEventHandler
    public void onInit(PreInitializationEvent event) {
        instance = this;
        log = event.getPluginLog();
        log.info("Plugin enabled.");
        config = new Config(new File(event.getConfigurationDirectory(), "config.yml"));

        try {
            config.load();
        } catch(IOException e) {
            e.printStackTrace();
        }
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
}
