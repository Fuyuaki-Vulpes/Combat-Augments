package com.fuyuvulpes.combataugments.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PoisonSpikesBlock extends SpikesBlock {


    public PoisonSpikesBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof LivingEntity pLiving){
            pLiving.addEffect(new MobEffectInstance(MobEffects.POISON,80,1));
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
