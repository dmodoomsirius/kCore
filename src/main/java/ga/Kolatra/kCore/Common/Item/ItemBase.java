package ga.kolatra.kcore.common.item;

import ga.kolatra.kcore.KCore;

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
