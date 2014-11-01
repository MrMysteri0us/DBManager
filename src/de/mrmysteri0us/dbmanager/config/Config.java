package de.mrmysteri0us.dbmanager.config;

import de.mrmysteri0us.dbmanager.DBManager;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robin on 01/11/2014
 */
public class Config {
    private DBManager           plugin;
    private File                configFile;
    private Yaml                configYaml;
    private Map<String, String> configMap;

    public Config(File configFile) {
        plugin = DBManager.getInstance();
        this.configFile = configFile;
        this.configYaml = new Yaml();

        if(!configFile.exists()) {
            plugin.getLog().info("Could not find config. Creating file.");

            try {
                // noinspection ResultOfMethodCallIgnored
                configFile.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() throws IOException {
        FileReader reader = new FileReader(configFile);
        Object data = configYaml.load(reader);
        reader.close();

        if(data instanceof HashMap) {
            configMap = (HashMap<String, String>) data;
        } else {
            plugin.getLog().error("Could not load config as a HashMap!");
        }
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(configFile);
        configYaml.dump(configMap, writer);
        writer.close();
    }

    public byte getByte(String key) {
        return Byte.parseByte(getValue(key));
    }

    public short getShort(String key) {
        return Short.parseShort(getValue(key));
    }

    public int getInt(String key) {
        return Integer.parseInt(getValue(key));
    }

    public long getLong(String key) {
        return Long.parseLong(getValue(key));
    }

    public float getFloat(String key) {
        return Float.parseFloat(getValue(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(getValue(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getValue(key));
    }

    public char getChar(String key) {
        return getValue(key).charAt(0);
    }

    public String getValue(String key) {
        return configMap.get(key);
    }

    public void setValue(String key, String value) {
        if(configMap.containsKey(key)) {
            configMap.remove(key);
        }

        configMap.put(key, value);
    }

    public Map<String, String> getMap() {
        return configMap;
    }
}
