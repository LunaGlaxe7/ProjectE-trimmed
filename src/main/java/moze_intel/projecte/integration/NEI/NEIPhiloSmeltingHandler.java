package moze_intel.projecte.integration.NEI;

import java.awt.*;

import net.minecraft.util.StatCollector;

import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class NEIPhiloSmeltingHandler extends ShapedRecipeHandler {

    private static String name = StatCollector.translateToLocal("pe.nei.philo");

    private static String id = "philoSmelting";

    public int[][] stackorder = new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 }, { 0, 2 }, { 1, 2 }, { 2, 0 },
            { 2, 1 }, { 2, 2 } };

    public String getRecipeName() {
        return name;
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (!(outputId.equals(id) && getClass() == NEIPhiloSmeltingHandler.class)) {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public boolean isRecipe2x2(int recipe) {
        return getIngredientStacks(recipe).size() <= 4;
    }

    @Override
    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(83, 23, 25, 10), id));
    }
}
