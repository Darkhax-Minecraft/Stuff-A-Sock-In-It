package net.darkhax.sasit;

import java.util.ArrayList;
import java.util.List;

import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.fml.loading.LogMarkers;

public class Configuration {

    private final ConfigSpec configSpec = new ConfigSpec();
    private final CommentedFileConfig configData;

    public Configuration () {

        this.configSpec.define("general.filterJavaOut", true);
        this.configSpec.define("general.filterJavaLogger", true);
        this.configSpec.define("general.filterLog4JRoot", true);
        this.configSpec.define("general.filterLog4JMisc", true);

        this.configSpec.define("filters.basic", new ArrayList<>());
        this.configSpec.define("filters.regex", new ArrayList<>());

        this.configData = CommentedFileConfig.builder("config/sasit.toml").sync().defaultResource("/META-INF/defaultconfig.toml").autosave().autoreload().writingMode(WritingMode.REPLACE).build();
        this.configData.load();

        if (!this.configSpec.isCorrect(this.configData)) {

            SASIT.LOG.warn(LogMarkers.CORE, "Configuration file config/sasit.toml is not correct. Attempting to correct it.");
            this.configSpec.correct(this.configData, (action, path, incorrectValue, correctedValue) -> SASIT.LOG.warn(LogMarkers.CORE, "Incorrect key {} was corrected from {} to {}", path, incorrectValue, correctedValue));
        }

        this.configData.save();
    }

    public boolean shouldFilterJavaOut () {

        return this.configData.get("general.filterJavaOut");
    }

    public boolean shouldFilterJavaLogger () {

        return this.configData.get("general.filterJavaLogger");
    }

    public boolean shouldFilterLog4J () {

        return this.configData.get("general.filterLog4JRoot");
    }

    public boolean shouldFilterLog4JMisc () {

        return this.configData.get("general.filterLog4JMisc");
    }

    public List<? extends String> getBasicFilters () {

        return this.configData.get("filters.basic");
    }

    public List<? extends String> getRegexFilters () {

        return this.configData.get("filters.regex");
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