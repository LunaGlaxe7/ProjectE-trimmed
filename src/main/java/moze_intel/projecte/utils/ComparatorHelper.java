package moze_intel.projecte.utils;

import net.minecraft.inventory.Container;
import net.minecraft.world.World;

import moze_intel.projecte.gameObjs.tiles.AlchChestTile;

/**
 * Utility class to get comparator outputs for a block
 */
public final class ComparatorHelper {

    public static int getForAlchChest(World world, int x, int y, int z) {
        return Container.calcRedstoneFromInventory(((AlchChestTile) world.getTileEntity(x, y, z)));
    }
}
