package ga.Kolatra.ExtraCraft.Common.Tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.TileEnergyHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarRF extends TileEntity implements IEnergyProvider, IEnergyReceiver
{
    // Variables
    public static boolean isUnderSun;
    public static int energyStored;
    public int maxExtract = 100;
    public int maxPower = 1000000;
    public int generationRate = 10;
    protected TileEnergyHandler energy = new TileEnergyHandler();
    protected EnergyStorage storage = new EnergyStorage(maxPower, 0, maxExtract);

    public TileSolarRF()
    {
    }

    @Override
    public void updateEntity()
    {
        if (worldObj.isRemote) return;

        super.updateEntity();

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
            storage.setEnergyStored(energyStored + getGeneration());
        }

        if ((storage.getEnergyStored() > 0)) {
            for (int i = 0; i < 6; i++){

                int targetX = xCoord + ForgeDirection.getOrientation(i).offsetX;
                int targetY = yCoord + ForgeDirection.getOrientation(i).offsetY;
                int targetZ = zCoord + ForgeDirection.getOrientation(i).offsetZ;

                TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
                if (tile instanceof IEnergyHandler) {

                    int maxExtract = this.maxExtract;
                    int maxAvailable = storage.extractEnergy(maxExtract, true);
                    int energyTransferred = ((IEnergyHandler) tile).receiveEnergy(ForgeDirection.getOrientation(i).getOpposite(), maxAvailable, false);

                    storage.extractEnergy(energyTransferred, false);
                }
            }
        }
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
        energy.writeToNBT(nbt);
    }

    /* IEnergyHandler */
    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return from != ForgeDirection.UP;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
    {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return storage.getMaxEnergyStored();
    }
}
