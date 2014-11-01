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
