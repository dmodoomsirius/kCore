package ga.Kolatra.ExtraCraft;

import ga.Kolatra.ExtraCraft.Common.Block.ModBlocks;
import ga.Kolatra.ExtraCraft.Common.Item.ModItems;
import ga.Kolatra.kCore.KCoreMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ExtraStuffCore.MODID, version = ExtraStuffCore.VERSION)
public class ExtraStuffCore extends KCoreMod
{
    public static final String MODID = "ExtraStuff";
    public static final String VERSION = "dev";

    public static final String PREFIX = "extrastuff:";

    @Override
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Override
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModBlocks.register();
        ModItems.register();
    }

    @Override
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
