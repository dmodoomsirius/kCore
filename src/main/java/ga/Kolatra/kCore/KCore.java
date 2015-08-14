package ga.Kolatra.kCore;

import ga.Kolatra.kCore.Client.GUI.GUIProxy;
import ga.Kolatra.kCore.Common.Command.CommandDebug;
import ga.Kolatra.kCore.Common.Command.CommandTrash;
import ga.Kolatra.kCore.Common.Config.KCoreConfig;
import ga.Kolatra.kCore.Common.Events.PlayerEvents;
import ga.Kolatra.kCore.Common.Item.CreativeTab;
import ga.Kolatra.kCore.Common.Libraries.LogHelper;
import ga.Kolatra.kCore.Common.Libraries.Reference;

import java.io.File;
import java.util.UUID;

import cpw.mods.fml.client.GuiIngameModOptions;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class KCore extends KCoreMod
{
    public static final CreativeTabs cTab = new CreativeTab();
    public static final UUID KolatraUUID = UUID.fromString("1d5e02e0-7e54-4e9e-8d9c-548b22c02daf");
    public static boolean debugMode;
    public static boolean trashItemsOnThrow;
    public static final String EXINDEX = "extrastuff:";

    private static int modGUIIndex = 0;

    public static final int GUI_SOLAR_RF = modGUIIndex++;

    public static File CONFIG_DIR;

    private static boolean kolatra = calculateKolatrasComputer();

    @Mod.Instance(Reference.MODID)
    public static KCore INSTANCE;

    static
    {
        if (isKolatrasComputer())
        {
            LogHelper.info("Loading on Tyler's computer. Dev features enabled.");
        }
    }

    public static boolean isKolatrasComputer()
    {
        return kolatra;
    }

    private static boolean calculateKolatrasComputer() // Thanks to Reika for this boolean that is used in DragonAPI.
    {
        try
        {
            int cpus = Runtime.getRuntime().availableProcessors();
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

    public static boolean isDeobf()
    {
        return !FMLForgePlugin.RUNTIME_DEOBF;
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CONFIG_DIR = new File(event.getModConfigurationDirectory(), "kCore");

        if (!CONFIG_DIR.exists())
        {
            CONFIG_DIR.mkdirs();
        }

        KCoreConfig.init(new File(CONFIG_DIR, "kCore.cfg"));

        PlayerEvents pl = new PlayerEvents();
        MinecraftForge.EVENT_BUS.register(pl);
        FMLCommonHandler.instance().bus().register(pl);
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIProxy());
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
        event.registerServerCommand(new CommandTrash());
    }

    @EventHandler
    public void turnOffDebug(FMLServerStoppingEvent event)
    {
        if (debugMode)
        {
            debugMode = false;
            LogHelper.info("Debug mode disabled.");
        }
    }

    @SubscribeEvent
    public void onGuiEvent(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiIngameModOptions)
            event.gui = new GuiModList(new GuiIngameMenu());
    }
}