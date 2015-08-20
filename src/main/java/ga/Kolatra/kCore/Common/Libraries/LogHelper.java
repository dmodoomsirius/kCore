package ga.kolatra.kcore.common.libraries;

import ga.kolatra.kcore.common.config.KCoreConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogHelper
{
    private static Logger logger = LogManager.getLogger(Reference.MODID);

    public static void log(Level level, String msg)
    {
        logger.log(level, msg);
    }

    public static void info(String msg)
    {
        logger.info(msg);
    }

    public static void warn(String msg)
    {
        logger.warn(msg);
    }

    public static void fatal(String msg)
    {
        logger.fatal(msg);
    }

    public static void debug(String msg)
    {
        if (KCoreConfig.logDebug)
        {
            logger.info(msg);
        }
    }

    public static void logCatch(Level lvl, Throwable throwable)
    {
        logger.catching(lvl, throwable);
    }
}
