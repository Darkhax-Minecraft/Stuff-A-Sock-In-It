package net.darkhax.sasit.libs;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

import net.darkhax.sasit.SASIT;
import net.darkhax.sasit.filter.FilterSASIT;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class LoggerUtils {

    public static final FilterSASIT FILTER = new FilterSASIT();

    private static final List<LoggerConfig> FOUND_LOG4J_LOGGERS = new ArrayList<>();

    public static void filterOtherLog4JLoggers () {

        final LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
        final Map<String, LoggerConfig> map = logContext.getConfiguration().getLoggers();

        for (final LoggerConfig logger : map.values()) {

            if (!FOUND_LOG4J_LOGGERS.contains(logger)) {

                logger.addFilter(FILTER);
                FOUND_LOG4J_LOGGERS.add(logger);
            }
        }
    }

    public static void filterForgeLogger () {

        try {

            final Field fieldLog = ReflectionHelper.findField(FMLLog.class, "log");
            
            final Logger log = (Logger) fieldLog.get(null);
            
            if (log instanceof org.apache.logging.log4j.core.Logger) {
            	
            	((org.apache.logging.log4j.core.Logger) log).addFilter(FILTER);
            }
        }

        catch (RuntimeException | IllegalAccessException e) {

            SASIT.LOG.catching(e);
        }
    }
}
