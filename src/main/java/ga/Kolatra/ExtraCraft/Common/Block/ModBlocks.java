package ga.Kolatra.ExtraCraft.Common.Block;

import ga.Kolatra.ExtraCraft.Common.TileEntity.TileSolarRF;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks
{
    public static Block blockSolarRF = new BlockSolarRF();

    public static void register()
    {
        // Blocks
        GameRegistry.registerBlock(blockSolarRF, "solar_rf");

        // Tile Entities
        GameRegistry.registerTileEntity(TileSolarRF.class, "SolarRFTile");
    }
}
