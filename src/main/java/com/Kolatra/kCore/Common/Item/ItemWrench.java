package com.Kolatra.kCore.Common.Item;

import com.Kolatra.kCore.Common.Libraries.BlockMeta;
import com.Kolatra.kCore.Common.Tile.TileBase;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemWrench extends ItemBase
{
    public ItemWrench()
    {
        super();
        this.setUnlocalizedName("wrench");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (!world.isRemote)
        {
            if (player != null)
            {
                BlockMeta blockMeta = new BlockMeta(world, x, y, z);

                if (player.isSneaking())
                {
                    TileEntity tile = world.getTileEntity(x, y, z);
                    if (world.getTileEntity(x, y, z) instanceof TileBase)
                    {
                        NBTTagCompound nbt = new NBTTagCompound();
                        tile.writeToNBT(nbt);

                        ItemStack stack1 = blockMeta.toItemStack();

                        stack1.setTagCompound(nbt);

                        world.removeTileEntity(x, y, z);
                        world.setBlockToAir(x, y, z);
                        world.spawnEntityInWorld(new EntityItem(world, x, y, z, stack1));
                    }
                }
            }
        }
        return false;
    }
}
