package ga.Kolatra.ExtraCraft.Common.Tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.TileEnergyHandler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarRF extends TileEntity implements IEnergyProvider
{
    // Variables
    public static boolean isUnderSun;
    public static int energyStored;
    public int maxExtract = 100;
    public int maxReceive = 100;
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

                //ForgeDirection is a useful helper class for handling directions.
                int targetX = xCoord + ForgeDirection.getOrientation(i).offsetX;
                int targetY = yCoord + ForgeDirection.getOrientation(i).offsetY;
                int targetZ = zCoord + ForgeDirection.getOrientation(i).offsetZ;

                TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
                if (tile instanceof IEnergyHandler) {

                    int maxExtract = this.maxExtract; //Gets the maximum amount of energy that can be extracted from this tile in one tick.
                    int maxAvailable = storage.extractEnergy(maxExtract, true); //Simulates removing "maxExtract" to find out how much energy is actually available.
                    int energyTransferred = ((IEnergyHandler) tile).receiveEnergy(ForgeDirection.getOrientation(i).getOpposite(), maxAvailable, false); //Sends "maxAvailable" to the target tile and records how much energy was accepted.

                    storage.extractEnergy(energyTransferred, false); //Extract the energy transferred from the internal storage.
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
