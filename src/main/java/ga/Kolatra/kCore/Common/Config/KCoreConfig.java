package ga.Kolatra.kCore.Common.Config;

import ga.Kolatra.kCore.Common.Libraries.Reference;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.common.config.Configuration;

public class KCoreConfig
{
    public static Configuration configuration;

    public static boolean logDebug;

    public static void init(File file)
    {
        if (configuration == null)
        {
            configuration = new Configuration(file);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        logDebug = configuration.getBoolean("debugLogging", "misc", true, "Enable debug logging.");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MODID))
        {
            loadConfiguration();
        }
    }
}
