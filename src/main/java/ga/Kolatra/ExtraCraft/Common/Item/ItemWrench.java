package ga.Kolatra.ExtraCraft.Common.Item;

import ga.Kolatra.ExtraCraft.Common.Tile.TileSolarRF;
import ga.Kolatra.kCore.Common.Item.ItemBase;
import ga.Kolatra.kCore.Common.Libraries.ClientUtils;
import ga.Kolatra.kCore.Common.Libraries.Reference;
import ga.Kolatra.kCore.Common.Tile.TileBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemWrench extends ItemBase
{
    private Block block;
    private int meta;

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
                    MovingObjectPosition mop = ClientUtils.mc().objectMouseOver;
                    Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
                    TileEntity tile = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                    if(mop != null && ClientUtils.mc().thePlayer.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) instanceof TileBase)
                    {
                        NBTTagCompound nbt = new NBTTagCompound();
                        tile.writeToNBT(nbt);

                        world.removeTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                        world.setBlockToAir(mop.blockX, mop.blockY, mop.blockZ);
                        block.dropBlockAsItem(world, mop.blockX, mop.blockY, mop.blockZ, world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ), 0);
                    }
                }
            }
        }
        return false;
    }
}
