package moze_intel.projecte.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;
import moze_intel.projecte.gameObjs.container.AlchBagContainer;
import moze_intel.projecte.gameObjs.container.AlchChestContainer;
import moze_intel.projecte.gameObjs.container.PhilosStoneContainer;
import moze_intel.projecte.gameObjs.container.TransmutationContainer;
import moze_intel.projecte.gameObjs.container.inventory.AlchBagInventory;
import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
import moze_intel.projecte.gameObjs.gui.GUIAlchChest;
import moze_intel.projecte.gameObjs.gui.GUIPhilosStone;
import moze_intel.projecte.gameObjs.gui.GUITransmutation;
import moze_intel.projecte.gameObjs.tiles.AlchChestTile;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (ID) {
            case Constants.ALCH_CHEST_GUI:
                if (tile != null && tile instanceof AlchChestTile)
                    return new AlchChestContainer(player.inventory, (AlchChestTile) tile);
                break;
            case Constants.ALCH_BAG_GUI:
                return new AlchBagContainer(player.inventory, new AlchBagInventory(player, player.getHeldItem()));
            case Constants.PHILOS_STONE_GUI:
                return new PhilosStoneContainer(player.inventory);
            case Constants.TRANSMUTATION_GUI:
                return new TransmutationContainer(player.inventory, new TransmutationInventory(player));
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (ID) {
            case Constants.ALCH_CHEST_GUI:
                if (tile != null && tile instanceof AlchChestTile)
                    return new GUIAlchChest(player.inventory, (AlchChestTile) tile);
                break;
            case Constants.ALCH_BAG_GUI:
                return new GUIAlchChest(player.inventory, new AlchBagInventory(player, player.getHeldItem()));
            case Constants.PHILOS_STONE_GUI:
                return new GUIPhilosStone(player.inventory);
            case Constants.TRANSMUTATION_GUI:
                return new GUITransmutation(player.inventory, new TransmutationInventory(player));
        }

        return null;
    }
}
