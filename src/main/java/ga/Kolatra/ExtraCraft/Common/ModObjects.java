package ga.Kolatra.ExtraCraft.Common;

import ga.Kolatra.ExtraCraft.Common.Block.BlockGlassPressurePlate;
import ga.Kolatra.ExtraCraft.Common.Block.BlockInstExplosive;
import ga.Kolatra.ExtraCraft.Common.Block.BlockSolarRF;
import ga.Kolatra.ExtraCraft.Common.Item.ItemDebug;
import ga.Kolatra.ExtraCraft.Common.Item.ItemSword;
import ga.Kolatra.ExtraCraft.Common.Item.ItemWrench;
import ga.Kolatra.ExtraCraft.Common.Tile.TileSolarRF;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModObjects
{
    /**
     * Blocks
     */
    public static final Block blockSolarRF = new BlockSolarRF();
    public static final Block blockInstEx = new BlockInstExplosive();
    public static final Block blockGlassPressure = new BlockGlassPressurePlate();

    /**
     * Items
     */
    public static Item itemSword = new ItemSword();
    public static Item debug = new ItemDebug();
    public static Item wrench = new ItemWrench();

    public static void registerObjects()
    {
        initBlocks();
        initTiles();
        initItems();
        initRecipes();
    }

    private static void initBlocks()
    {
        GameRegistry.registerBlock(blockSolarRF, "solar_rf");
        GameRegistry.registerBlock(blockInstEx, "instant_explosive");
        GameRegistry.registerBlock(blockGlassPressure, "glass_pressure");
    }

    private static void initTiles()
    {
        GameRegistry.registerTileEntity(TileSolarRF.class, "TileSolarRF");
    }

    private static void initItems()
    {
        GameRegistry.registerItem(itemSword, "item_swing");
        GameRegistry.registerItem(debug, "item_debug");
        GameRegistry.registerItem(wrench, "item_wrench");
    }

    private static void initRecipes()
    {

    }
}
