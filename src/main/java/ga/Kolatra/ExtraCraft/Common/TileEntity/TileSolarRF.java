package ga.Kolatra.ExtraCraft.Common.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;

public class TileSolarRF extends TileEntity implements IInventory
{
    public TileSolarRF()
    {

    }

    public static boolean isUnderSun;

    public static double energyStored;
    public static double maxPower = 100000;
    public static double generationRate = 10;

    public double getMaxPower()
    {
        return maxPower;
    }

    public double getEnergyStored()
    {
        return energyStored;
    }

    public double getProduction()
    {
        return getGeneration();
    }

    public static boolean canFill()
    {
        return energyStored < maxPower;
    }

    public static boolean canOperate()
    {
        return isUnderSun && canFill();
    }

    public void setEnergy(double energy)
    {
        energyStored = Math.max(Math.min(energy, getMaxPower()), 0);
        this.markDirty();
    }

    public double getGeneration() // TO-DO Figure out ANY way to show this on the GUI, right now it throws NPE because worldObj is null on the client.
    {
        double ret;

        if (isUnderSun)
        {
            ret = generationRate;

            if (isDesert())
            {
                ret *= 1.5;
            }

            return ret;
        }
        return 0;
    }

    public boolean isDesert()
    {
        return worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof BiomeGenDesert;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            if (worldObj.isDaytime() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord))
            {
                isUnderSun = true;
            }
            else
            {
                isUnderSun = false;
            }

            if (canOperate())
            {
                setEnergy(getEnergyStored() + getGeneration());
                if (energyStored > maxPower)
                    energyStored = maxPower;
            }
        }
    }

    private ItemStack[] inv;

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn)
    {
        return inv[slotIn];
    }

    @Override
    public ItemStack decrStackSize(int slot, int count)
    {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null)
        {
            if (stack.stackSize <= count)
            {
                setInventorySlotContents(slot, null);
            }
            else
            {
                stack = stack.splitStack(slot);
                if (stack.stackSize == 0)
                {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null)
        {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inv[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return "extrastuff.tilesolarrf";
    }

    @Override
    public boolean isCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }
}
