package com.Uebbels.MyFirstMod.datagen;

import com.Uebbels.MyFirstMod.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {


    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.FIRSTBLOCK, createStandardTable("firstblock", ModBlocks.FIRSTBLOCK));
    }
}
