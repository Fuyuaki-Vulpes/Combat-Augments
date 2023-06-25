package com.fuyuvulpes.combataugments.registries;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.effect.BleedingEffect;
import com.fuyuvulpes.combataugments.entity.effect.VibrationOcclusion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobEffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CombatantsAugmentsMod.MODID);

    public static final RegistryObject<MobEffect> BLEEDING = EFFECTS.register("bleeding", () -> new BleedingEffect(MobEffectCategory.HARMFUL,6688793).addAttributeModifier(Attributes.MOVEMENT_SPEED,"21cebbd9-ab07-4276-a649-dd620816db27", (double)-0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> VIBRATION_OCCLUSION = EFFECTS.register("vibration_occlusion", () -> new VibrationOcclusion(MobEffectCategory.BENEFICIAL,9273750));


    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);

    }

}
