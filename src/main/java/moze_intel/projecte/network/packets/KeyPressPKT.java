package moze_intel.projecte.network.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import moze_intel.projecte.api.item.IExtraFunction;
import moze_intel.projecte.api.item.IItemCharge;
import moze_intel.projecte.api.item.IModeChanger;
import moze_intel.projecte.utils.PEKeybind;

public class KeyPressPKT implements IMessage {

    private PEKeybind key;

    public KeyPressPKT() {}

    public KeyPressPKT(PEKeybind key) {
        this.key = key;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        key = PEKeybind.values()[buf.readInt()];
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(key.ordinal());
    }

    public static class Handler implements IMessageHandler<KeyPressPKT, IMessage> {

        @Override
        public IMessage onMessage(final KeyPressPKT message, final MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            ItemStack stack = player.getHeldItem();

            switch (message.key) {
                case CHARGE:
                    if (stack != null && stack.getItem() instanceof IItemCharge) {
                        ((IItemCharge) stack.getItem()).changeCharge(player, stack);
                    }
                    break;
                case EXTRA_FUNCTION:
                    if (stack != null && stack.getItem() instanceof IExtraFunction) {
                        ((IExtraFunction) stack.getItem()).doExtraFunction(stack, player);
                    }
                case MODE:
                    if (stack != null && stack.getItem() instanceof IModeChanger) {
                        ((IModeChanger) stack.getItem()).changeMode(player, stack);
                    }
                    break;
            }
            return null;
        }
    }
}
