package ga.Kolatra.ExtraCraft.Common.Tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.TileEnergyHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarRF extends TileEntity implements IEnergyHandler
{
    public static boolean isUnderSun;
    public static int energyStored;
    public int maxExtract = 100;
    public int maxReceive = 100;
    public int maxPower = 1000000;
    public int generationRate = 10;
    protected TileEnergyHandler energy = new TileEnergyHandler();
    protected EnergyStorage storage = new EnergyStorage(maxPower);

    public TileSolarRF()
    {
    }

    public int getProduction()
    {
        return getGeneration();
    }

    public boolean canFill()
    {
        return energyStored < maxPower;
    }

    public boolean canOperate()
    {
        return isUnderSun && canFill();
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
        energy.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        //energy.writeToNBT(nbt);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return from != ForgeDirection.UP;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
    {
        if (from == ForgeDirection.UNKNOWN)
        {
            return this.storage.receiveEnergy(Math.min(maxReceive, this.maxReceive), simulate);
        }
        return 0;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        if (from == ForgeDirection.UNKNOWN)
        {
            return this.storage.extractEnergy(Math.min(maxExtract, this.maxExtract), simulate);
        }
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return maxPower;
    }

    @Override
    public void updateEntity()
    {
        if (worldObj.isRemote)
        {
            return;
        }

        super.updateEntity();

        if (worldObj.isDaytime() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord))
        {
            isUnderSun = true;
        }
        else
        {
            isUnderSun = false;
        }
    }
}
