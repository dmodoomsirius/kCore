package com.Kolatra.kCore.Common.Compatibility;

import cpw.mods.fml.common.Loader;

public enum ModList
{
    SPIRITUS("SpiritusMalus"),
    EXTRACRAFT("ExtraCraft"),
    MININGFIX("MiningSpeedFix"),
    VOLTZENGINE("VoltzEngine"),
    DRAGONAPI("DragonAPI"),
    SHUTTERS("Shutters"),
    PROJECTE("ProjectE");

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
