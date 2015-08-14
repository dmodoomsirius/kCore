package ga.Kolatra.kCore.Common.Compatibility;

import cpw.mods.fml.common.Loader;

public enum ModCompat
{
    VE("VoltzEngine"),
    DRAGON_API("DragonAPI");

    public final String mod_id;

    ModCompat(String id)
    {
        this.mod_id = id;
    }

    public boolean isLoaded()
    {
        return Loader.isModLoaded(mod_id);
    }
}
