package ga.Kolatra.ExtraCraft.Client.GUI;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GUISolarRF extends GuiContainer
{
    private final ResourceLocation solarRF = new ResourceLocation("extrastuff:textures/gui/container/solarrf.png");

    public GUISolarRF(Container invSlots)
    {
        super(invSlots);
        xSize = 176;
        ySize = 187;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        // NO-OP
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(solarRF);
    }
}
