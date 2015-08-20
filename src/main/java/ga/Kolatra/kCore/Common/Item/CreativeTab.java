package ga.kolatra.kcore.common.item;

import ga.kolatra.kcore.common.libraries.Reference;

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
