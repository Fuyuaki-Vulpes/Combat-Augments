package com.fuyuvulpes.combataugments.registries;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypes {

    public static final ResourceKey<DamageType> BLEEDING = register("bleeding_damage");
    public static final ResourceKey<DamageType> SPIKES = register("spikes");
    public static final ResourceKey<DamageType> SPIKES_FALL = register("spikes_fall");


    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CombatantsAugmentsMod.MODID, name));
    }
}
