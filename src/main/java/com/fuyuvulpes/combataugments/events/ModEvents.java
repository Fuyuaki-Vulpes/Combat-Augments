package com.fuyuvulpes.combataugments.events;


import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.DungeonGuardEntity;
import com.fuyuvulpes.combataugments.entity.YukiOnnaEntity;
import com.fuyuvulpes.combataugments.entity.golems.WoolGolemEntity;
import com.fuyuvulpes.combataugments.registries.EntityTypeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CombatantsAugmentsMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {


    @SubscribeEvent
    public static void entityAttributeCreationEvent(EntityAttributeCreationEvent event){
        event.put(EntityTypeRegistry.WOOL_GOLEM.get(), WoolGolemEntity.setAttributes());
        event.put(EntityTypeRegistry.DUNGEON_GUARD.get(), DungeonGuardEntity.setAttributes());
        event.put(EntityTypeRegistry.YUKI_ONNA.get(), YukiOnnaEntity.setAttributes());

    }
}
