package com.fuyuvulpes.combataugments.client.models;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.YukiOnnaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class YukiOnnaEyes extends GeoRenderLayer<YukiOnnaEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/yuki_onna_eyes.png");

    public YukiOnnaEyes(GeoRenderer<YukiOnnaEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, YukiOnnaEntity animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType renderType1 = RenderType.eyes(TEXTURE);


        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, renderType1,
                bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }
}
