package com.fuyuvulpes.combataugments.registries;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.CHERRY_PLANKS;
import static net.minecraft.world.level.block.Blocks.COBBLED_DEEPSLATE;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,  CombatantsAugmentsMod.MODID);


    public static final RegistryObject<Block> EXPLOSIVE_BARREL = registerBlock("explosive_barrel", () -> new ExplosiveBarrelBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).strength(2.5F).sound(SoundType.WOOD)));



    public static final RegistryObject<Block> GOLEM_CORE = registerBlock("golem_core", () -> new GolemCoreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(4.0F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()
            .lightLevel(f -> {return 15;}).emissiveRendering((pState, pLevel, pPos) -> {return true;})));




    public static final RegistryObject<Block> PAPER_DOOR = registerBlock("paper_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(CHERRY_PLANKS).strength(3.0F).noOcclusion(), BlockSetType.CHERRY));


    public static final RegistryObject<Block> DUNGEON_WALL_TILES = registerBlock("dungeon_wall_tiles", () -> new Block(BlockBehaviour.Properties.copy(COBBLED_DEEPSLATE).sound(SoundType.DEEPSLATE_TILES).strength(50.0F, 1200.0F)));

    public static final RegistryObject<Block> DUNGEON_WALL_STAIRS = registerBlock("dungeon_wall_stairs", () -> new StairBlock(DUNGEON_WALL_TILES.get()::defaultBlockState,BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> DUNGEON_WALL_SLAB = registerBlock("dungeon_wall_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> DUNGEON_WALL_WALL = registerBlock("dungeon_wall_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> DUNGEON_FLOOR_TILES = registerBlock("dungeon_floor_tiles", () -> new Block(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> DUNGEON_FLOOR_STAIRS = registerBlock("dungeon_floor_stairs", () -> new StairBlock(DUNGEON_FLOOR_TILES.get()::defaultBlockState,BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> DUNGEON_FLOOR_SLAB = registerBlock("dungeon_floor_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> DUNGEON_FLOOR_WALL = registerBlock("dungeon_floor_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> DUNGEON_SHATTERABLE_FLOOR = registerBlock("dungeon_shatterable_floor", () -> new ShatterableBlock(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get()).strength(1.2F)));

    public static final RegistryObject<Block> DUNGEON_DISPENSER = registerBlock("dungeon_dispenser", () -> new DispenserBlock(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get())));

    public static final RegistryObject<Block> SPIKES = registerBlock("spikes", () -> new SpikesBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> POISON_SPIKES = registerBlock("poison_spikes", () -> new PoisonSpikesBlock(BlockBehaviour.Properties.copy(SPIKES.get())));


    public static final RegistryObject<Block> DUNGEON_DOOR = registerBlock("dungeon_door", () -> new DungeonDoorBlock(BlockBehaviour.Properties.copy(DUNGEON_WALL_TILES.get()), BlockSetType.POLISHED_BLACKSTONE));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }


}
