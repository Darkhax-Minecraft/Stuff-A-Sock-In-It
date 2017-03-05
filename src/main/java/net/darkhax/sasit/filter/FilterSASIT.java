package net.darkhax.sasit.filter;

import java.util.logging.LogRecord;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import net.darkhax.sasit.handler.ConfigurationHandler;

public class FilterSASIT implements org.apache.logging.log4j.core.Filter, java.util.logging.Filter {

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

    @Override
    public Filter.Result filter (Logger logger, Level level, Marker marker, Object object, Throwable t) {

        return Result.NEUTRAL;
    }

    @Override
    public Filter.Result filter (Logger logger, Level level, Marker marker, Message message, Throwable t) {

        return Result.NEUTRAL;
    }

    @Override
    public Filter.Result getOnMatch () {

        return Result.NEUTRAL;
    }

    @Override
    public Filter.Result getOnMismatch () {

        return Result.NEUTRAL;
    }

    @Override
    public Result filter (Logger logger, Level level, Marker marker, String msg, Object... params) {

        return Result.NEUTRAL;
    }
}