package ga.Kolatra.ExtraCraft.Common.Block;

import cofh.api.energy.IEnergyConnection;
import ga.Kolatra.ExtraCraft.Common.TileEntity.TileTest;
import ga.Kolatra.kCore.Common.Block.BlockBase;
import ga.Kolatra.kCore.Common.Libraries.ChatHelper;
import ga.Kolatra.kCore.KCore;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockRandom extends BlockBase implements ITileEntityProvider, IEnergyConnection
{

    public BlockRandom()
    {
        this.setCreativeTab(KCore.cTab);
        this.setBlockTextureName("extrastuff:random_block");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileTest();

        // Actually links the TE to the Block.
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            return false;
        }
        ChatHelper.sendChatToPlayer(player, "onBlockActivated");
        if (KCore.debugMode)
        {
            ChatHelper.sendChatToAllOnServer("This is a debug mode test!");
        }
        return true;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from)
    {
        return true;
    }
}
