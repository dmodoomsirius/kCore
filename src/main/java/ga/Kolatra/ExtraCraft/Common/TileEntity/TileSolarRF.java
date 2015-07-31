package ga.Kolatra.ExtraCraft.Common.TileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;

public class TileSolarRF extends TileEntity
{
    public TileSolarRF()
    {

    }

    public TileSolarRF(double maxPower, int powerGen)
    {
        this.maxPower = maxPower;
        this.generationRate = powerGen;
    }

    public boolean isUnderSun = false;

    public static double energyStored;
    public double maxPower = 100000;
    public double generationRate = 10;

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
        return generationRate;
    }

    public boolean canFill()
    {
        return energyStored < maxPower;
    }

    public boolean canOperate()
    {
        return isUnderSun && canFill();
    }

    public void setEnergy(double energy)
    {
        energyStored = Math.max(Math.min(energy, getMaxPower()), 0);
        this.markDirty();
    }

    private void keepMaxPower()
    {
        energyStored = getMaxPower();
        this.markDirty();
    }

    public double getGeneration()
    {
        double rte;

        if (isUnderSun)
        {
            rte = generationRate;

            if (worldObj.getBiomeGenForCoords(xCoord >> 4, zCoord >> 4) instanceof BiomeGenDesert)
            {
                rte *= 1.5;
            }

            return rte;
        }
        return 0;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {

    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (worldObj.isRemote)
        {
            //return;
        }

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
            setEnergy(getEnergyStored() + getProduction());
            if (energyStored > maxPower)
                energyStored = maxPower;
        }
    }
}
