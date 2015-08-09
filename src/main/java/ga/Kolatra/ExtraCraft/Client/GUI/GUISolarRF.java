package ga.Kolatra.ExtraCraft.Client.GUI;

import ga.Kolatra.ExtraCraft.Common.TileEntity.TileSolarRF;
import ga.Kolatra.kCore.KCore;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GUISolarRF extends GuiScreen
{
    public GUISolarRF()
    {
        super();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        ResourceLocation texture = new ResourceLocation(KCore.EXINDEX + "textures/gui/solarrf.png");
        this.drawDefaultBackground();
        this.mc.renderEngine.bindTexture(texture);
        //fontRendererObj.drawString("Stored energy " + TileSolarRF.energyStored, 175, 115, 0xFFFF); // Default window size.
        fontRendererObj.drawString("Stored energy " + TileSolarRF.energyStored, 275, 135, 0xFFFF); // Full window size.
        fontRendererObj.drawString("Generation rate:" + TileSolarRF.generationRate, 270, 195, 0XFFFF); // Full window size.
    }
}
