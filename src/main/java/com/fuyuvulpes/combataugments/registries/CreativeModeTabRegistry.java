package com.fuyuvulpes.combataugments.registries;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CombatantsAugmentsMod.MODID);

    public static RegistryObject<CreativeModeTab> COMBATAUGMENTS_TAB = MOD_TABS.register("combataugments_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.NETHERITE_GREATAXE.get())).title(Component.translatable("itemGroup." + CombatantsAugmentsMod.MODID + ".main_tab")).build());





    public static void register(IEventBus eventBus){
        MOD_TABS.register(eventBus);
    }

}
