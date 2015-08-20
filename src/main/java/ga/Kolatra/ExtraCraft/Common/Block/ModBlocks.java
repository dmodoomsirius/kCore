package ga.Kolatra.ExtraCraft.Common.Block;

import ga.Kolatra.ExtraCraft.Common.TileEntity.TileSolarRF;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;

public class ModBlocks
{
    public static final Block blockSolarRF = new BlockSolarRF();
    public static final Block blockInstEx = new BlockInstExplosive();
    public static final Block blockGlassPressure = new BlockGlassPressurePlate("glass_pressure", Material.glass, Sensitivity.everything);

    public static void register()
    {
        // Blocks
        GameRegistry.registerBlock(blockSolarRF, "solar_rf");
        GameRegistry.registerBlock(blockInstEx, "instant_explosive");
        GameRegistry.registerBlock(blockGlassPressure, "glass_pressure");

        // Tile Entities
        GameRegistry.registerTileEntity(TileSolarRF.class, "SolarRFTile");
    }
}
