package com.fuyuvulpes.combataugments.block;

import com.fuyuvulpes.combataugments.entity.golems.WoolGolemEntity;
import com.fuyuvulpes.combataugments.registries.BlockRegistry;
import com.fuyuvulpes.combataugments.registries.EntityTypeRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class GolemCoreBlock extends Block {

    private BlockPattern woolGolemFull;
    public GolemCoreBlock(Properties pProperties) {
        super(pProperties);
    }


/*

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        this.trySpawnGolem(pLevel, pPos, pPlacer);
    }

    private static final Predicate<BlockState> WOOL_BLOCK_PREDICATE = (blockState) -> {
        return blockState != null && blockState.is(BlockTags.WOOL);
    };



    private BlockPattern getOrCreateWoolGolemFull() {
        if (woolGolemFull == null) {
            woolGolemFull = BlockPatternBuilder.start().aisle("#~#", "#^#").where('#', BlockInWorld.hasState(WOOL_BLOCK_PREDICATE)).where('~', BlockInWorld.hasState(BlockStatePredicate.forBlock(BlockRegistry.GOLEM_CORE.get()))).where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.AIR))).build();
        }
        return woolGolemFull;
    }

    private void trySpawnGolem(Level level, BlockPos pos, LivingEntity livingEntity){

        BlockPattern.BlockPatternMatch blockPatternMatch = this.getOrCreateWoolGolemFull().find(level,pos);
        if (livingEntity instanceof Player player) {
            if (blockPatternMatch != null) {
                WoolGolemEntity woolGolem = new WoolGolemEntity(level, player);
                if (woolGolem != null && player != null) {
                    woolGolem.setOwnerUUID(player.getUUID());
                    woolGolem.setTame(true);
                    spawnGolemInWorld(level, blockPatternMatch, woolGolem, blockPatternMatch.getBlock(1, 1, 0).getPos());

                }

            }
        }
    }

    private static void spawnGolemInWorld(Level pLevel, BlockPattern.BlockPatternMatch pPatternMatch, Entity pGolem, BlockPos pPos) {
        clearPatternBlocks(pLevel, pPatternMatch);
        pGolem.moveTo((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.05D, (double)pPos.getZ() + 0.5D, 0.0F, 0.0F);
        pLevel.addFreshEntity(pGolem);

        for(ServerPlayer serverplayer : pLevel.getEntitiesOfClass(ServerPlayer.class, pGolem.getBoundingBox().inflate(5.0D))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, pGolem);
        }

        updatePatternBlocks(pLevel, pPatternMatch);
    }

    public static void clearPatternBlocks(Level pLevel, BlockPattern.BlockPatternMatch pPatternMatch) {
        for(int i = 0; i < pPatternMatch.getWidth(); ++i) {
            for(int j = 0; j < pPatternMatch.getHeight(); ++j) {
                BlockInWorld blockinworld = pPatternMatch.getBlock(i, j, 0);
                pLevel.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                pLevel.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
            }
        }

    }

    public static void updatePatternBlocks(Level pLevel, BlockPattern.BlockPatternMatch pPatternMatch) {
        for(int i = 0; i < pPatternMatch.getWidth(); ++i) {
            for(int j = 0; j < pPatternMatch.getHeight(); ++j) {
                BlockInWorld blockinworld = pPatternMatch.getBlock(i, j, 0);
                pLevel.blockUpdated(blockinworld.getPos(), Blocks.AIR);
            }
        }

    }







*/

}
