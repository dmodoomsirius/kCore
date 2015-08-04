package ga.Kolatra.ExtraCraft.Common.Block;

import ga.Kolatra.ExtraCraft.Common.TileEntity.TileSolarRF;
import ga.Kolatra.kCore.Common.Block.BlockBase;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSolarRF extends BlockBase implements ITileEntityProvider
{
    public BlockSolarRF()
    {
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
        return this.blockIcon;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        System.out.println(TileSolarRF.energyStored);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon("extrastuff:solar_rf");
    }
}
