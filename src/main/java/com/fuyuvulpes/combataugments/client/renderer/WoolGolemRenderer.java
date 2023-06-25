package com.fuyuvulpes.combataugments.client.renderer;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.client.models.WoolGolemModel;
import com.fuyuvulpes.combataugments.client.models.WoolGolemWoolLayer;
import com.fuyuvulpes.combataugments.entity.golems.WoolGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WoolGolemRenderer extends GeoEntityRenderer<WoolGolemEntity> {
    public WoolGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WoolGolemModel());
        addRenderLayer(new WoolGolemWoolLayer(this));
        shadowRadius = 0.7f;

    }


    @Override
    public ResourceLocation getTextureLocation(WoolGolemEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/wool_golem/wool_golem.png");
    }

    @Override
    public void render(WoolGolemEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {



        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

