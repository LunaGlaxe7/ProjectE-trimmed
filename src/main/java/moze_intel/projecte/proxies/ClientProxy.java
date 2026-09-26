package moze_intel.projecte.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import moze_intel.projecte.events.KeyPressEvent;
import moze_intel.projecte.events.PlayerRender;
import moze_intel.projecte.events.ToolTipEvent;
import moze_intel.projecte.events.TransmutationRenderingEvent;
import moze_intel.projecte.gameObjs.ObjHandler;
import moze_intel.projecte.gameObjs.tiles.AlchChestTile;
import moze_intel.projecte.playerData.AlchBagProps;
import moze_intel.projecte.playerData.Transmutation;
import moze_intel.projecte.playerData.TransmutationProps;
import moze_intel.projecte.rendering.ChestItemRenderer;
import moze_intel.projecte.rendering.ChestRenderer;
import moze_intel.projecte.utils.ClientKeyHelper;

public class ClientProxy implements IProxy {
    // These three following methods are here to prevent a strange crash in the dedicated server whenever packets are
    // received
    // and the wrapped methods are called directly.

    @Override
    public void clearClientKnowledge() {
        Transmutation.clearKnowledge(FMLClientHandler.instance().getClientPlayerEntity());
    }

    @Override
    public TransmutationProps getClientTransmutationProps() {
        return TransmutationProps.getDataFor(FMLClientHandler.instance().getClientPlayerEntity());
    }

    @Override
    public AlchBagProps getClientBagProps() {
        return AlchBagProps.getDataFor(FMLClientHandler.instance().getClientPlayerEntity());
    }

    @Override
    public void registerKeyBinds() {
        ClientKeyHelper.registerMCBindings();
    }

    @Override
    public void registerRenderers() {
        // Items
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ObjHandler.alchChest), new ChestItemRenderer());
        // Blocks
        ClientRegistry.bindTileEntitySpecialRenderer(AlchChestTile.class, new ChestRenderer());
    }

    @Override
    public void registerClientOnlyEvents() {
        MinecraftForge.EVENT_BUS.register(new ToolTipEvent());
        MinecraftForge.EVENT_BUS.register(new TransmutationRenderingEvent());
        FMLCommonHandler.instance().bus().register(new KeyPressEvent());

        PlayerRender pr = new PlayerRender();
        MinecraftForge.EVENT_BUS.register(pr);
        FMLCommonHandler.instance().bus().register(pr);
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return FMLClientHandler.instance().getClientPlayerEntity();
    }

}
