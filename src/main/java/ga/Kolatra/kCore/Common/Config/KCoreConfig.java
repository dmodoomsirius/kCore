package ga.kolatra.kcore.common.config;

import ga.kolatra.kcore.common.libraries.LogHelper;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class KCoreConfig
{
    public static boolean logDebug;

    public static void init(File configFile)
    {
        Configuration configuration = new Configuration(configFile);

        try
        {
            configuration.load();

            logDebug = configuration.getBoolean("debugLogging", "misc", true, "Enable debug logging.");
        }
        catch (Exception e)
        {
            LogHelper.fatal("Caught exception while loading Config!");
            e.printStackTrace();
        }
        finally
        {
            if (configuration.hasChanged())
            {
                configuration.save();
            }
        }
    }
}
