package ga.Kolatra.kCore.Common.Block;

import ga.Kolatra.kCore.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{
    public BlockBase()
    {
        super(Material.rock);
        this.setCreativeTab(KCore.cTab);
    }
}
