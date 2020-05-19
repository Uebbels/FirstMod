package com.Uebbels.MyFirstMod.items;

import com.Uebbels.MyFirstMod.MyFirstMod;
import net.minecraft.item.Item;

public class FirstItem extends Item {

    public FirstItem() {
        super(new Item.Properties()
                .group(MyFirstMod.setup.itemGroup)
                .maxStackSize(1)
        );
        setRegistryName("firstitem");
    }
}
