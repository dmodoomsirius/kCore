package ga.Kolatra.kCore.Common.Events;

import ga.Kolatra.ExtraCraft.Common.Item.ModItems;
import ga.Kolatra.kCore.Common.Interfaces.BlockInterfaces;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ClientEventHandler
{
    @SubscribeEvent
    public void renderRF(RenderGameOverlayEvent.Post event)
    {
        if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null && event.type == RenderGameOverlayEvent.ElementType.TEXT)
        {
            if (Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem().equals(ModItems.wrench))
            {
                MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
                if(mop != null && Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) instanceof BlockInterfaces.IBlockOverlayText)
                {
                    BlockInterfaces.IBlockOverlayText overlayBlock = (BlockInterfaces.IBlockOverlayText) Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                    String[] strings = overlayBlock.getOverlayText(mop);
                    if(strings != null && strings.length > 0)
                        for(int is=0; is < strings.length; is++)
                            Minecraft.getMinecraft().fontRenderer.drawString(strings[is], event.resolution.getScaledWidth() / 2 + 8, event.resolution.getScaledHeight() / 2 + 8 + is * Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, 0xcccccc, true);
                }
            }
        }
    }
}
