package ga.Kolatra.ExtraCraft.Common.TileEntity;

import cofh.api.energy.IEnergyConnection;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarRF extends TileEntity implements IEnergyConnection
{
    public TileSolarRF() {}

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

    public double getGeneration() // TODO Figure out ANY way to show this on the GUI, right now it throws NPE because worldObj is null on the client.
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
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        energyStored = nbt.getDouble("Energy");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setDouble("Energy", energyStored);
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
        if (from == ForgeDirection.UP)
        {
            return true;
        }
        return false;
    }
}
