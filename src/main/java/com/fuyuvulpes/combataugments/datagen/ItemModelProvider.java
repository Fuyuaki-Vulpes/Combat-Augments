package com.fuyuvulpes.combataugments.datagen;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CombatantsAugmentsMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemRegistry.BANDAGE);
        simpleItem(ItemRegistry.DUNGEON_KEY);
        simpleItem(ItemRegistry.GATE_POTTERY_SHERD);
        simpleItem(ItemRegistry.SPELL_EXPLOSION);

        
        simpleItem(ItemRegistry.WOOL_GOLEM_SPAWN_EGG);

        withExistingParent(ItemRegistry.DUNGEON_GUARD_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ItemRegistry.YUKI_ONNA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));


        handheldBigItem(ItemRegistry.STONE_GREATAXE);
        handheldBigItem(ItemRegistry.IRON_GREATAXE);
        handheldBigItem(ItemRegistry.GOLD_GREATAXE);
        handheldBigItem(ItemRegistry.DIAMOND_GREATAXE);
        handheldBigItem(ItemRegistry.NETHERITE_GREATAXE);

        handheldBigItem(ItemRegistry.STONE_SCYTHE);
        handheldBigItem(ItemRegistry.IRON_SCYTHE);
        handheldBigItem(ItemRegistry.GOLD_SCYTHE);
        handheldBigItem(ItemRegistry.DIAMOND_SCYTHE);
        handheldBigItem(ItemRegistry.NETHERITE_SCYTHE);

        handheldBigItem(ItemRegistry.STONE_NAGINATA);
        handheldBigItem(ItemRegistry.IRON_NAGINATA);
        handheldBigItem(ItemRegistry.GOLD_NAGINATA);
        handheldBigItem(ItemRegistry.DIAMOND_NAGINATA);
        handheldBigItem(ItemRegistry.NETHERITE_NAGINATA);

        handheldBigItem(ItemRegistry.STONE_HALBERD);
        handheldBigItem(ItemRegistry.IRON_HALBERD);
        handheldBigItem(ItemRegistry.GOLD_HALBERD);
        handheldBigItem(ItemRegistry.DIAMOND_HALBERD);
        handheldBigItem(ItemRegistry.NETHERITE_HALBERD);

        handheldItem(ItemRegistry.STONE_RAPIER);
        handheldItem(ItemRegistry.IRON_RAPIER);
        handheldItem(ItemRegistry.GOLD_RAPIER);
        handheldItem(ItemRegistry.DIAMOND_RAPIER);
        handheldItem(ItemRegistry.NETHERITE_RAPIER);

        handheldItem(ItemRegistry.STONE_KATANA);
        handheldItem(ItemRegistry.IRON_KATANA);
        handheldItem(ItemRegistry.GOLD_KATANA);
        handheldItem(ItemRegistry.DIAMOND_KATANA);
        handheldItem(ItemRegistry.NETHERITE_KATANA);

        handheldItem(ItemRegistry.STONE_DAGGER);
        handheldItem(ItemRegistry.IRON_DAGGER);
        handheldItem(ItemRegistry.GOLD_DAGGER);
        handheldItem(ItemRegistry.DIAMOND_DAGGER);
        handheldItem(ItemRegistry.NETHERITE_DAGGER);
        
        simpleItem(ItemRegistry.POLESTICK);
        simpleItem(ItemRegistry.LONGSTRING);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CombatantsAugmentsMod.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CombatantsAugmentsMod.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldBigItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(CombatantsAugmentsMod.MODID,"item/handheld_big")).texture("layer0",
                new ResourceLocation(CombatantsAugmentsMod.MODID, "item/" + item.getId().getPath()));
    }
}
