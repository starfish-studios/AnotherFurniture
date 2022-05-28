package com.crispytwig.another_furniture.block.entity;

import com.crispytwig.another_furniture.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlanterBoxBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public PlanterBoxBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.PLANTER_BOX.get(), pWorldPosition, pBlockState);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public Item getItemFromSlot(int slot) {
        return this.items.get(slot).getItem();
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.items.clear();
        ContainerHelper.loadAllItems(pTag, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        ContainerHelper.saveAllItems(pTag, this.items, true);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        return compoundtag;
    }

    public boolean placeFlower(ItemStack stack) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                this.items.set(i, stack.split(1));
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

    @Override
    public void clearContent() {
        this.items.clear();
    }
}
