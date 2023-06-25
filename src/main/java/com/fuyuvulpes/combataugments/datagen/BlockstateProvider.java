package com.fuyuvulpes.combataugments.datagen;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.registries.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockstateProvider extends BlockStateProvider {
    public BlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CombatantsAugmentsMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
       // blockWithItem(BlockRegistry.GOLEM_CORE);
        blockWithItem(BlockRegistry.DUNGEON_WALL_TILES);
        blockWithItem(BlockRegistry.DUNGEON_FLOOR_TILES);
        simpleDoor(BlockRegistry.PAPER_DOOR);
        simpleDoor(BlockRegistry.DUNGEON_DOOR);
        stairsBlock((StairBlock) BlockRegistry.DUNGEON_WALL_STAIRS.get(), new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_wall_tiles"));
        stairsBlock((StairBlock) BlockRegistry.DUNGEON_FLOOR_STAIRS.get(), new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_floor_tiles"));
        slabBlock((SlabBlock) BlockRegistry.DUNGEON_WALL_SLAB.get(), new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_wall_tiles"),new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_wall_tiles"));
        slabBlock((SlabBlock) BlockRegistry.DUNGEON_FLOOR_SLAB.get(), new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_floor_tiles"), new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_floor_tiles"));
        wallBlock((WallBlock) BlockRegistry.DUNGEON_WALL_WALL.get(), new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_wall_tiles"));
        wallBlock((WallBlock) BlockRegistry.DUNGEON_FLOOR_WALL.get(), new ResourceLocation(CombatantsAugmentsMod.MODID,"block/dungeon_floor_tiles"));
        blockWithItem(BlockRegistry.DUNGEON_SHATTERABLE_FLOOR);


    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void simpleDoor(RegistryObject<Block> door){
        String baseName = key(door.get()).getPath();
        ResourceLocation bottom = new ResourceLocation(CombatantsAugmentsMod.MODID,"block/" + baseName + "_bottom");
        ResourceLocation top = new ResourceLocation(CombatantsAugmentsMod.MODID,"block/" + baseName + "_top");

        ModelFile bottomLeft = models().doorBottomLeft(baseName + "_bottom_left", bottom, top);
        ModelFile bottomLeftOpen = models().doorBottomLeftOpen(baseName + "_bottom_left_open", bottom, top);
        ModelFile bottomRight = models().doorBottomRight(baseName + "_bottom_right", bottom, top);
        ModelFile bottomRightOpen = models().doorBottomRightOpen(baseName + "_bottom_right_open", bottom, top);
        ModelFile topLeft = models().doorTopLeft(baseName + "_top_left", bottom, top);
        ModelFile topLeftOpen = models().doorTopLeftOpen(baseName + "_top_left_open", bottom, top);
        ModelFile topRight = models().doorTopRight(baseName + "_top_right", bottom, top);
        ModelFile topRightOpen = models().doorTopRightOpen(baseName + "_top_right_open", bottom, top);
        doorBlock((DoorBlock) door.get(), bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
        customTextureBlockItem(door);
    }
    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void customTextureBlockItem(RegistryObject<Block> block){
        itemModels().basicItem(block.getId());
    }
}
