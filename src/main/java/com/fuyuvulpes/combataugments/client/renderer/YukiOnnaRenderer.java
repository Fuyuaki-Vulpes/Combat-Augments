package com.fuyuvulpes.combataugments.client.renderer;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.client.models.YukiOnnaEyes;
import com.fuyuvulpes.combataugments.client.models.YukiOnnaModel;
import com.fuyuvulpes.combataugments.entity.YukiOnnaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class YukiOnnaRenderer extends GeoEntityRenderer<YukiOnnaEntity> {
    public YukiOnnaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new YukiOnnaModel());
        addRenderLayer(new YukiOnnaEyes(this));
        shadowRadius = 0.5F;
    }


    @Override
    public ResourceLocation getTextureLocation(YukiOnnaEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/yuki_onna.png");
    }


}
