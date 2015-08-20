package ga.kolatra.extracraft.common.block;

import ga.kolatra.kcore.KCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockGlassPressurePlate extends BlockPressurePlate
{
    protected BlockGlassPressurePlate(String name, Material material, Sensitivity sensitivity)
    {
        super(name, material, sensitivity);
        this.setCreativeTab(KCore.cTab);
        this.setBlockName("glass_pressure");
    }

    public BlockGlassPressurePlate()
    {
        this("glass_pressure", Material.glass, Sensitivity.everything);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(KCore.EXINDEX + "glass_pressure");
    }
}
