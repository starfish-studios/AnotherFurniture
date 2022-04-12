package com.crispytwig.another_furniture.block.entity;

import com.crispytwig.another_furniture.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShelfBlockEntity extends BlockEntity implements Clearable {
    private static final int NUM_SLOTS = 4;
    private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    public ShelfBlockEntity(BlockPos p_155301_, BlockState p_155302_) {
        super(ModBlockEntities.SHELF.get(), p_155301_, p_155302_);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void load(CompoundTag p_155312_) {
        super.load(p_155312_);
        this.items.clear();
        ContainerHelper.loadAllItems(p_155312_, this.items);
    }

    protected void saveAdditional(CompoundTag p_187486_) {
        super.saveAdditional(p_187486_);
        ContainerHelper.saveAllItems(p_187486_, this.items, true);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        return compoundtag;
    }

    public boolean placeFood(ItemStack p_59054_) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                this.items.set(i, p_59054_.split(1));
                this.markUpdated();
                return true;
            }
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void clearContent() {
        this.items.clear();
    }

    public void dowse() {
        if (this.level != null) {
            this.markUpdated();
        }

    }
}