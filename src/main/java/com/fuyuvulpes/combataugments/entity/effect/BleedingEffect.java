package com.fuyuvulpes.combataugments.entity.effect;

import com.fuyuvulpes.combataugments.registries.DamageTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BleedingEffect extends MobEffect {

    public int bleedingTicks = -1;
    public BleedingEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(pLivingEntity.damageSources().source(DamageTypes.BLEEDING), (float) ( pAmplifier + 1));
        pLivingEntity.playSound(SoundEvents.WARDEN_HEARTBEAT, (float) (2.0 + pAmplifier * 0.5), 0.9F);
        if (pLivingEntity instanceof Player player) {
            player.causeFoodExhaustion(4.0F);
        }
        for (int i = 0; i < pAmplifier + 2; i++) {
            pLivingEntity.level().addParticle(ParticleTypes.DAMAGE_INDICATOR, pLivingEntity.getRandomX(1.0), pLivingEntity.getRandomY(), pLivingEntity.getRandomZ(1.0), 0, -0.2, 0);
        }
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        bleedingTicks = 60 - pAmplifier * 5;
        if (bleedingTicks > 0) {
            return pDuration > 0 && pDuration % bleedingTicks == 0;
        } else {
            return true;
        }
    }

    @Override
    public String getDescriptionId() {
        return "combataugments.potion.bleeding";
    }
}
