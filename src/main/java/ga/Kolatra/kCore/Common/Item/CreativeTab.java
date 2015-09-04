package ga.Kolatra.kCore.Common.Item;

import ga.Kolatra.kCore.Common.Libraries.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTab extends CreativeTabs
{
    public CreativeTab()
    {
        super(Reference.MODID);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return Items.apple;
    }

}
