package ga.kolatra.defaultsoundmod;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.settings.GameSettings;

@Mod(modid = DefaultSoundCore.MODID, version = DefaultSoundCore.VERSION)
public class DefaultSoundCore
{
    public static final String MODID = "DefaultSound";
    public static final String VERSION = "0.1";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        DefaultSoundConfig.init(new File("DefaultSoundLevels"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        if (event.getSide().isClient())
        {
            Minecraft minecraft = Minecraft.getMinecraft();
            GameSettings gameSettings = minecraft.gameSettings;
            gameSettings.setSoundLevel(SoundCategory.MASTER, DefaultSoundConfig.defaultMasterSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.MUSIC, DefaultSoundConfig.defaultMusicSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.RECORDS, DefaultSoundConfig.defaultRecordsSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.WEATHER, DefaultSoundConfig.defaultWeatherSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.BLOCKS, DefaultSoundConfig.defaultBlocksSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.MOBS, DefaultSoundConfig.defaultMobsSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.ANIMALS, DefaultSoundConfig.defaultAnimalsSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.PLAYERS, DefaultSoundConfig.defaultPlayersSoundLevel);
            gameSettings.setSoundLevel(SoundCategory.AMBIENT, DefaultSoundConfig.defaultAmbientSoundLevel);
        }
        else
        {
            System.out.println("You are installing this mod on a server. While it will not crash, it will not function. Please remove this mod from the server.");
        }
    }
}
