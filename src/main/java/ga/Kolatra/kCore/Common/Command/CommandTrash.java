package ga.Kolatra.kCore.Common.Command;

import ga.Kolatra.kCore.Common.Libraries.ChatHelper;
import ga.Kolatra.kCore.KCore;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandTrash extends CommandBase
{

    @Override
    public String getCommandName()
    {
        return "trash";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "trash";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] string)
    {
        EntityPlayerMP playerMP = getCommandSenderAsPlayer(sender);

        KCore.trashItemsOnThrow = !KCore.trashItemsOnThrow;
        ChatHelper.sendChatToPlayer(playerMP, "Trash mode is " + KCore.trashItemsOnThrow + "!");
    }
}
