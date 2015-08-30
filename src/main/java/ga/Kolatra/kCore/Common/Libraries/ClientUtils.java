package ga.Kolatra.kCore.Common.Libraries;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ClientUtils
{
    public static Minecraft mc()
    {
        return Minecraft.getMinecraft();
    }

    public static FontRenderer font()
    {
        return mc().fontRenderer;
    }
}
