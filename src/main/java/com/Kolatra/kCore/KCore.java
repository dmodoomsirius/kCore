package com.Kolatra.kCore;

import com.Kolatra.kCore.Client.GUI.GUIProxy;
import com.Kolatra.kCore.Common.Command.CommandDebug;
import com.Kolatra.kCore.Common.Command.CommandTrash;
import com.Kolatra.kCore.Common.Compatibility.ModList;
import com.Kolatra.kCore.Common.Config.KCoreConfig;
import com.Kolatra.kCore.Common.Events.ClientEventHandler;
import com.Kolatra.kCore.Common.Events.MainEvents;
import com.Kolatra.kCore.Common.Events.PlayerEvents;
import com.Kolatra.kCore.Common.Item.CreativeTab;
import com.Kolatra.kCore.Common.Item.ModItems;
import com.Kolatra.kCore.Common.Libraries.LogHelper;
import com.Kolatra.kCore.Common.Libraries.Reference;

import java.io.File;
import java.util.UUID;

import cpw.mods.fml.client.GuiIngameModOptions;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class KCore implements IModKCore
{
    public static final CreativeTabs    cTab = new CreativeTab();
    public static final UUID            KolatraUUID = UUID.fromString("1d5e02e0-7e54-4e9e-8d9c-548b22c02daf");
    public static boolean               debugMode;
    public static boolean               trashItemsOnThrow;
    public static File                  CONFIG_DIR;

    @Instance(Reference.MODID)
    public static KCore INSTANCE;

    static
    {
        if (isKolatrasComputer())
        {
            LogHelper.info("Loading on Tyler's computer.");
        }
    }

    static
    {
        LogHelper.fatal("s( ?° ?? ?°)z");
    }

    public static boolean isKolatrasComputer()
    {
        return calculateKolatrasComputer();
    }

    private static boolean calculateKolatrasComputer()
    {
        try
        {
            long diskSize = new File("c:").getTotalSpace();
            int cpus = Runtime.getRuntime().availableProcessors();
            String username = System.getProperty("user.name");
            if (cpus == 8 && username.equals("Tyler") && diskSize == 249584152576L)
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

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CONFIG_DIR = new File(event.getModConfigurationDirectory(), "kCore");

        if (!CONFIG_DIR.exists())
        {
            CONFIG_DIR.mkdirs();
        }

        KCoreConfig.init(new File(CONFIG_DIR, "kCore.cfg"));

        ModItems.register();

        PlayerEvents pl = new PlayerEvents();
        MainEvents mainEvents = new MainEvents();
        ClientEventHandler client = new ClientEventHandler();
        registerEvents(pl);
        registerEvents(mainEvents);
        registerEvents(client);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIProxy());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        if (ModList.VE.isLoaded())
        {
            LogHelper.info("VoltzEngine has been detected in this environment.");
        }
        if (ModList.DRAGON_API.isLoaded())
        {
            LogHelper.info("DragonAPI has been detected in this environment.");
        }
        if (ModList.PROJECTE.isLoaded())
        {
            LogHelper.info("Project E has been detected in this environment.");
        }
        if (ModList.SPIRITUS.isLoaded())
        {
            LogHelper.info("Spiritus Malus has been detected in this environment.");
        }
        if (ModList.SHUTTERS.isLoaded())
        {
            LogHelper.info("Shutters has been detected in this environment.");
        }
        if (ModList.EXTRACRAFT.isLoaded())
        {
            LogHelper.info("ExtraCraft has been detected in this environment.");
        }
    }

    @EventHandler
    public void registerCommands(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDebug());
        event.registerServerCommand(new CommandTrash());
    }

    private void registerEvents(Object target)
    {
        MinecraftForge.EVENT_BUS.register(target);
        FMLCommonHandler.instance().bus().register(target);
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
    @SideOnly(Side.CLIENT)
    public void onGuiEvent(GuiOpenEvent event)
    {
        if (event.gui instanceof GuiIngameModOptions)
            event.gui = new GuiModList(new GuiIngameMenu());
    }

    @Override
    public String modid()
    {
        return Reference.MODID;
    }

    @Override
    public String name()
    {
        return Reference.MODNAME;
    }

    @Override
    public String version()
    {
        return Reference.VERSION;
    }
}