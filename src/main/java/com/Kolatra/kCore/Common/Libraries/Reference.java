package com.Kolatra.kCore.Common.Libraries;

public class Reference
{
    // A constants and references class. Will expand as the mod is developed.

    /**
     * GUIs
     */
    private static int MOD_GUI_INDEX = 0;
    public static final int GUI_SOLAR_RF = MOD_GUI_INDEX++;

    /**
     * kCore
     */
    public static final String MODID = "kCore";
    public static final String MODNAME = "kCore";

    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    public static final String DOMAIN = "kcore";
    public static final String PREFIX = DOMAIN + ":";

    public static final String DIRECTORY = "/assets/" + DOMAIN + "/";
    public static final String TEXTURE_DIRECTORY = "textures/";
    public static final String ITEM_DIRECTORY = TEXTURE_DIRECTORY + "items/";
    public static final String BLOCK_DIRECTORY = TEXTURE_DIRECTORY + "blocks/";
    public static final String GUI_DIRECTORY = TEXTURE_DIRECTORY + "gui/";

    /**
     * ExtraCraft
     */
    public static final String EXINDEX = "extrastuff:";
}
