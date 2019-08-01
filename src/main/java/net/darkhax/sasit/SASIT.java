package net.darkhax.sasit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.sasit.io.PrintStreamFilterable;
import net.darkhax.sasit.libs.LoggerUtils;
import net.minecraftforge.fml.common.Mod;

@Mod("sasit")
public class SASIT {

    public static final Logger LOG = LogManager.getLogger("Stuff A Sock In It");
    public static final Configuration CONFIG = new Configuration();

    public SASIT () {

        if (!CONFIG.getBasicFilters().isEmpty()) {

            LOG.info("Logs containing the following text may have been removed!");

            for (final String entry : CONFIG.getBasicFilters()) {

                LOG.info(entry);
            }
        }

        if (!CONFIG.getRegexFilters().isEmpty()) {

            LOG.info("Logs matching the following REGEX patterns may have been removed!");

            for (final String entry : CONFIG.getRegexFilters()) {

                LOG.info(entry);
            }
        }

        // Java system out
        if (CONFIG.shouldFilterJavaOut()) {

            System.setOut(new PrintStreamFilterable(System.out));
        }

        // Java logger root
        if (CONFIG.shouldFilterJavaLogger()) {

            java.util.logging.Logger.getLogger("").setFilter(LoggerUtils.FILTER);
        }

        // Root Apache/Log4J logger
        if (CONFIG.shouldFilterLog4J()) {
            ((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger()).addFilter(LoggerUtils.FILTER);
        }

        // Other stray Log4J loggers.
        if (CONFIG.shouldFilterLog4JMisc()) {
            LoggerUtils.filterOtherLog4JLoggers();
        }
    }
}