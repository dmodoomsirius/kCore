package ga.Kolatra.kCore.Common.Item;

import ga.Kolatra.kCore.KCore;

import net.minecraft.item.Item;

public class ItemBase extends Item
{
    public ItemBase()
    {
        super();
        this.setCreativeTab(KCore.cTab);
    }
}
