package ga.kolatra.extracraft.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems
{
    public static Item itemSwing = new ItemSwing();
    public static Item debug = new ItemDebug("item_debug");
    public static Item wrench = new ItemWrench("item_wrench");

    public static void register()
    {
        GameRegistry.registerItem(itemSwing, "item_swing");
        GameRegistry.registerItem(debug, "item_debug");
        GameRegistry.registerItem(wrench, "item_wrench");
    }
}
