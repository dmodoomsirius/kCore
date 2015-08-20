package ga.kolatra.kcore.Client.gui;

import ga.kolatra.extracraft.client.gui.GUISolarRF;
import ga.kolatra.extracraft.common.container.ContainerSolarRF;
import ga.kolatra.extracraft.common.tile.TileSolarRF;
import ga.kolatra.kcore.common.libraries.Reference;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GUIProxy implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (ID == Reference.GUI_SOLAR_RF && tileEntity instanceof TileSolarRF)
        {
            TileSolarRF tileSolarRF = new TileSolarRF();
            return new ContainerSolarRF(player.inventory, tileSolarRF);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        TileSolarRF solarTile = new TileSolarRF();
        Container solarCon = new ContainerSolarRF(player.inventory, solarTile);
        if (ID == Reference.GUI_SOLAR_RF && tileEntity instanceof TileSolarRF)
        {
            return new GUISolarRF(solarCon);
        }
        return null;
    }
}
