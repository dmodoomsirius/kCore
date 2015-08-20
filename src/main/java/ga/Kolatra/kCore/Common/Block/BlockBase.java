package ga.Kolatra.kCore.Common.Block;

import ga.Kolatra.kCore.KCore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{
    public BlockBase()
    {
        super(Material.rock);
        this.setCreativeTab(KCore.cTab);
        this.setHardness(1.0F);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
}
