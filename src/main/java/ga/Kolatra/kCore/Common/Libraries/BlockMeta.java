package ga.Kolatra.kCore.Common.Libraries;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockMeta
{
    private Block block;
    private int meta;

    public BlockMeta(World world, int x, int y, int z)
    {
        this.block = world.getBlock(x, y, z);
        this.meta = world.getBlockMetadata(x, y, z);
    }

    public ItemStack toItemStack()
    {
        return new ItemStack(block, 1, meta);
    }
}
