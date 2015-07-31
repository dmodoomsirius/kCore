package ga.Kolatra.kCore.Common.Libraries;

import ga.Kolatra.kCore.Common.Config.KCoreConfig;
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

    public static void logInfo(String msg)
    {
        logger.info(msg);
    }

    public static void logWarn(String msg)
    {
        logger.warn(msg);
    }

    public static void logFatal(String msg)
    {
        logger.fatal(msg);
    }

    public static void logDebug(String msg)
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
