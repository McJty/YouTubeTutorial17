package com.mcjty.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.List;

public class DemoBlock extends Block implements EntityBlock {

    public DemoBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .noOcclusion()
                .strength(2.0f));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DemoBE(pos, state);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (pStack.hasTag()) {
            CompoundTag blockEntityTag = pStack.getTag().getCompound("BlockEntityTag");

            if (!pLevel.isClientSide && !blockEntityTag.isEmpty()) {
                BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                if (blockEntity instanceof DemoBE demoBE) {
                    demoBE.load(blockEntityTag);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent("message.demo.tooltip"));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return (level1, pos, state1, tile) -> {
                if (tile instanceof DemoBE demo) {
                    demo.tickClient(state1);
                }
            };
        } else {
            return (level1, pos, state1, tile) -> {
                if (tile instanceof DemoBE demo) {
                    demo.tickServer(state1);
                }
            };
        }
    }
}
