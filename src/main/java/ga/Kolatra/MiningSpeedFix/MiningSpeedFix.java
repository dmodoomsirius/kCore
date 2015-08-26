package ga.Kolatra.MiningSpeedFix;

import ga.Kolatra.kCore.KCoreMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Mod(modid = "MiningSpeedFix", name = "MiningSpeedFix", version = "1.0")
public class MiningSpeedFix extends KCoreMod
{

    @Override
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Override
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @SubscribeEvent
    public void changeSpeed(PlayerEvent.BreakSpeed event)
    {
        if (event.entityPlayer.isInWater() || !event.entityPlayer.onGround)
        {
            event.newSpeed = event.newSpeed * 5;
        }
    }

}
