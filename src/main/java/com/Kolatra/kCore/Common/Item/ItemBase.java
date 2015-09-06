package com.Kolatra.kCore.Common.Item;

import com.Kolatra.kCore.KCore;

import net.minecraft.item.Item;

public class ItemBase extends Item
{
    public ItemBase()
    {
        super();
        this.setCreativeTab(KCore.cTab);
    }
}
