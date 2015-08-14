package ga.Kolatra.DefaultSoundMod;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class DefaultSoundConfig
{
    public static float defaultMasterSoundLevel;
    public static float defaultMusicSoundLevel;
    public static float defaultRecordsSoundLevel;
    public static float defaultWeatherSoundLevel;
    public static float defaultBlocksSoundLevel;
    public static float defaultMobsSoundLevel;
    public static float defaultAnimalsSoundLevel;
    public static float defaultPlayersSoundLevel;
    public static float defaultAmbientSoundLevel;

    /*
    MASTER
    MUSIC
    RECORDS
    WEATHER
    BLOCKS
    MOBS
    ANIMALS
    PLAYERS
    AMBIENT
     */
    public static void init(File configFile)
    {
        Configuration configuration = new Configuration(configFile);

        try
        {
            configuration.load();

            defaultMasterSoundLevel = configuration.getFloat("Master Sound Level", "misc", 0.2F, 0.0F, 1.0F, "Master sound level.");
            defaultMusicSoundLevel = configuration.getFloat("Music Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Music sound level.");
            defaultRecordsSoundLevel = configuration.getFloat("Records Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Records sound level.");
            defaultWeatherSoundLevel = configuration.getFloat("Records Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Records sound level.");
            defaultBlocksSoundLevel = configuration.getFloat("Records Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Records sound level.");
            defaultMobsSoundLevel = configuration.getFloat("Records Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Records sound level.");
            defaultAnimalsSoundLevel = configuration.getFloat("Records Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Records sound level.");
            defaultPlayersSoundLevel = configuration.getFloat("Records Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Records sound level.");
            defaultAmbientSoundLevel = configuration.getFloat("Records Sound Level", "misc", 1.0F, 0.0F, 1.0F, "Records sound level.");
        }
        catch (Exception e)
        {
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
