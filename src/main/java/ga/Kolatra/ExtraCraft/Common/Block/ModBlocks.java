package ga.Kolatra.ExtraCraft.Common.Block;

import ga.Kolatra.ExtraCraft.Common.TileEntity.TileSolarRF;
import ga.Kolatra.ExtraCraft.Common.TileEntity.TileTest;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks
{
    public static Block blockRandom = new BlockRandom("random_block");
    public static Block blockSolarRF = new BlockSolarRF("solar_rf");

    public static void register()
    {
        // Blocks
        GameRegistry.registerBlock(blockRandom, "random_block");
        GameRegistry.registerBlock(blockSolarRF, "solar_rf");

        // Tile Entities
        GameRegistry.registerTileEntity(TileTest.class, "TestBlockTile");
        GameRegistry.registerTileEntity(TileSolarRF.class, "SolarRFTile");
    }
}
