package com.Kolatra.kCore.Common.Command;

import com.Kolatra.kCore.Common.Libraries.ChatHelper;
import com.Kolatra.kCore.KCore;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandDebug extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "debugmode";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/debugmode";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] string)
    {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);

        KCore.debugMode = !KCore.debugMode;
        ChatHelper.sendChatToPlayer(player, "Debug mode: " + KCore.debugMode);
    }
}
