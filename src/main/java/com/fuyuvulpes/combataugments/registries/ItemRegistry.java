package com.fuyuvulpes.combataugments.registries;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CombatantsAugmentsMod.MODID);


    public static final RegistryObject<Item> LONGBOW = registerItem("longbow", () -> new Longbow(new Item.Properties().durability(1200)));

    public static final RegistryObject<Item> GATE_POTTERY_SHERD = registerSimpleItem("gate_pottery_sherd");


    //WEAPONS -> Greataxe, Scythe, Naginata, Halberd, Rapier, Katana, Dagger
    public static final RegistryObject<Item> STONE_GREATAXE = registerItem("stone_greataxe", () -> new WeaponItem(Tiers.STONE,8,-3.6F,1.0F,-0.15F,true, false, new Item.Properties()));
    public static final RegistryObject<Item> IRON_GREATAXE = registerItem("iron_greataxe", () -> new WeaponItem(Tiers.IRON,8,-3.6F,1.0F,-0.15F,true, false, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_GREATAXE = registerItem("gold_greataxe", () -> new WeaponItem(Tiers.GOLD,8,-3.6F,1.0F,-0.15F,true, false, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_GREATAXE = registerItem("diamond_greataxe", () -> new WeaponItem(Tiers.DIAMOND,8,-3.6F,1.0F,-0.15F,true, false, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_GREATAXE = registerItem("netherite_greataxe", () -> new WeaponItem(Tiers.NETHERITE,8,-3.6F,1.0F,-0.15F,true, false, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_SCYTHE = registerItem("stone_scythe", () -> new WeaponItem(Tiers.STONE, 4, -2.8F, 1.5F, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SCYTHE = registerItem("iron_scythe", () -> new WeaponItem(Tiers.IRON, 4, -2.8F, 1.5F, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_SCYTHE = registerItem("gold_scythe", () -> new WeaponItem(Tiers.GOLD, 4, -2.8F, 1.5F, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SCYTHE = registerItem("diamond_scythe", () -> new WeaponItem(Tiers.DIAMOND, 4, -2.8F, 1.5F, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SCYTHE = registerItem("netherite_scythe", () -> new WeaponItem(Tiers.NETHERITE, 4, -2.8F, 1.5F, 0, false, true, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_NAGINATA = registerItem("stone_naginata", () -> new WeaponItem(Tiers.STONE, 5, -2.5F, 2.0F, 0, false, false, new Item.Properties()));
    public static final RegistryObject<Item> IRON_NAGINATA = registerItem("iron_naginata", () -> new WeaponItem(Tiers.IRON, 5, -2.5F, 2.0F, 0, false, false, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_NAGINATA = registerItem("gold_naginata", () -> new WeaponItem(Tiers.GOLD, 5, -2.5F, 2.0F, 0, false, false, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_NAGINATA = registerItem("diamond_naginata", () -> new WeaponItem(Tiers.DIAMOND, 5, -2.5F, 2.0F, 0, false, false, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_NAGINATA = registerItem("netherite_naginata", () -> new WeaponItem(Tiers.NETHERITE, 5, -2.5F, 2.0F, 0, false, false, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_RAPIER = registerItem("stone_rapier", () -> new WeaponItem(Tiers.STONE, 2, -2.0F, 0, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> IRON_RAPIER = registerItem("iron_rapier", () -> new WeaponItem(Tiers.IRON, 2, -2.0F, 0, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_RAPIER = registerItem("gold_rapier", () -> new WeaponItem(Tiers.GOLD, 2, -2.0F, 0, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_RAPIER = registerItem("diamond_rapier", () -> new WeaponItem(Tiers.DIAMOND, 2, -2.0F, 0, 0, false, true, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_RAPIER = registerItem("netherite_rapier", () -> new WeaponItem(Tiers.NETHERITE, 2, -2.0F, 0, 0, false, true, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_KATANA = registerItem("stone_katana", () -> new WeaponItem(Tiers.STONE, 4, -2.0F, -0.4F, 0.1F, false, false, new Item.Properties()));
    public static final RegistryObject<Item> IRON_KATANA = registerItem("iron_katana", () -> new WeaponItem(Tiers.IRON, 4, -2.0F, -0.4F, 0.1F, false, false, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_KATANA = registerItem("gold_katana", () -> new WeaponItem(Tiers.GOLD, 4, -2.0F, -0.4F, 0.1F, false, false, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_KATANA = registerItem("diamond_katana", () -> new WeaponItem(Tiers.DIAMOND, 4, -2.0F, -0.4F, 0.1F, false, false, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_KATANA = registerItem("netherite_katana", () -> new WeaponItem(Tiers.NETHERITE, 4, -2.0F, -0.4F, 0.1F, false, false, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_HALBERD = registerItem("stone_halberd", () -> new WeaponItem(Tiers.STONE, 5, -2.8F, 1.5F, -0.1F, true, false, new Item.Properties()));
    public static final RegistryObject<Item> IRON_HALBERD = registerItem("iron_halberd", () -> new WeaponItem(Tiers.IRON, 5, -2.8F, 1.5F, -0.1F, true, false, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_HALBERD = registerItem("gold_halberd", () -> new WeaponItem(Tiers.GOLD, 5, -2.8F, 1.5F, -0.1F, true, false, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HALBERD = registerItem("diamond_halberd", () -> new WeaponItem(Tiers.DIAMOND, 5, -2.8F, 1.5F, -0.1F, true, false, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_HALBERD = registerItem("netherite_halberd", () -> new WeaponItem(Tiers.NETHERITE, 5, -2.8F, 1.5F, -0.1F, true, false, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> STONE_DAGGER = registerItem("stone_dagger", () -> new DaggerItem(Tiers.STONE, 1, -1, -0.8F, 0.2F, false, true, new Item.Properties()));
    public static final RegistryObject<Item> IRON_DAGGER = registerItem("iron_dagger", () -> new DaggerItem(Tiers.IRON, 1, -1, -0.8F, 0.2F, false, true, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_DAGGER = registerItem("gold_dagger", () -> new DaggerItem(Tiers.GOLD, 1, -1, -0.8F, 0.2F, false, true, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_DAGGER = registerItem("diamond_dagger", () -> new DaggerItem(Tiers.DIAMOND, 1, -1, -0.8F, 0.2F, false, true, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_DAGGER = registerItem("netherite_dagger", () -> new DaggerItem(Tiers.NETHERITE, 1, -1, -0.8F, 0.2F, false, true, new Item.Properties().fireResistant()));






    public static final RegistryObject<Item> BANDAGE = registerItem("bandage", () -> new BandageItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> SIEGE_SHIELD = registerItem("siege_shield", SiegeShieldItem::new);

    public static final RegistryObject<Item> POLESTICK = registerSimpleItem("polestick");

    public static final RegistryObject<Item> LONGSTRING = registerSimpleItem("longstring");




    public static final RegistryObject<Item> WOOL_GOLEM_SPAWN_EGG = registerItem("wool_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypeRegistry.WOOL_GOLEM, 0xDCDED4, 0xF1F0E9 ,new Item.Properties()));
    public static final RegistryObject<Item> DUNGEON_GUARD_SPAWN_EGG = registerItem("dungeon_guard_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypeRegistry.DUNGEON_GUARD, 0x2c2c30, 0x64b0b3 ,new Item.Properties()));

    public static final RegistryObject<Item> YUKI_ONNA_SPAWN_EGG = registerItem("yuki_onna_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypeRegistry.YUKI_ONNA, 0xc7eced, 0xa0c5d9 ,new Item.Properties()));




    public static final RegistryObject<Item> SPELLBOOK = registerItem("spellbook", () -> new SpellBookItem(new Item.Properties()));



    public static final RegistryObject<Item> DUNGEON_KEY = registerSimpleItem("dungeon_key");

    public static final RegistryObject<Item> DUNGEON_GUARD_ARM = registerItem("dungeon_guard_arm",
            DungeonGuardArmItem::new);






    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item){
        RegistryObject<T> toReturn = ITEMS.register(name,item);
        return toReturn;
    }
    private static RegistryObject<Item> registerSimpleItem(String name){
        RegistryObject<Item> toReturn = ITEMS.register(name, () -> new Item(new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);

    }

}
