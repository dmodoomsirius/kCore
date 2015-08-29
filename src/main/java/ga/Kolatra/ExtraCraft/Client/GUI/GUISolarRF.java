package ga.Kolatra.ExtraCraft.Client.GUI;

import ga.Kolatra.ExtraCraft.Common.Tile.TileSolarRF;
import ga.Kolatra.ExtraCraft.Common.Container.ContainerSolarRF;
import ga.Kolatra.kCore.Common.Libraries.Reference;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class GUISolarRF extends GuiContainer
{
    protected TileSolarRF te = new TileSolarRF();
    protected InventoryPlayer player;

    Container container = new ContainerSolarRF(player, te);

    public static final ResourceLocation texture = new ResourceLocation(Reference.PREFIX + Reference.GUI_DIRECTORY + "solarrf.png");

    public GUISolarRF(Container container)
    {
        super(container);

        this.container = container;
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

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        fontRendererObj.drawString("Solar Panel", 8, 6, 4210752);
        fontRendererObj.drawString("Stored energy: " + te.getEnergyStored(null) + " RF", 8, 36, 4210752);
    }
}
