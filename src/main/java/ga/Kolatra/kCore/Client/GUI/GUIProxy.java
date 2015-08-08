package ga.Kolatra.kCore.Client.GUI;

import ga.Kolatra.ExtraCraft.Client.GUI.GUISolarRF;
import ga.Kolatra.kCore.KCore;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class GUIProxy implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == KCore.GUI_SOLAR_RF)
        {
            return new GUISolarRF();
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
}
