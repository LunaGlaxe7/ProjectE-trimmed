package moze_intel.projecte.impl;

import net.minecraft.item.ItemStack;

import com.google.common.base.Preconditions;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import moze_intel.projecte.api.proxy.IBlacklistProxy;
import moze_intel.projecte.utils.NBTWhitelist;
import moze_intel.projecte.utils.PELogger;

public class BlacklistProxyImpl implements IBlacklistProxy {

    public static final IBlacklistProxy instance = new BlacklistProxyImpl();

    private BlacklistProxyImpl() {}

    @Override
    public void whitelistNBT(ItemStack stack) {
        Preconditions.checkNotNull(stack);
        Preconditions.checkState(
                Loader.instance().isInState(LoaderState.POSTINITIALIZATION),
                "Mod %s registering NBT whitelist at incorrect time!",
                Loader.instance().activeModContainer().getModId());
        doWhitelistNBT(stack, Loader.instance().activeModContainer().getModId());
    }

    protected void doWhitelistNBT(ItemStack s, String modName) {
        NBTWhitelist.register(s);
        PELogger.logInfo("Mod %s whitelisted %s for NBT duping", modName, s.toString());
    }
}
