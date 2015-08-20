package ga.kolatra.kcore.common;

import ga.kolatra.kcore.Client.gui.GUIProxy;
import ga.kolatra.kcore.KCore;

import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy
{
    public static void init()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(KCore.INSTANCE, new GUIProxy());
    }
}
