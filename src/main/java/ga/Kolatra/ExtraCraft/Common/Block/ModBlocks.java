package ga.Kolatra.ExtraCraft.Common.Block;

import ga.Kolatra.ExtraCraft.Common.Tile.TileSolarRF;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks
{
    public static final Block blockSolarRF = new BlockSolarRF();
    public static final Block blockInstEx = new BlockInstExplosive();
    public static final Block blockGlassPressure = new BlockGlassPressurePlate();

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
