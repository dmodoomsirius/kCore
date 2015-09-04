package ga.Kolatra.kCore.Common.Libraries;

import ga.Kolatra.kCore.KCore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.util.FakePlayer;

public class PlayerChecks
{
    public static boolean isAdmin(EntityPlayer ep)
    {
        return MinecraftServer.getServer().getConfigurationManager().func_152596_g(ep.getGameProfile());
    }

    public static boolean isFakePlayer(EntityPlayer ep)
    {
        if (ep instanceof FakePlayer)
        {
            return true;
        }
        if (ep.getCommandSenderName().contains("CoFH") || ep.getCommandSenderName().contains("Thaumcraft"))
        {
            return true;
        }
        if (ep.getClass().getName().toLowerCase().contains("fake"))
        {
            return true;
        }
        return false;
    }

    public static boolean isKolatra(EntityPlayer ep)
    {
        return ep.getGameProfile().getId().equals(KCore.KolatraUUID) || ep.getCommandSenderName().equals("Kolatra");
    }
}
