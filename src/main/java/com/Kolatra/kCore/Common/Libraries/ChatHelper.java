package com.Kolatra.kCore.Common.Libraries;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class ChatHelper
{
    /**********************************************************************
     * The code in this class is provided in DragonAPI, written by Reika. *
     * I take no credit for the code written here.                        *
     * These are some basic chat functions that make it easier to send    *
     * chat messages to players without worring about having to use       *
     * IChatComponent or anything stupid like that.                       *
     **********************************************************************/
    public static void sendChatToPlayer(EntityPlayer ep, String string)
    {
        String[] parts = string.split("\\n");
        for (int i = 0; i < parts.length; i++)
        {
            ChatComponentTranslation chat = new ChatComponentTranslation(parts[i]);
            ep.addChatMessage(chat);
        }
    }

    public static void sendChatToAllOnServer(String string)
    {
        String[] parts = string.split("\\n");
        MinecraftServer server = MinecraftServer.getServer();
        if (server != null)
        {
            ServerConfigurationManager config = server.getConfigurationManager();
            if (config != null)
            {
                for (int i = 0; i < parts.length; i++)
                {
                    ChatComponentTranslation chat = new ChatComponentTranslation(parts[i]);
                    config.sendChatMsg(chat);
                }
            }
        }
    }

    public static void sendChatComponentToAll(IChatComponent icc)
    {
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(icc);
    }

    public static void sendChatComponentToPlayer(EntityPlayer entityPlayer, IChatComponent iChatComponent)
    {
        entityPlayer.addChatMessage(iChatComponent);
    }
}