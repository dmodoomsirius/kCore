package ga.Kolatra.ExtraCraft.Common.Item;

import ga.Kolatra.kCore.Common.Item.ItemBase;
import ga.Kolatra.kCore.Common.Libraries.BlockMeta;
import ga.Kolatra.kCore.Common.Libraries.Reference;
import ga.Kolatra.kCore.Common.Tile.TileBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemWrench extends ItemBase
{
    public ItemWrench()
    {
        super();
        this.setUnlocalizedName("wrench");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(Reference.EXINDEX + "wrench");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        return this.itemIcon;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int sideHit, float px, float py, float pz)
    {
        if (!world.isRemote)
        {
            if (player != null)
            {
                if (player.isSneaking())
                {
                    TileEntity tile = world.getTileEntity(blockX, blockY, blockZ);
                    BlockMeta blockMeta = new BlockMeta(world, blockX, blockY, blockZ);
                    if (world.getTileEntity(blockX, blockY, blockZ) instanceof TileBase)
                    {
                        NBTTagCompound nbt = new NBTTagCompound();
                        tile.writeToNBT(nbt);

                        ItemStack stack1 = blockMeta.toItemStack();

                        stack1.setTagCompound(nbt);

                        world.removeTileEntity(blockX, blockY, blockZ);
                        world.setBlockToAir(blockX, blockY, blockZ);
                        world.spawnEntityInWorld(new EntityItem(world, blockX, blockY, blockZ, stack1));
                    }
                }
            }
        }
        return false;
    }
}
