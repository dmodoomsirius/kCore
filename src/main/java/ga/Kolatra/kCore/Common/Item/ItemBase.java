package ga.Kolatra.kCore.Common.Item;

import ga.Kolatra.kCore.KCore;

import net.minecraft.item.Item;

public class ItemBase extends Item
{

    public String modid;

    public ItemBase(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(KCore.cTab);
    }
}
