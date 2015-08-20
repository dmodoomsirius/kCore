package ga.kolatra.kcore;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class KCoreMod
{
    public static String MODID;
    public static String MODNAME;
    public static String MODVERSION;

    @Mod.EventHandler
    public abstract void preInit(FMLPreInitializationEvent event);

    @Mod.EventHandler
    public abstract void init(FMLInitializationEvent event);

    @Mod.EventHandler
    public abstract void postInit(FMLPostInitializationEvent event);
}
