package moze_intel.projecte.gameObjs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

import cpw.mods.fml.common.registry.GameRegistry;
import moze_intel.projecte.config.ProjectEConfig;
import moze_intel.projecte.gameObjs.blocks.AlchemicalChest;
import moze_intel.projecte.gameObjs.blocks.TransmutationStone;
import moze_intel.projecte.gameObjs.customRecipes.RecipeAlchemyBag;
import moze_intel.projecte.gameObjs.items.AlchemicalBag;
import moze_intel.projecte.gameObjs.items.CovalenceDust;
import moze_intel.projecte.gameObjs.items.PhilosophersStone;
import moze_intel.projecte.gameObjs.items.Tome;
import moze_intel.projecte.gameObjs.items.TransmutationTablet;
import moze_intel.projecte.gameObjs.items.itemBlocks.ItemAlchemyChestBlock;
import moze_intel.projecte.gameObjs.items.itemBlocks.ItemTransmutationBlock;
import moze_intel.projecte.gameObjs.tiles.AlchChestTile;

public class ObjHandler {

    public static final CreativeTabs cTab = new CreativeTab();
    public static Block alchChest = new AlchemicalChest();
    // 转化桌
    public static Block transmuteStone = new TransmutationStone();
    public static Item philosStone = new PhilosophersStone();
    public static Item alchBag = new AlchemicalBag();
    public static Item covalence = new CovalenceDust();

    public static Item tome = new Tome();

    public static Item transmutationTablet = new TransmutationTablet();

    public static void register() {

        // Blocks with ItemBlock
        GameRegistry.registerBlock(alchChest, ItemAlchemyChestBlock.class, "alchemical_chest");
        GameRegistry.registerBlock(transmuteStone, ItemTransmutationBlock.class, "transmutation_table");

        // Items
        GameRegistry.registerItem(philosStone, philosStone.getUnlocalizedName());
        GameRegistry.registerItem(alchBag, alchBag.getUnlocalizedName());
        GameRegistry.registerItem(tome, tome.getUnlocalizedName());
        GameRegistry.registerItem(transmutationTablet, transmutationTablet.getUnlocalizedName());

        // Tile Entities
        GameRegistry.registerTileEntityWithAlternatives(AlchChestTile.class, "AlchChestTile", "Alchemical Chest Tile");

    }

    public static void addRecipes() {
        ItemStack diamondReplacement = new ItemStack(Items.diamond);

        if (ProjectEConfig.altCraftingMat) {
            diamondReplacement = new ItemStack(Items.nether_star);
        }

        // Shaped Recipes
        // Philos Stone
        GameRegistry.addRecipe(
                new ItemStack(philosStone),
                "RGR",
                "GDG",
                "RGR",
                'R',
                Items.redstone,
                'G',
                Items.glowstone_dust,
                'D',
                diamondReplacement);
        GameRegistry.addRecipe(
                new ItemStack(philosStone),
                "GRG",
                "RDR",
                "GRG",
                'R',
                Items.redstone,
                'G',
                Items.glowstone_dust,
                'D',
                diamondReplacement);

        // Alchemical Chest
        GameRegistry.addRecipe(
                new ItemStack(alchChest),
                "LMH",
                "SDS",
                "ICI",
                'D',
                diamondReplacement,
                'L',
                new ItemStack(covalence, 1, 0),
                'M',
                new ItemStack(covalence, 1, 1),
                'H',
                new ItemStack(covalence, 1, 2),
                'S',
                Blocks.stone,
                'I',
                Items.iron_ingot,
                'C',
                Blocks.chest);

        // Alchemical Bags
        for (int i = 0; i < 16; i++) {
            GameRegistry.addRecipe(
                    new ItemStack(alchBag, 1, i),
                    "CCC",
                    "WAW",
                    "WWW",
                    'C',
                    new ItemStack(covalence, 1, 2),
                    'A',
                    alchChest,
                    'W',
                    new ItemStack(Blocks.wool, 1, i));
        }

        // Transmutation Table
        GameRegistry.addRecipe(
                new ItemStack(transmuteStone),
                "OSO",
                "SPS",
                "OSO",
                'S',
                Blocks.stone,
                'O',
                Blocks.obsidian,
                'P',
                philosStone);

        // TransmutationTablet
        // GameRegistry.addRecipe(new ItemStack(transmutationTablet), "DSD", "STS", "DSD", 'D', new
        // ItemStack(matterBlock, 1, 0), 'S', Blocks.stone, 'T', transmuteStone);
        GameRegistry.addShapelessRecipe(new ItemStack(transmutationTablet), transmuteStone);

        // Shapeless Recipes
        // Philos Stone exchanges
        GameRegistry.addShapelessRecipe(
                new ItemStack(Items.ender_pearl),
                philosStone,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.iron_ingot, 8), philosStone, Items.gold_ingot);
        GameRegistry.addShapelessRecipe(
                new ItemStack(Items.gold_ingot),
                philosStone,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot,
                Items.iron_ingot);
        GameRegistry.addShapelessRecipe(
                new ItemStack(Items.diamond),
                philosStone,
                Items.gold_ingot,
                Items.gold_ingot,
                Items.gold_ingot,
                Items.gold_ingot);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_ingot, 4), philosStone, Items.diamond);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald), philosStone, Items.diamond, Items.diamond);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 2), philosStone, Items.emerald);

        // Covalence dust
        GameRegistry.addShapelessRecipe(
                new ItemStack(covalence, 40, 0),
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                new ItemStack(Items.coal, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(covalence, 40, 1), Items.iron_ingot, Items.redstone);
        GameRegistry.addShapelessRecipe(new ItemStack(covalence, 40, 2), Items.diamond, Items.coal);

        // Custom Recipe managment
        for (int i = 1; i <= 15; i++) {
            GameRegistry.addRecipe(
                    new RecipeAlchemyBag(
                            new ItemStack(alchBag, 1, 15 - i),
                            new ItemStack(alchBag, 1, 0),
                            new ItemStack(Items.dye, 1, i)));
            GameRegistry.addRecipe(
                    new RecipeAlchemyBag(
                            new ItemStack(alchBag, 1, 0),
                            new ItemStack(alchBag, 1, i),
                            new ItemStack(Items.dye, 1, 15)));
        }
        RecipeSorter.register(
                "Alchemical Bags Recipes",
                RecipeAlchemyBag.class,
                Category.SHAPELESS,
                "before:minecraft:shaped");
    }

}
