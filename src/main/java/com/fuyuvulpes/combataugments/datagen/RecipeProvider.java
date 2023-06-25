package com.fuyuvulpes.combataugments.datagen;

import com.fuyuvulpes.combataugments.registries.BlockRegistry;
import com.fuyuvulpes.combataugments.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider implements IConditionBuilder {
    public RecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.LONGSTRING.get(),2).requires(Items.STRING,3)
                .unlockedBy("has_string",has(Items.STRING))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.POLESTICK.get()).requires(Items.STICK,3).requires(Items.STRING)
                .unlockedBy("has_sticks",has(Items.STICK))
                .save(consumer);

        wall(consumer,RecipeCategory.BUILDING_BLOCKS,BlockRegistry.DUNGEON_FLOOR_WALL.get(),BlockRegistry.DUNGEON_FLOOR_TILES.get());

        wall(consumer,RecipeCategory.BUILDING_BLOCKS,BlockRegistry.DUNGEON_WALL_WALL.get(),BlockRegistry.DUNGEON_WALL_TILES.get());

        stairBuilder(BlockRegistry.DUNGEON_FLOOR_STAIRS.get(), Ingredient.of(BlockRegistry.DUNGEON_FLOOR_TILES.get()))
                .unlockedBy("has_floor_tiles",has(BlockRegistry.DUNGEON_FLOOR_TILES.get())).save(consumer);
        stairBuilder(BlockRegistry.DUNGEON_WALL_STAIRS.get(), Ingredient.of(BlockRegistry.DUNGEON_WALL_TILES.get()))
                .unlockedBy("has_wall_tiles",has(BlockRegistry.DUNGEON_WALL_TILES.get())).save(consumer);

        slab(consumer, RecipeCategory.BUILDING_BLOCKS, BlockRegistry.DUNGEON_FLOOR_SLAB.get(), BlockRegistry.DUNGEON_FLOOR_TILES.get());
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, BlockRegistry.DUNGEON_WALL_SLAB.get(), BlockRegistry.DUNGEON_WALL_TILES.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BANDAGE.get(),4).define('W', ItemTags.WOOL).define('S', Items.STRING)
                .pattern(" W ")
                .pattern("WSW")
                .pattern(" W ")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.SIEGE_SHIELD.get())
                .define('D', Blocks.COBBLED_DEEPSLATE).define('I', Items.IRON_INGOT).define('G', Items.GOLD_NUGGET)
                .pattern("IGI")
                .pattern("IDI")
                .pattern("DDD")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE,BlockRegistry.PAPER_DOOR.get(),3)
                .define('C', Items.CHERRY_PLANKS).define('P', Items.PAPER)
                .pattern("CP")
                .pattern("PP")
                .pattern("PC")
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(consumer);
/*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockRegistry.GOLEM_CORE.get(),2)
                .define('I', Blocks.IRON_BLOCK).define('D', Items.DIAMOND).define('G', Items.GOLD_NUGGET)
                .pattern("GDG")
                .pattern("DID")
                .pattern("GDG")
                .unlockedBy("has_diamonds",has(Items.DIAMOND))
                .save(consumer);
*/

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, BlockRegistry.EXPLOSIVE_BARREL.get(),2)
                .requires(Items.BARREL).requires(Items.TNT)
                .unlockedBy("has_barrel",has(Items.BARREL))
                .unlockedBy("has_tnt",has(Items.TNT))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ItemRegistry.LONGBOW.get())
                .define('S', ItemRegistry.LONGSTRING.get()).define('P', ItemRegistry.POLESTICK.get()).define('B',Items.STICK)
                .pattern(" BS")
                .pattern("P S")
                .pattern(" BS")
                .unlockedBy("has_sticks",has(Items.STICK))
                .save(consumer);




        //WEAPON RECIPES

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_GREATAXE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', ItemTags.STONE_TOOL_MATERIALS)
                .pattern("III")
                .pattern("III")
                .pattern(" P ")
                .unlockedBy("has_stone_material", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_SCYTHE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', ItemTags.STONE_TOOL_MATERIALS)
                .pattern("III")
                .pattern("IP ")
                .unlockedBy("has_stone_material", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_NAGINATA.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', ItemTags.STONE_TOOL_MATERIALS)
                .pattern("I")
                .pattern("I")
                .pattern("P")
                .unlockedBy("has_stone_material", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_HALBERD.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', ItemTags.STONE_TOOL_MATERIALS)
                .pattern("II ")
                .pattern("I I")
                .pattern(" P ")
                .unlockedBy("has_stone_material", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_RAPIER.get())
                .define('P', Items.STICK).define('I', ItemTags.STONE_TOOL_MATERIALS)
                .define('S', Items.STRING)
                .pattern(" I")
                .pattern("SI")
                .pattern(" P")
                .unlockedBy("has_stone_material", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_KATANA.get())
                .define('P', Items.STICK).define('I', ItemTags.STONE_TOOL_MATERIALS)
                .pattern("  I")
                .pattern(" I ")
                .pattern("P  ")
                .unlockedBy("has_stone_material", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.STONE_DAGGER.get())
                .define('P', Items.STICK).define('I', ItemTags.STONE_TOOL_MATERIALS)
                .pattern(" I")
                .pattern("PI")
                .unlockedBy("has_stone_material", has(ItemTags.STONE_TOOL_MATERIALS))
                .save(consumer);


        //IRON #######################################


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.IRON_GREATAXE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("III")
                .pattern(" P ")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.IRON_SCYTHE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IP ")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.IRON_NAGINATA.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.IRON_INGOT)
                .pattern("I")
                .pattern("I")
                .pattern("P")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.IRON_HALBERD.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.IRON_INGOT)
                .pattern("II ")
                .pattern("I I")
                .pattern(" P ")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.IRON_RAPIER.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.IRON_INGOT)
                .define('S', Items.STRING)
                .pattern(" I")
                .pattern("SI")
                .pattern(" P")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.IRON_KATANA.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.IRON_INGOT)
                .pattern("  I")
                .pattern(" I ")
                .pattern("P  ")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.IRON_DAGGER.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.IRON_INGOT)
                .pattern(" I")
                .pattern("PI")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);


        //GOLD #######################################


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLD_GREATAXE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.GOLD_INGOT)
                .pattern("III")
                .pattern("III")
                .pattern(" P ")
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLD_SCYTHE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.GOLD_INGOT)
                .pattern("III")
                .pattern("IP ")
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLD_NAGINATA.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.GOLD_INGOT)
                .pattern("I")
                .pattern("I")
                .pattern("P")
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLD_HALBERD.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.GOLD_INGOT)
                .pattern("II ")
                .pattern("I I")
                .pattern(" P ")
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLD_RAPIER.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.GOLD_INGOT)
                .define('S', Items.STRING)
                .pattern(" I")
                .pattern("SI")
                .pattern(" P")
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLD_KATANA.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.GOLD_INGOT)
                .pattern("  I")
                .pattern(" I ")
                .pattern("P  ")
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.GOLD_DAGGER.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.GOLD_INGOT)
                .pattern(" I")
                .pattern("PI")
                .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                .save(consumer);






        //DIAMOND ##################################################








        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DIAMOND_GREATAXE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.DIAMOND)
                .pattern("III")
                .pattern("III")
                .pattern(" P ")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DIAMOND_SCYTHE.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.DIAMOND)
                .pattern("III")
                .pattern("IP ")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DIAMOND_NAGINATA.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.DIAMOND)
                .pattern("I")
                .pattern("I")
                .pattern("P")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DIAMOND_HALBERD.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.DIAMOND)
                .pattern("II ")
                .pattern("I I")
                .pattern(" P ")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DIAMOND_RAPIER.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.DIAMOND)
                .define('S', Items.STRING)
                .pattern(" I")
                .pattern("SI")
                .pattern(" P")
                .unlockedBy("has_diamond", has(Items.GOLD_INGOT))
                .save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DIAMOND_KATANA.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.DIAMOND)
                .pattern("  I")
                .pattern(" I ")
                .pattern("P  ")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);




        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemRegistry.DIAMOND_DAGGER.get())
                .define('P', ItemRegistry.POLESTICK.get()).define('I', Items.DIAMOND)
                .pattern(" I")
                .pattern("PI")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer);



        netheriteSmithing(consumer, ItemRegistry.DIAMOND_GREATAXE.get(),RecipeCategory.COMBAT, ItemRegistry.NETHERITE_GREATAXE.get());
        netheriteSmithing(consumer, ItemRegistry.DIAMOND_SCYTHE.get(),RecipeCategory.COMBAT, ItemRegistry.NETHERITE_SCYTHE.get());
        netheriteSmithing(consumer, ItemRegistry.DIAMOND_NAGINATA.get(),RecipeCategory.COMBAT, ItemRegistry.NETHERITE_NAGINATA.get());
        netheriteSmithing(consumer, ItemRegistry.DIAMOND_HALBERD.get(),RecipeCategory.COMBAT, ItemRegistry.NETHERITE_HALBERD.get());
        netheriteSmithing(consumer, ItemRegistry.DIAMOND_RAPIER.get(),RecipeCategory.COMBAT, ItemRegistry.NETHERITE_RAPIER.get());
        netheriteSmithing(consumer, ItemRegistry.DIAMOND_KATANA.get(),RecipeCategory.COMBAT, ItemRegistry.NETHERITE_KATANA.get());
        netheriteSmithing(consumer, ItemRegistry.DIAMOND_DAGGER.get(),RecipeCategory.COMBAT, ItemRegistry.NETHERITE_DAGGER.get());


    }
}
