package net.darkhax.sasit.filter;

import java.util.logging.LogRecord;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;

import net.darkhax.sasit.handler.ConfigurationHandler;

public class FilterSASIT extends AbstractFilter implements java.util.logging.Filter {

    // Oracle/Java Filter
    @Override
    public boolean isLoggable (LogRecord record) {

        return !ConfigurationHandler.requiresFiltering(record.getMessage());
    }

    // Apache/Log4J Filter
    @Override
    public Result filter (LogEvent event) {

        return ConfigurationHandler.requiresFiltering("[" + event.getLoggerName() + "]: " + event.getMessage().getFormattedMessage()) ? Result.DENY : Result.NEUTRAL;
    }
}