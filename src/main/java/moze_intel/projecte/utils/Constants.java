package moze_intel.projecte.utils;

import java.text.DecimalFormat;

import com.google.common.collect.ImmutableMap;

import cpw.mods.fml.client.registry.RenderingRegistry;

public final class Constants {

    public static final DecimalFormat EMC_FORMATTER = new DecimalFormat("##.##");
    public static final int TILE_MAX_EMC = 1073741824;

    public static final int ALCH_CHEST_GUI = 0;
    public static final int ALCH_BAG_GUI = 1;
    @Deprecated
    public static final int TRANSMUTE_STONE_GUI = 2;

    public static final int PHILOS_STONE_GUI = 13;
    public static final int TRANSMUTATION_GUI = 14;

    public static final int CHEST_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

    public static final int MAX_VEIN_SIZE = 250;

    public static final int ENCH_EMC_BONUS = 5000;

    public static final ImmutableMap<String, String> SPACE_STRIP_NAME_MAP;

    static {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        // builder.put("Alchemical Chest", "alchemical_chest");
        // builder.put("Interdiction Torch", "interdiction_torch");
        builder.put("Transmutation Stone", "transmutation_table");
        // builder.put("Condenser", "condenser_mk1");
        // builder.put("Condenser MK2", "condenser_mk2");
        // builder.put("RM Furnace", "rm_furnace");
        // builder.put("RM Furnace Lit", "rm_furnace_lit");
        // builder.put("DM Furnace", "dm_furnace");
        // builder.put("DM Furnace Lit", "dm_furnace_lit");
        // builder.put("DM Pedestal", "dm_pedestal");
        // builder.put("Matter Block", "matter_block");
        // builder.put("Fuel Block", "fuel_block");
        // builder.put("Collector MK1", "collector_mk1");
        // builder.put("Collector MK2", "collector_mk2");
        // builder.put("Collector MK3", "collector_mk3");
        // builder.put("Relay MK1", "relay_mk1");
        // builder.put("Realy MK2", "relay_mk2");
        // builder.put("Relay MK3", "relay_mk3");
        // builder.put("Nova Catalyst", "nova_catalyst");
        // builder.put("Nova Cataclysm", "nova_cataclysm");
        SPACE_STRIP_NAME_MAP = builder.build();
    }
}
