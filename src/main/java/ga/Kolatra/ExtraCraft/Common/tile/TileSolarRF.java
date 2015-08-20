package ga.kolatra.extracraft.common.tile;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarRF extends TileEntity implements IEnergyConnection, IEnergyStorage, IEnergyProvider
{
    public TileSolarRF() {}

    public static boolean isUnderSun;

    public static int energyStored;
    public static int maxPower = 100000;
    public static int generationRate = 10;
    public int maxExtraction;

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        int energyExtracted = Math.min(energyStored, Math.min(this.maxExtraction, maxExtract));

        energyStored -= energyExtracted;

        return energyExtracted;
    }

    @Override
    public int getEnergyStored()
    {
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return maxPower;
    }

    public int getProduction()
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

    public void setEnergy(int energy)
    {
        energyStored = Math.max(Math.min(energy, getMaxEnergyStored()), 0);
        this.markDirty();
    }

    public int getGeneration()
    {
        int ret;

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
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        energyStored = nbt.getInteger("Energy");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("Energy", energyStored);
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

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return from != ForgeDirection.UP;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        if (from == ForgeDirection.UP)
        {
            return 0;
        }
        else
        {
            int energyExtracted = Math.min(energyStored, Math.min(this.maxExtraction, maxExtract));

            if (!simulate)
            {
                energyStored -= energyExtracted;
            }
            return energyExtracted;
        }
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return getMaxEnergyStored();
    }
}
