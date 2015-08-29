package ga.Kolatra.kCore.Common.Compatibility;

import cpw.mods.fml.common.Loader;

public enum ModList
{
    SPIRITUS("SpiritusMalus"), // possibly dead mod
    MININGFIX("MiningSpeedFix"),
    VE("VoltzEngine"),
    DRAGON_API("DragonAPI"),
    PROJECTE("projecte");

    public final String mod_id;

    ModList(String id)
    {
        this.mod_id = id;
    }

    public boolean isLoaded()
    {
        return Loader.isModLoaded(mod_id);
    }
}
