package com.Kolatra.kCore.Common.Events;

import com.Kolatra.kCore.Common.Compatibility.ModList;
import com.Kolatra.kCore.Common.Libraries.ChatHelper;
import com.Kolatra.kCore.Common.Libraries.LogHelper;
import com.Kolatra.kCore.Common.Libraries.PlayerChecks;
import com.Kolatra.kCore.KCore;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerEvents
{
    private static final String kolatraMsg = getKolatraWelcome();

    private static String getKolatraWelcome()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(EnumChatFormatting.DARK_AQUA + "Welcome ");
        sb.append(EnumChatFormatting.DARK_RED + "Kolatra");
        sb.append(EnumChatFormatting.WHITE + ", ");
        sb.append(EnumChatFormatting.DARK_AQUA + "developer of:\n");
        sb.append(EnumChatFormatting.WHITE + "\n");
        sb.append(EnumChatFormatting.GOLD + "kCore ");
        sb.append(EnumChatFormatting.GREEN + "(Installed)\n");
        sb.append(EnumChatFormatting.YELLOW + "ExtraCraft ");
        sb.append(EnumChatFormatting.GREEN + "(Installed)\n");
        sb.append(EnumChatFormatting.DARK_RED + "Spiritus Malus ");
        if (ModList.SPIRITUS.isLoaded())
        {
            sb.append(EnumChatFormatting.GREEN + "(Installed)\n");
        }
        else
        {
            sb.append(EnumChatFormatting.RED + "(Not Installed)\n");
        }
        sb.append(EnumChatFormatting.LIGHT_PURPLE + "MiningFixCore ");
        if (ModList.MININGFIX.isLoaded())
        {
            sb.append(EnumChatFormatting.GREEN + "(Installed)\n");
        }
        else
        {
            sb.append(EnumChatFormatting.RED + "(Not Installed)\n");
        }
        sb.append(EnumChatFormatting.WHITE + "\n");
        sb.append(EnumChatFormatting.DARK_AQUA + "to the server!");
        return sb.toString();
    }

    @SubscribeEvent
    public void onDeveloperJoin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        if (PlayerChecks.isKolatra(event.player) || KCore.isKolatrasComputer())
        {
            ChatHelper.sendChatToAllOnServer(kolatraMsg);
        }

        if (KCore.isDeobf())
        {
            ChatComponentText deobfMsg = new ChatComponentText(EnumChatFormatting.GOLD + "kCore is running in a development environment!");
            ChatHelper.sendChatComponentToAll(deobfMsg);
        }
    }

    @SubscribeEvent
    public void onDisplayName(PlayerEvent.NameFormat evt)
    {
        if (evt.entityPlayer.getCommandSenderName().equals("Kolatra"))
        {
            evt.displayname = EnumChatFormatting.DARK_RED + "Kolatra";
        }
    }

    @SubscribeEvent
    public void trashItemOnThrow(ItemTossEvent event)
    {
        if (event.player != null)
        {
            if (KCore.trashItemsOnThrow)
            {
                event.setCanceled(true);
            }
        }
    }
}
