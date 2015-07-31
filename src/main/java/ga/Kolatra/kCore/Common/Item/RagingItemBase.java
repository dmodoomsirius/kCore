package ga.Kolatra.kCore.Common.Item;

import ga.Kolatra.kCore.KCore;

import net.minecraft.item.Item;

public class RagingItemBase extends Item
{

    public String modid;

    public RagingItemBase(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(KCore.cTab);
    }
}
