/*
 * Copyright (c) 2015. RagingCommandoSquad under the license specified at the LICENSE file
 */

package ga.Kolatra.kCore.Common.Network;

import ga.Kolatra.kCore.Common.Libraries.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class NetworkHandler
{
    public static NetworkHandler INSTANCE = new NetworkHandler();
    private final SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    private NetworkHandler()
    {
    }
}
