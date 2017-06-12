package net.darkhax.sasit.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    public static String[] basicFilter = {};

    public static boolean filterGeneric = true;

    public static boolean filterJava = true;

    public static boolean filterLog4J = true;

    public static boolean filterForge = true;

    public static boolean filterOthers = true;

    /**
     * An instance of the Configuration object being used.
     */
    public static Configuration config = null;

    /**
     * Initializes the configuration file.
     *
     * @param file The file to read/write config stuff to.
     */
    public static void initConfig (File file) {

        config = new Configuration(file);
        syncConfig();
    }

    /**
     * Syncs all configuration properties.
     */
    public static void syncConfig () {

        basicFilter = config.getStringList("BasicFilter", Configuration.CATEGORY_GENERAL, basicFilter, "A list of strings. If a console message contains any of these, it will be filtered out of the log.");

        final String filterCat = "FILTERS";
        filterGeneric = config.getBoolean("FilterSystem", filterCat, true, "Should messages from system out be filtered?");
        filterJava = config.getBoolean("FilterJava", filterCat, true, "Should messages from java loggers be filtered?");
        filterLog4J = config.getBoolean("FilterLog4J", filterCat, true, "Should messages from log4j loggers be filtered?");
        filterForge = config.getBoolean("FilterForge", filterCat, true, "Should messages from the forge logger be filtered?");
        filterOthers = config.getBoolean("FilterOthers", filterCat, true, "Should messages from misc loggers be filtered?");

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static boolean requiresFiltering (String message) {

        for (final String filter : basicFilter) {
            if (message.contains(filter)) {
                return true;
            }
        }

        return false;
    }
}