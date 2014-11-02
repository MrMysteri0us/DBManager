/*
The MIT License (MIT)

Copyright (c) 2014 MrMysteri0us

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

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
    private Logger              log;
    private Config              config;
    private Config              configExceptions;
    private Map<String, Config> exceptions;

    @SpongeEventHandler
    public void onInit(PreInitializationEvent event) {
        log = event.getPluginLog();
        log.info("Plugin loading.");
        instance = this;
        exceptions = new HashMap<String, Config>();
        File pluginDirectory = event.getConfigurationDirectory();
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
