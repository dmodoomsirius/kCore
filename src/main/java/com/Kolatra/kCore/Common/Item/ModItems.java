package com.Kolatra.kCore.Common.Item;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;

public class ModItems
{
    public static Item wrench = new ItemWrench();

    public static void register()
    {
        GameRegistry.registerItem(wrench, "wrench");
    }
}
