package ga.Kolatra.ExtraCraft.Client.GUI;

import ga.Kolatra.ExtraCraft.Common.Tile.TileSolarRF;
import ga.Kolatra.ExtraCraft.Common.Container.ContainerSolarRF;
import ga.Kolatra.kCore.Common.Libraries.ClientUtils;
import ga.Kolatra.kCore.Common.Libraries.Reference;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class GUISolarRF extends GuiContainer
{
    TileSolarRF tile = new TileSolarRF();

    public static final ResourceLocation texture = new ResourceLocation(Reference.PREFIX + Reference.GUI_DIRECTORY + "solarrf.png");

    public GUISolarRF(InventoryPlayer inventoryPlayer, TileSolarRF tile)
    {
        super(new ContainerSolarRF(inventoryPlayer, tile));
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        ClientUtils.font().drawString("Solar Panel", 8, 6, 4210752);
        ClientUtils.font().drawString(tile.energyStorage.getEnergyStored() + "/" + tile.energyStorage.getMaxEnergyStored() + " RF", 8, 36, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 256, 256, xSize, ySize);
    }
}
