package ga.Kolatra.kCore.Tile;

import ga.Kolatra.ExtraCraft.Common.TileEntity.TileSolarRF;
import ga.Kolatra.kCore.Testing.AbstractTest;
import ga.Kolatra.kCore.Testing.VoltzTestRunner;
import junit.framework.Test;
import org.junit.runner.RunWith;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//@RunWith(VoltzTestRunner.class)
public class SolarRFTest extends AbstractTest implements Test
{
    World world;
    TileEntity tileEntity;
    TileSolarRF solarRF;

    public void testEnergy()
    {
        assertFalse("This should be true?", solarRF.isUnderSun);
    }
}
