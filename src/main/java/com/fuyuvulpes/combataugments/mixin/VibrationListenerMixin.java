package com.fuyuvulpes.combataugments.mixin;


import com.fuyuvulpes.combataugments.registries.MobEffectRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VibrationSystem.Listener.class)
public class VibrationListenerMixin {


    @Inject(method = "handleGameEvent", at = @At("HEAD"), cancellable = true)
    private void handleGameEventMixin(ServerLevel pLevel, GameEvent pGameEvent, GameEvent.Context pContext, Vec3 pPos, CallbackInfoReturnable<Boolean> cir){
        if (pContext.sourceEntity() instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffectRegistry.VIBRATION_OCCLUSION.get())){
            cir.setReturnValue(false);
        }
    }
}
