package com.fuyuvulpes.combataugments.datagen;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CombatantsAugmentsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new RecipeProvider(packOutput));
        generator.addProvider(true, new BlockstateProvider(packOutput, existingFileHelper));
        generator.addProvider(true, LootTablesProvider.create(packOutput));
        generator.addProvider(true, new ItemModelProvider(packOutput, existingFileHelper));
    }
}
