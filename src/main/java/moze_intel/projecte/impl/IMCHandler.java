package moze_intel.projecte.impl;

import java.util.Locale;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.event.FMLInterModComms;
import moze_intel.projecte.utils.PELogger;

public class IMCHandler {

    public static void handleIMC(FMLInterModComms.IMCMessage msg) {
        String messageKey = msg.key.toLowerCase(Locale.ROOT);
        if ("registeremc".equals(messageKey)) {
            PELogger.logWarn(
                    "Mod %s is using a deprecated version of the ProjectE API, their EMC registrations have been ignored",
                    msg.getSender());
        } else {
            PELogger.logWarn("Received unknown message \"%s\" from mod %s, ignoring.", messageKey, msg.getSender());
        }
    }

    private static void whitelistNBT(FMLInterModComms.IMCMessage msg) {
        ItemStack s = msg.getItemStackValue();
        if (s != null) {
            ((BlacklistProxyImpl) BlacklistProxyImpl.instance).doWhitelistNBT(s, msg.getSender());
        }
    }

    private static <T, U extends T> Class<U> loadAndCheckSubclass(String name, Class<T> toCheck) {
        try {
            Class<?> clazz = Class.forName(name);
            if (toCheck.isAssignableFrom(clazz)) {
                return (Class<U>) clazz;
            }
        } catch (ClassNotFoundException ex) {
            PELogger.logWarn("IMC tried to identify a class that couldn't be found: %s", name);
        }
        return null;
    }
}
