package ga.Kolatra.kCore.Common.Events;

import ga.Kolatra.ExtraCraft.Common.ModObjects;
import ga.Kolatra.kCore.Common.Interfaces.BlockInterfaces;
import ga.Kolatra.kCore.Common.Libraries.ClientUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ClientEventHandler
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderInformation(RenderGameOverlayEvent.Post event)
    {
        if (ClientUtils.mc().thePlayer != null && ClientUtils.mc().thePlayer.getCurrentEquippedItem() != null && event.type == RenderGameOverlayEvent.ElementType.TEXT)
        {
            if (ClientUtils.mc().thePlayer.getCurrentEquippedItem().getItem().equals(ModObjects.wrench))
            {
                MovingObjectPosition mop = ClientUtils.mc().objectMouseOver;
                if (mop != null && ClientUtils.mc().thePlayer.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) instanceof BlockInterfaces.IBlockOverlayText)
                {
                    BlockInterfaces.IBlockOverlayText overlayBlock = (BlockInterfaces.IBlockOverlayText) Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                    String[] strings = overlayBlock.getOverlayText(mop);
                    if (strings != null && strings.length > 0)
                    {
                        for (int is = 0; is < strings.length; is++)
                        {
                            ClientUtils.mc().fontRenderer.drawString(strings[is], event.resolution.getScaledWidth() / 2 + 8, event.resolution.getScaledHeight() / 2 + 8 + is * Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, 0xcccccc, true);
                        }
                    }
                }
            }
        }
    }
}
