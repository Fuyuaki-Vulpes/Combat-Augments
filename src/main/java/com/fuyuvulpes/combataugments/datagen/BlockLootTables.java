package com.fuyuvulpes.combataugments.datagen;

import com.fuyuvulpes.combataugments.registries.BlockRegistry;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
    protected BlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(BlockRegistry.EXPLOSIVE_BARREL.get());
        //dropSelf(BlockRegistry.GOLEM_CORE.get());
        dropSelf(BlockRegistry.DUNGEON_DISPENSER.get());
        dropSelf(BlockRegistry.DUNGEON_WALL_TILES.get());
        dropSelf(BlockRegistry.DUNGEON_FLOOR_TILES.get());
        dropSelf(BlockRegistry.SPIKES.get());
        dropSelf(BlockRegistry.POISON_SPIKES.get());
        dropSelf(BlockRegistry.DUNGEON_FLOOR_SLAB.get());
        dropSelf(BlockRegistry.DUNGEON_FLOOR_WALL.get());
        dropSelf(BlockRegistry.DUNGEON_FLOOR_STAIRS.get());
        dropSelf(BlockRegistry.DUNGEON_WALL_SLAB.get());
        dropSelf(BlockRegistry.DUNGEON_WALL_WALL.get());
        dropSelf(BlockRegistry.DUNGEON_WALL_STAIRS.get());

        add(BlockRegistry.DUNGEON_DOOR.get(), (block) -> {
            return createDoorTable(block);
        });

        add(BlockRegistry.DUNGEON_SHATTERABLE_FLOOR.get(), noDrop());

        add(BlockRegistry.PAPER_DOOR.get(), (block) -> {
            return createDoorTable(block);
        });

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
