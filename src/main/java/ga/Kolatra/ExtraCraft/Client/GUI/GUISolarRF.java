package ga.kolatra.extracraft.client.gui;

import ga.kolatra.extracraft.common.tile.TileSolarRF;
import ga.kolatra.extracraft.common.container.ContainerSolarRF;
import ga.kolatra.kcore.common.libraries.Reference;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GUISolarRF extends GuiContainer
{
    private static TileSolarRF te;
    private static InventoryPlayer player;

    static Container container = new ContainerSolarRF(player, te);

    public static final ResourceLocation texture = new ResourceLocation(Reference.PREFIX + Reference.GUI_DIRECTORY + "solarrf.png");

    public GUISolarRF(Container container)
    {
        super(container);
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
        fontRendererObj.drawString("Stored energy: " + TileSolarRF.energyStored + " RF", 8, 36, 4210752);
        fontRendererObj.drawString("Machine operating: " + TileSolarRF.canOperate(), 8, 66, 4210752);
    }
}
