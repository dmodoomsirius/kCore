package com.Kolatra.kCore.Common.Events;

import com.Kolatra.kCore.Common.Compatibility.ModList;
import com.Kolatra.kCore.Common.Libraries.ChatHelper;
import com.Kolatra.kCore.KCore;
import com.Kolatra.kCore.Common.Libraries.PlayerChecks;

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
        sb.append(EnumChatFormatting.AQUA.toString() + "Welcome ");
        sb.append(EnumChatFormatting.DARK_RED.toString() + "Kolatra");
        sb.append(EnumChatFormatting.AQUA.toString() + ", ");
        sb.append(EnumChatFormatting.AQUA.toString() + "developer of:\n");
        sb.append(EnumChatFormatting.WHITE.toString() + "\n");
        sb.append(EnumChatFormatting.GOLD.toString() + "kCore ");
        sb.append(EnumChatFormatting.GREEN.toString() + "(Installed)\n");
        sb.append(EnumChatFormatting.YELLOW.toString() + "ExtraCraft ");
        sb.append(EnumChatFormatting.GREEN.toString() + "(Installed)\n");
        sb.append(EnumChatFormatting.DARK_RED.toString() + "Spiritus Malus ");
        if (ModList.SPIRITUS.isLoaded())
        {
            sb.append(EnumChatFormatting.GREEN.toString() + "(Installed)\n");
        }
        else
        {
            sb.append(EnumChatFormatting.RED.toString() + "(Not Installed)\n");
        }
        sb.append(EnumChatFormatting.LIGHT_PURPLE.toString() + "MiningFixCore ");
        if (ModList.MININGFIX.isLoaded())
        {
            sb.append(EnumChatFormatting.GREEN.toString() + "(Installed)\n");
        }
        else
        {
            sb.append(EnumChatFormatting.RED.toString() + "(Not Installed)\n");
        }
        sb.append(EnumChatFormatting.WHITE.toString() + "\n");
        sb.append(EnumChatFormatting.AQUA.toString() + "to the server!");
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
