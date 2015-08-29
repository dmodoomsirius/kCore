package ga.Kolatra.kCore.Common.Interfaces;

import net.minecraft.util.MovingObjectPosition;

/**
 * Thanks to BluSunrize for letting me use this.
 */
public class BlockInterfaces
{
    public interface IBlockOverlayText
    {
        String[] getOverlayText(MovingObjectPosition mop);
    }
}
