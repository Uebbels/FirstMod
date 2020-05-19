package com.Uebbels.MyFirstMod.datagen;

import com.Uebbels.MyFirstMod.blocks.ModBlocks;
import com.Uebbels.MyFirstMod.items.Moditems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.FIRSTBLOCK)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', Moditems.FIRSTITEM)
                .setGroup("myfirstmod")
                .addCriterion("firstitem", InventoryChangeTrigger.Instance.forItems(Moditems.FIRSTITEM))
                .build(consumer);

    }
}
