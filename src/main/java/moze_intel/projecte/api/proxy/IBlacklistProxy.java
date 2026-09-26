package moze_intel.projecte.api.proxy;

import net.minecraft.item.ItemStack;

public interface IBlacklistProxy {

    /**
     * Whitelist an ItemStack, allowing stacks of its kind to dupe NBT during Transmutation and Condensation Call this
     * during the postinit phase
     * 
     * @param stack The stack to whitelist
     */
    void whitelistNBT(ItemStack stack);
}
