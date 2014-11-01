package de.mrmysteri0us.dbmanager;

import org.apache.logging.log4j.Logger;
import org.spongepowered.api.event.SpongeEventHandler;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * Created by robin on 01/11/2014
 */

@Plugin(id = "DBManager", name = "DBManager", version = "1.0")
public class DBManager {
    private static DBManager instance;
    private Logger log;

    @SpongeEventHandler
    public void onInit(PreInitializationEvent event) {
        instance = this;
        log = event.getPluginLog();
        log.info("Plugin enabled.");
    }

    @SpongeEventHandler
    public void onStop(ServerStoppingEvent event) {
        log.info("Plugin disabled.");
    }

    public static DBManager getInstance() {
        return instance;
    }

    public Logger getLog() {
        return log;
    }
}
