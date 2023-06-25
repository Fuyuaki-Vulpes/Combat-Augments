package com.fuyuvulpes.combataugments.block;

import com.fuyuvulpes.combataugments.registries.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class DungeonDoorBlock extends DoorBlock {

    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");


    private final BlockSetType type;


    public DungeonDoorBlock(Properties properties, BlockSetType type) {
        super(properties, type);
        this.type = type;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, Boolean.FALSE).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, Boolean.FALSE).setValue(HALF, DoubleBlockHalf.LOWER).setValue(LOCKED, Boolean.TRUE));


    }


    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getItemInHand(pHand).is(ItemRegistry.DUNGEON_KEY.get()) && !isOpen(pState) && isLocked(pState)){
            ItemStack stack = pPlayer.getItemInHand(pHand);
            pState = pState.cycle(OPEN).setValue(LOCKED, Boolean.FALSE);
            pLevel.setBlock(pPos, pState, 10);
            this.playSound(pPlayer, pLevel, pPos, pState.getValue(OPEN));
            pLevel.gameEvent(pPlayer, this.isOpen(pState) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pPos);
            stack.shrink(1);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        else if (!isLocked(pState)){

            pState = pState.cycle(OPEN);
            pLevel.setBlock(pPos, pState, 10);
            this.playSound(pPlayer, pLevel, pPos, pState.getValue(OPEN));
            pLevel.gameEvent(pPlayer, this.isOpen(pState) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pPos);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);


        }
        else {
            return InteractionResult.PASS;

        }
    }


    public boolean isLocked(BlockState pState) {
        return pState.getValue(LOCKED);
    }


    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {

    }

    private void playSound(@Nullable Entity pSource, Level pLevel, BlockPos pPos, boolean pIsOpening) {
        pLevel.playSound(pSource, pPos, pIsOpening ? this.type.doorOpen() : this.type.doorClose(), SoundSource.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(LOCKED);
    }

}
