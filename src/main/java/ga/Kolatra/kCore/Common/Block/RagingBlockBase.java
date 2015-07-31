package ga.Kolatra.kCore.Common.Block;

import ga.Kolatra.kCore.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RagingBlockBase extends Block
{
    public RagingBlockBase()
    {
        super(Material.rock);
    }

    public RagingBlockBase(String name)
    {
        super(Material.rock);
        this.setCreativeTab(KCore.cTab);
        this.setBlockName(name);
        this.setHardness(1.5F);
        this.setHarvestLevel("pickaxe", 3);
        this.setResistance(10F);
        this.setStepSound(Block.soundTypeStone);
    }
}
