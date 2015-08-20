package ga.kolatra.extracraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockInstExplosive extends Explosives
{
    public BlockInstExplosive()
    {
        this.setBlockName("instant_explosive");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if (!world.isRemote)
        {
            if (world.isBlockIndirectlyGettingPowered(x, y, z))
            {
                world.setBlockToAir(x, y, z);
                world.createExplosion(null, x, y, z, 3, true);
            }
        }
    }
}
