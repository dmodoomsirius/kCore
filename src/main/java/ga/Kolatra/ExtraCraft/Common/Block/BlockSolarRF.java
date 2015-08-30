package ga.Kolatra.ExtraCraft.Common.Block;

import ga.Kolatra.ExtraCraft.Common.Tile.TileSolarRF;
import ga.Kolatra.kCore.Common.Block.BlockBase;
import ga.Kolatra.kCore.Common.Libraries.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSolarRF extends BlockBase implements ITileEntityProvider
{
    private IIcon textureTop;

    public BlockSolarRF()
    {
        this.setBlockName("solar_rf");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileSolarRF();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (side == 1) // top
        {
            return this.textureTop;
        }
        return this.blockIcon;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            if (player != null)
            {
                if (world.getTileEntity(x, y, z) instanceof TileSolarRF)
                {
                    //player.openGui(KCore.INSTANCE, KCore.GUI_SOLAR_RF, world, x, y, z);
                }
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(Reference.EXINDEX + "solar_rf");
        this.textureTop = register.registerIcon(Reference.EXINDEX + "solar_rf_top");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase elb, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, elb, stack);
        TileSolarRF tileEntity = (TileSolarRF) world.getTileEntity(x, y, z);
        if (stack.hasTagCompound())
        {
            tileEntity.readFromNBT(stack.getTagCompound());
        }
    }
}
