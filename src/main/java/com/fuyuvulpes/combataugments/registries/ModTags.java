package com.fuyuvulpes.combataugments.registries;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Blocks{}
    public static class EntityTypes{}
    public static class Items{}
    public static class Fluids{}

    public static class Biomes {
        public static final TagKey<Biome> YUKI_ONNA_SPAWNABLE = tag("yuki_onna_spawnable");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(CombatantsAugmentsMod.MODID, name));
        }
    }


}
