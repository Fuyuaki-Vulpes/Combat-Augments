package com.fuyuvulpes.combataugments.registries;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.DaggerEntity;
import com.fuyuvulpes.combataugments.entity.DungeonGuardEntity;
import com.fuyuvulpes.combataugments.entity.YukiOnnaEntity;
import com.fuyuvulpes.combataugments.entity.golems.WoolGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,  CombatantsAugmentsMod.MODID);


    public static final RegistryObject<EntityType<DaggerEntity>> DAGGER = ENTITY_TYPES.register("dagger",
            () -> EntityType.Builder.<DaggerEntity>of(DaggerEntity::new, MobCategory.MISC).sized(0.5F,0.5F)
                    .clientTrackingRange(4)
                    .build(new ResourceLocation(CombatantsAugmentsMod.MODID,"dagger").toString()));


    public static final RegistryObject<EntityType<WoolGolemEntity>> WOOL_GOLEM = ENTITY_TYPES.register("wool_golem",
            () -> EntityType.Builder.<WoolGolemEntity>of(WoolGolemEntity::new,MobCategory.MISC).sized(0.75F,1.2F).clientTrackingRange(12)
                    .build(new ResourceLocation(CombatantsAugmentsMod.MODID,"wool_golem").toString()));



    public static final RegistryObject<EntityType<YukiOnnaEntity>> YUKI_ONNA = ENTITY_TYPES.register("yuki_onna",
            () -> EntityType.Builder.of(YukiOnnaEntity::new,MobCategory.MONSTER).sized(0.9F,1.8F).clientTrackingRange(12)
                    .build(new ResourceLocation(CombatantsAugmentsMod.MODID,"yuki_onna").toString()));


    public static final RegistryObject<EntityType<DungeonGuardEntity>> DUNGEON_GUARD = ENTITY_TYPES.register("dungeon_guard",
            () -> EntityType.Builder.of(DungeonGuardEntity::new,MobCategory.MISC).sized(2.5F,5.5F).clientTrackingRange(12)
                    .fireImmune().immuneTo(Blocks.WITHER_ROSE)
                    .build(new ResourceLocation(CombatantsAugmentsMod.MODID,"dungeon_guard").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
