package com.crispytwig.another_furniture.block.entity;

import com.crispytwig.another_furniture.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShelfBlockEntity extends BlockEntity implements Clearable {
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

    public boolean placeItem(ItemStack stack, int position) {
        ItemStack itemstack = this.items.get(position);
        if (itemstack.isEmpty()) {
            this.items.set(position, stack.split(stack.getCount()));
            this.markUpdated();
            return true;
        }

        return false;
    }

    public boolean removeItem(int index) {
        if (!this.items.get(index).isEmpty()) {
            double posX = worldPosition.getX() + 0.3 + 0.4 * (index % 2);
            double posY = worldPosition.getY() + 1.0;
            double posZ = worldPosition.getZ() + 0.3 + 0.4 * (index / 2);

            ItemEntity entity = new ItemEntity(this.level, posX, posY + 0.1, posZ, this.items.get(index).copy());
            this.level.addFreshEntity(entity);
            this.items.set(index, ItemStack.EMPTY);
            this.markUpdated();
            return true;
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

    public void removeAllItems() {
        boolean update = false;
        for (int i = 0; i < 4; i++) {
            if (!this.items.get(i).isEmpty()) {
                double posX = worldPosition.getX() + 0.3 + 0.4 * (i % 2);
                double posY = worldPosition.getY() + 1.0;
                double posZ = worldPosition.getZ() + 0.3 + 0.4 * (i / 2);

                ItemEntity entity = new ItemEntity(this.level, posX, posY + 0.1, posZ, this.items.get(i).copy());
                this.level.addFreshEntity(entity);
                this.items.set(i, ItemStack.EMPTY);
                update = true;
            }
        }
        if (update) {
            this.markUpdated();
        }
    }
}