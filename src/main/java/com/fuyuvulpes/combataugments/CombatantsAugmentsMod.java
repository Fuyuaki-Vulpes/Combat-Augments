package com.fuyuvulpes.combataugments;

import com.fuyuvulpes.combataugments.client.renderer.DaggerRenderer;
import com.fuyuvulpes.combataugments.client.renderer.DungeonGuardRenderer;
import com.fuyuvulpes.combataugments.client.renderer.WoolGolemRenderer;
import com.fuyuvulpes.combataugments.client.renderer.YukiOnnaRenderer;
import com.fuyuvulpes.combataugments.registries.*;
import com.mojang.logging.LogUtils;
import dev.thomasglasser.sherdsapi.api.PotterySherdRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(CombatantsAugmentsMod.MODID)
public class CombatantsAugmentsMod
{    public static final String MODID = "combataugments";
    private static final Logger LOGGER = LogUtils.getLogger();
    public CombatantsAugmentsMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::enqueueIMC);

        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);

        CreativeModeTabRegistry.register(modEventBus);

        MobEffectRegistry.register(modEventBus);
        EntityTypeRegistry.register(modEventBus);



        MinecraftForge.EVENT_BUS.register(this);


        modEventBus.addListener(this::addCreative);
        postInit();


    }


    private void postInit(){
        PotterySherdRegistry.register(ItemRegistry.GATE_POTTERY_SHERD.getKey(), new ResourceLocation(MODID,"gate_pottery_pattern"));
    }



    private void commonSetup(final FMLCommonSetupEvent event){

    }




    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTab() == CreativeModeTabRegistry.COMBATAUGMENTS_TAB.get()) {
            event.accept(ItemRegistry.POLESTICK);
            event.accept(ItemRegistry.LONGSTRING);

            event.accept(ItemRegistry.BANDAGE);
            event.accept(ItemRegistry.DUNGEON_KEY);

            event.accept(ItemRegistry.LONGBOW);

            event.accept(ItemRegistry.SIEGE_SHIELD);

            event.accept(ItemRegistry.STONE_GREATAXE);
            event.accept(ItemRegistry.IRON_GREATAXE);
            event.accept(ItemRegistry.GOLD_GREATAXE);
            event.accept(ItemRegistry.DIAMOND_GREATAXE);
            event.accept(ItemRegistry.NETHERITE_GREATAXE);

            event.accept(ItemRegistry.STONE_SCYTHE);
            event.accept(ItemRegistry.IRON_SCYTHE);
            event.accept(ItemRegistry.GOLD_SCYTHE);
            event.accept(ItemRegistry.DIAMOND_SCYTHE);
            event.accept(ItemRegistry.NETHERITE_SCYTHE);

            event.accept(ItemRegistry.STONE_NAGINATA);
            event.accept(ItemRegistry.IRON_NAGINATA);
            event.accept(ItemRegistry.GOLD_NAGINATA);
            event.accept(ItemRegistry.DIAMOND_NAGINATA);
            event.accept(ItemRegistry.NETHERITE_NAGINATA);

            event.accept(ItemRegistry.STONE_HALBERD);
            event.accept(ItemRegistry.IRON_HALBERD);
            event.accept(ItemRegistry.GOLD_HALBERD);
            event.accept(ItemRegistry.DIAMOND_HALBERD);
            event.accept(ItemRegistry.NETHERITE_HALBERD);

            event.accept(ItemRegistry.STONE_RAPIER);
            event.accept(ItemRegistry.IRON_RAPIER);
            event.accept(ItemRegistry.GOLD_RAPIER);
            event.accept(ItemRegistry.DIAMOND_RAPIER);
            event.accept(ItemRegistry.NETHERITE_RAPIER);

            event.accept(ItemRegistry.STONE_KATANA);
            event.accept(ItemRegistry.IRON_KATANA);
            event.accept(ItemRegistry.GOLD_KATANA);
            event.accept(ItemRegistry.DIAMOND_KATANA);
            event.accept(ItemRegistry.NETHERITE_KATANA);

            event.accept(ItemRegistry.STONE_DAGGER);
            event.accept(ItemRegistry.IRON_DAGGER);
            event.accept(ItemRegistry.GOLD_DAGGER);
            event.accept(ItemRegistry.DIAMOND_DAGGER);
            event.accept(ItemRegistry.NETHERITE_DAGGER);

            event.accept(ItemRegistry.DUNGEON_GUARD_ARM);

            event.accept(ItemRegistry.SPELLBOOK);

            event.accept(ItemRegistry.SPELL_EXPLOSION);



            event.accept(BlockRegistry.EXPLOSIVE_BARREL);
            //event.accept(BlockRegistry.GOLEM_CORE);



            event.accept(BlockRegistry.DUNGEON_WALL_TILES);
            event.accept(BlockRegistry.DUNGEON_WALL_STAIRS);
            event.accept(BlockRegistry.DUNGEON_WALL_SLAB);
            event.accept(BlockRegistry.DUNGEON_WALL_WALL);

            event.accept(BlockRegistry.DUNGEON_FLOOR_TILES);
            event.accept(BlockRegistry.DUNGEON_FLOOR_STAIRS);
            event.accept(BlockRegistry.DUNGEON_FLOOR_SLAB);
            event.accept(BlockRegistry.DUNGEON_FLOOR_WALL);

            event.accept(BlockRegistry.DUNGEON_DISPENSER);
            event.accept(BlockRegistry.DUNGEON_SHATTERABLE_FLOOR);
            event.accept(BlockRegistry.DUNGEON_DOOR);



            event.accept(BlockRegistry.PAPER_DOOR.get());


            event.accept(BlockRegistry.SPIKES);
            event.accept(BlockRegistry.POISON_SPIKES);

            event.accept(ItemRegistry.GATE_POTTERY_SHERD);

            event.accept(ItemRegistry.WOOL_GOLEM_SPAWN_EGG);
            event.accept(ItemRegistry.YUKI_ONNA_SPAWN_EGG);

            if (event.hasPermissions()){
                event.accept(ItemRegistry.DUNGEON_GUARD_SPAWN_EGG);

            }

        }

    }

    public void enqueueIMC(final InterModEnqueueEvent event) {

    }


        @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event){
    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

            EntityRenderers.register(EntityTypeRegistry.DAGGER.get(), DaggerRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.WOOL_GOLEM.get(), WoolGolemRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.DUNGEON_GUARD.get(), DungeonGuardRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.YUKI_ONNA.get(), YukiOnnaRenderer::new);

            event.enqueueWork(() ->
            {
                ItemProperties.register(ItemRegistry.LONGBOW.get(),
                        new ResourceLocation(CombatantsAugmentsMod.MODID, "pulling"), (stack, level, living, id) -> {
                            return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
                        });

                ItemProperties.register(ItemRegistry.LONGBOW.get(),
                        new ResourceLocation(CombatantsAugmentsMod.MODID,"pull"),(stack,level,living,id) -> {
                            if (living == null) {
                                return 0.0F;
                            } else {
                                return living.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - living.getUseItemRemainingTicks()) / 20.0F;
                            }
                        });
                ItemProperties.register(ItemRegistry.SIEGE_SHIELD.get(),
                        new ResourceLocation(CombatantsAugmentsMod.MODID,"blocking"),(stack, level, living, id) -> {
                            return living != null && living.isUsingItem() && living.getUseItem() == stack ? 1.0F : 0.0F;
                });
            });
        }
    }
}
