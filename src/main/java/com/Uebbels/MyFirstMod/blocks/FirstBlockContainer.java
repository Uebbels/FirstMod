package com.Uebbels.MyFirstMod.blocks;

import com.Uebbels.MyFirstMod.tools.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

import static com.Uebbels.MyFirstMod.blocks.ModBlocks.FIRSTBLOCK_CONTAINER;


public class FirstBlockContainer extends Container {

    private  TileEntity tileEntity;
    private  PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public FirstBlockContainer(int windowID, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {
        super(FIRSTBLOCK_CONTAINER, windowID);
        TileEntity tileEntity = world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(inventory);

       tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
           addSlot(new SlotItemHandler(h,0, 64, 24));
       });

       layoutPlayerInventory(10, 70);

       trackInt(new IntReferenceHolder() {
           @Override
           public int get() {
               return getEnergy();
           }

           @Override
           public void set(int energy) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage)h).setEnergy(energy));
           }
       });
    }


    public int getEnergy() {
        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity. getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.FIRSTBLOCK );
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int horAmount, int dx) {
        for (int i = 0; i < horAmount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventory(int leftCol, int topRow) {
//        inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

//        Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 19);
    }
}
