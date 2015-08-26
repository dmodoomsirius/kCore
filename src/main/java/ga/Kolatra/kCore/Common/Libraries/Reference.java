package ga.Kolatra.kCore.Common.Libraries;

public class Reference
{
    // A constants and references class. Will expand as the mod is developed.

    private static int modGUIIndex = 0;

    public static final int GUI_SOLAR_RF = modGUIIndex++;

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
    public static final String GUI_DIRECTORY = TEXTURE_DIRECTORY + "gui/";
}
