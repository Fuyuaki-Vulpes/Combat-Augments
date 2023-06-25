package com.fuyuvulpes.combataugments.client.renderer;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.client.models.DungeonGuardEmissive;
import com.fuyuvulpes.combataugments.client.models.DungeonGuardModel;
import com.fuyuvulpes.combataugments.entity.DungeonGuardEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DungeonGuardRenderer extends GeoEntityRenderer<DungeonGuardEntity> {
    public DungeonGuardRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DungeonGuardModel());

        addRenderLayer(new DungeonGuardEmissive(this));
        shadowRadius = 2.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(DungeonGuardEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/dungeon_guard/dungeon_guard.png");

    }

    @Override
    public void render(DungeonGuardEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(DungeonGuardEntity animatable) {
        return 0.0F;
    }

}

