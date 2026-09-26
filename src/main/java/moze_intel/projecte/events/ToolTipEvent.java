package moze_intel.projecte.events;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.math.LongMath;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.api.item.IPedestalItem;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.EMCHelper;

@SideOnly(Side.CLIENT)
public class ToolTipEvent {

    @SubscribeEvent
    public void tTipEvent(ItemTooltipEvent event) {
        ItemStack current = event.itemStack;
        Item currentItem = current.getItem();
        Block currentBlock = Block.getBlockFromItem(currentItem);

        if (current == null) {
            return;
        }

        if (ProjectEConfig.showPedestalTooltip && currentItem instanceof IPedestalItem) {
            if (!(ProjectEConfig.showPedestalTooltipInGUI)) {
                event.toolTip.add(
                        EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("pe.pedestal.on_pedestal")
                                + " ");
                List<String> description = ((IPedestalItem) currentItem).getPedestalDescription();
                if (description.isEmpty()) {
                    event.toolTip.add(IPedestalItem.TOOLTIPDISABLED);
                } else {
                    event.toolTip.addAll(((IPedestalItem) currentItem).getPedestalDescription());
                }
            }

        }

        if (ProjectEConfig.showUnlocalizedNames) {
            event.toolTip.add("UN: " + Item.itemRegistry.getNameForObject(current.getItem()));
        }

        if (ProjectEConfig.showODNames) {
            for (int id : OreDictionary.getOreIDs(current)) {
                event.toolTip.add("OD: " + OreDictionary.getOreName(id));
            }
            if (currentBlock instanceof BlockFluidBase) {
                event.toolTip.add("Fluid: " + ((BlockFluidBase) currentBlock).getFluid().getName());
            }
        }

        if (ProjectEConfig.showEMCTooltip) {
            if (EMCHelper.doesItemHaveEmc(current)) {
                int value = EMCHelper.getEmcValue(current);

                event.toolTip.add(
                        EnumChatFormatting.YELLOW + StatCollector.translateToLocal("pe.emc.emc_tooltip_prefix")
                                + " "
                                + EnumChatFormatting.WHITE
                                + String.format("%,d", value));

                if (current.stackSize > 1) {
                    long total;
                    try {
                        total = LongMath.checkedMultiply(value, current.stackSize);
                    } catch (ArithmeticException e) {
                        total = Long.MAX_VALUE;
                    }
                    if (total < 0 || total <= value || total > Integer.MAX_VALUE) {
                        event.toolTip.add(
                                EnumChatFormatting.YELLOW
                                        + StatCollector.translateToLocal("pe.emc.stackemc_tooltip_prefix")
                                        + " "
                                        + EnumChatFormatting.OBFUSCATED
                                        + StatCollector.translateToLocal("pe.emc.too_much"));
                    } else {
                        event.toolTip.add(
                                EnumChatFormatting.YELLOW
                                        + StatCollector.translateToLocal("pe.emc.stackemc_tooltip_prefix")
                                        + " "
                                        + EnumChatFormatting.WHITE
                                        + String.format("%,d", value * current.stackSize));
                    }

                }
            }
        }

        if (current.hasTagCompound()) {
            if (current.stackTagCompound.getBoolean("ProjectEBlock")) {
                event.toolTip.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("pe.misc.wrenched_block"));

                if (current.stackTagCompound.getDouble("EMC") > 0) {
                    event.toolTip.add(
                            EnumChatFormatting.YELLOW + String.format(
                                    StatCollector.translateToLocal("pe.emc.storedemc_tooltip") + " "
                                            + EnumChatFormatting.RESET
                                            + "%,d",
                                    (int) current.stackTagCompound.getDouble("EMC")));
                }
            }
            if (current.getItem() instanceof IItemEmc || current.stackTagCompound.hasKey("StoredEMC")) {
                double value = 0;
                if (current.stackTagCompound.hasKey("StoredEMC")) {
                    value = current.stackTagCompound.getDouble("StoredEMC");
                } else {
                    value = ((IItemEmc) current.getItem()).getStoredEmc(current);
                }

                event.toolTip.add(
                        EnumChatFormatting.YELLOW + StatCollector.translateToLocal("pe.emc.storedemc_tooltip")
                                + " "
                                + EnumChatFormatting.RESET
                                + Constants.EMC_FORMATTER.format(value));
            }

            if (current.stackTagCompound.hasKey("StoredXP")) {
                event.toolTip.add(
                        String.format(
                                EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal(
                                        "pe.misc.storedxp_tooltip") + " " + EnumChatFormatting.GREEN + "%,d",
                                current.stackTagCompound.getInteger("StoredXP")));
            }
        }
    }
}
