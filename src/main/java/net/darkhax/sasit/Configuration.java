package net.darkhax.sasit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.toml.TomlWriter;

import net.minecraftforge.fml.loading.LogMarkers;

public class Configuration {

    private static final String configPath = "config/stuff-a-sock-in-it.toml";
    private static final String keyFilterJavaOut = "general.filterJavaOut";
    private static final String keyFilterJavaLogger = "general.filterJavaLogger";
    private static final String keyFilterLog4JRoot = "general.filterLog4JRoot";
    private static final String keyFilterLog4JMisc = "general.filterLog4JMisc";
    private static final String keyFiltersBasic = "filters.basic";
    private static final String keyFiltersRegex = "filters.regex";
    
    private final ConfigSpec configSpec = new ConfigSpec();
    private final CommentedConfig defaultConfig = CommentedConfig.inMemory();
    private final CommentedFileConfig configData;

    public Configuration () {
        
        this.configSpec.define(keyFilterJavaOut, true);
        this.configSpec.define(keyFilterJavaLogger, true);
        this.configSpec.define(keyFilterLog4JRoot, true);
        this.configSpec.define(keyFilterLog4JMisc, true);
        this.configSpec.define(keyFiltersBasic, new ArrayList<>());
        this.configSpec.define(keyFiltersRegex, new ArrayList<>());
        
        defaultConfig.set(keyFilterJavaOut, true);
        defaultConfig.setComment(keyFilterJavaOut, "Should Java's system output stream be filtered?");
        
        defaultConfig.set(keyFilterJavaLogger, true);
        defaultConfig.setComment(keyFilterJavaLogger, "Should Java loggers be filtered?");
        
        defaultConfig.set(keyFilterLog4JRoot, true);
        defaultConfig.setComment(keyFilterLog4JRoot, "Should the Log4J root logger be filtered?");
        
        defaultConfig.set(keyFilterLog4JMisc, true);
        defaultConfig.setComment(keyFilterLog4JMisc, "Should misc Log4J loggers be filtered?");
        
        defaultConfig.set(keyFiltersBasic, new ArrayList<>());
        defaultConfig.setComment(keyFiltersBasic, "A list of basic filters. Messages will be filtered if they contain any basic filter as a substring.");
        
        defaultConfig.set(keyFiltersRegex, new ArrayList<>());
        defaultConfig.setComment(keyFiltersRegex, "A list of regex filters. Messages will be filtered if they match any of the defined regex patterns.");
        
        File configFile = new File(configPath);
        
        if (!configFile.exists()) {
            
            TomlWriter writer = new TomlWriter();
            writer.write(defaultConfig, configFile, WritingMode.REPLACE);
        }
        
        this.configData = CommentedFileConfig.builder(configPath).sync().autosave().autoreload().writingMode(WritingMode.REPLACE).build();
        this.configData.load();

        if (!this.configSpec.isCorrect(this.configData)) {

            SASIT.LOG.warn(LogMarkers.CORE, "Configuration file {} is not correct. Attempting to correct it.", configPath);
            this.configSpec.correct(this.configData, (action, path, incorrectValue, correctedValue) -> SASIT.LOG.warn(LogMarkers.CORE, "Incorrect key {} was corrected from {} to {}", path, incorrectValue, correctedValue));
        }

        this.configData.save();
    }

    public boolean shouldFilterJavaOut () {

        return this.configData.get(keyFilterJavaOut);
    }

    public boolean shouldFilterJavaLogger () {

        return this.configData.get(keyFilterJavaLogger);
    }

    public boolean shouldFilterLog4J () {

        return this.configData.get(keyFilterLog4JRoot);
    }

    public boolean shouldFilterLog4JMisc () {

        return this.configData.get(keyFilterLog4JMisc);
    }

    public List<? extends String> getBasicFilters () {

        return this.configData.get(keyFiltersBasic);
    }

    public List<? extends String> getRegexFilters () {

        return this.configData.get(keyFiltersRegex);
    }

    public boolean shouldFilterMessage (String message) {

        for (final String filter : this.getBasicFilters()) {
            if (message.contains(filter)) {
                return true;
            }
        }

        for (final String filter : this.getRegexFilters()) {
            if (message.matches(filter)) {
                return true;
            }
        }

        return false;
    }
}