package ga.kolatra.extracraft.common.block;

import ga.kolatra.extracraft.common.tile.TileSolarRF;
import ga.kolatra.kcore.common.block.BlockBase;
import ga.kolatra.kcore.KCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSolarRF extends BlockBase implements ITileEntityProvider
{
    private IIcon textureTop;

    public BlockSolarRF()
    {
        this.setBlockTextureName("solar_rf");
        this.setBlockName("solar_rf");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSolarRF();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (side == 1) // top
        {
            return this.textureTop;
        }
        return this.blockIcon;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            if (player != null)
            {
                if (world.getTileEntity(x, y, z) instanceof TileSolarRF)
                {
                    player.openGui(KCore.INSTANCE, KCore.GUI_SOLAR_RF, world, x, y, z);
                }
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(KCore.EXINDEX + "solar_rf");
        this.textureTop = register.registerIcon(KCore.EXINDEX + "solar_rf_top");
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block blockBroken, int meta)
    {
        if (world.getTileEntity(x, y, z) instanceof TileSolarRF)
        {
            TileSolarRF.energyStored = 0;
        }
    }
}
