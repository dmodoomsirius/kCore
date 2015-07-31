package ga.Kolatra.kCore;

import ga.Kolatra.kCore.Common.Command.CommandDebug;
import ga.Kolatra.kCore.Common.Config.KCoreConfig;
import ga.Kolatra.kCore.Common.Events.PlayerEvents;
import ga.Kolatra.kCore.Common.Item.CreativeTab;
import ga.Kolatra.kCore.Common.Libraries.LogHelper;
import ga.Kolatra.kCore.Common.Libraries.Reference;

import java.io.File;
import java.util.UUID;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class KCore extends KCoreMod
{
    public static final CreativeTabs cTab = new CreativeTab();

    private static boolean kolatra = calculateKolatrasComputer();

    public static boolean debugMode;

    public static File CONFIG_DIR;

    public static final UUID KolatraUUID = UUID.fromString("1d5e02e0-7e54-4e9e-8d9c-548b22c02daf");

    public static boolean isKolatrasComputer()
    {
        return kolatra;
    }

    private static boolean calculateKolatrasComputer() // Thanks to Reika for this boolean that is used in DragonAPI.
    {
        try
        {
            int cpus = Runtime.getRuntime().availableProcessors();
            long diskSize = new File("c:").getTotalSpace();
            String username = System.getProperty("user.name");
            if (cpus == 8 && username.equals("Tyler"))
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    {
        if (isKolatrasComputer())
        {
            LogHelper.logInfo("Loading on Tyler's computer. Dev features enabled.");
        }
    }

    public static boolean isDeobf()
    {
        return !FMLForgePlugin.RUNTIME_DEOBF;
    }

    @Mod.Instance(Reference.MODID)
    public static KCore INSTANCE;

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CONFIG_DIR = new File(event.getModConfigurationDirectory(), "RagingLib");

        if (!CONFIG_DIR.exists())
        {
            CONFIG_DIR.mkdirs();
        }

        KCoreConfig.init(new File(CONFIG_DIR, "RagingLib.cfg"));

        PlayerEvents pl = new PlayerEvents();
        MinecraftForge.EVENT_BUS.register(pl);
        FMLCommonHandler.instance().bus().register(pl);
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @EventHandler
    public void registerCommands(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDebug());
    }

    @EventHandler
    public void turnOffDebug(FMLServerStoppingEvent event)
    {
        if (debugMode)
        {
            debugMode = false;
            LogHelper.logInfo("Debug mode disabled.");
        }
    }
}