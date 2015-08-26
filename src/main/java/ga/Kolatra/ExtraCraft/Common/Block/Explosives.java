package ga.Kolatra.ExtraCraft.Common.Block;

import ga.Kolatra.kCore.Common.Block.BlockBase;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class Explosives extends BlockBase implements ITileEntityProvider
{
    public Explosives()
    {
        this.setHardness(0.1F);
    }

    @Override
    public Material getMaterial()
    {
        return Material.cloth;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return null;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return true;
    }
}
