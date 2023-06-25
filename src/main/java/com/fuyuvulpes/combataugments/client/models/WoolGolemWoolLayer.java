package com.fuyuvulpes.combataugments.client.models;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.golems.WoolGolemEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class WoolGolemWoolLayer extends GeoRenderLayer<WoolGolemEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/wool_golem/wool_golem_wool.png");

    public WoolGolemWoolLayer(GeoRenderer<WoolGolemEntity> entityRendererIn) {
        super(entityRendererIn);

    }

    @Override
    public void render(PoseStack poseStack, WoolGolemEntity animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType woolRenderType = RenderType.entityCutoutNoCull(TEXTURE);

        float[] afloat = Sheep.getColorArray(animatable.getColor());
        float f = afloat[0];
        float f1 = afloat[1];
        float f2 = afloat[2];

        getRenderer().reRender(getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, woolRenderType,
                bufferSource.getBuffer(woolRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                f, f1, f2, 1);

    }
}
