package ga.Kolatra.kCore.Common;

import ga.Kolatra.kCore.Client.GUI.GUIProxy;
import ga.Kolatra.kCore.KCore;

import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy
{
    public static void init()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(KCore.INSTANCE, new GUIProxy());
    }
}
