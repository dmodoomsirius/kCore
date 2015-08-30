package ga.Kolatra.ExtraCraft.Common.Tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import ga.Kolatra.kCore.Common.Interfaces.BlockInterfaces;
import ga.Kolatra.kCore.Common.Tile.TileBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarRF extends TileBase implements IEnergyProvider, IEnergyReceiver, BlockInterfaces.IBlockOverlayText
{
    public static boolean isUnderSun;
    public static boolean enabled;
    public static int energyStored;
    public int maxExtract = 1000;
    public int maxPower = 1000000;
    public int generationRate = 1000;
    public EnergyStorage energyStorage = new EnergyStorage(maxPower);

    public TileSolarRF()
    {
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (worldObj.isRemote)
        {
            return;
        }

        isUnderSun = worldObj.isDaytime() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord);

        if (enabled)
        {
            if (canOperate())
            {
                energyStorage.setEnergyStored(energyStorage.getEnergyStored() + generationRate);
                this.markDirty();
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

            if (energyStorage.getEnergyStored() > 0)
            {
                for (int i = 0; i < 6; i++){

                    int targetX = xCoord + ForgeDirection.getOrientation(i).offsetX;
                    int targetY = yCoord + ForgeDirection.getOrientation(i).offsetY;
                    int targetZ = zCoord + ForgeDirection.getOrientation(i).offsetZ;

                    TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
                    if (tile instanceof IEnergyHandler)
                    {

                        int maxExtract = this.maxExtract;
                        int maxAvailable = energyStorage.extractEnergy(maxExtract, true);
                        int energyTransferred = ((IEnergyHandler) tile).receiveEnergy(ForgeDirection.getOrientation(i).getOpposite(), maxAvailable, false);

                        energyStorage.extractEnergy(energyTransferred, false);
                        this.markDirty();
                        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    }
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
        energyStorage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        energyStorage.writeToNBT(nbt);
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
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
    {
        return energyStorage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from)
    {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from)
    {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public String[] getOverlayText(MovingObjectPosition mop)
    {
        if (enabled)
        {
            return new String[]
                    {
                            StatCollector.translateToLocal(EnumChatFormatting.GOLD + "Stored power: " + energyStorage.getEnergyStored() + " RF"),
                            StatCollector.translateToLocal(EnumChatFormatting.GREEN + "Enabled.")
                    };
        }
        else
        {
            return new String[]
                    {
                            StatCollector.translateToLocal(EnumChatFormatting.GOLD + "Stored power: " + energyStorage.getEnergyStored() + " RF"),
                            StatCollector.translateToLocal(EnumChatFormatting.RED + "Disabled.")
                    };
        }
    }
}
