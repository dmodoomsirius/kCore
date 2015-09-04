package ga.Kolatra.kCore.Client.GUI;

import ga.Kolatra.ExtraCraft.Client.GUI.GUISolarRF;
import ga.Kolatra.ExtraCraft.Common.Container.ContainerSolarRF;
import ga.Kolatra.ExtraCraft.Common.Tile.TileSolarRF;
import ga.Kolatra.kCore.Common.Libraries.Reference;

import cpw.mods.fml.common.network.IGuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GUIProxy implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == Reference.GUI_SOLAR_RF && world.getTileEntity(x, y, z) instanceof TileSolarRF)
        {
            return new ContainerSolarRF(player.inventory, (TileSolarRF) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == Reference.GUI_SOLAR_RF && world.getTileEntity(x, y, z) instanceof TileSolarRF)
        {
            return new GUISolarRF(player.inventory, (TileSolarRF) world.getTileEntity(x, y, z));
        }
        return null;
    }
}
