package com.fuyuvulpes.combataugments.client.models;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.YukiOnnaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class YukiOnnaModel extends GeoModel<YukiOnnaEntity> {
    @Override
    public ResourceLocation getModelResource(YukiOnnaEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"geo/yukionna.geo.json");

    }

    @Override
    public ResourceLocation getTextureResource(YukiOnnaEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/yuki_onna.png");
    }

    @Override
    public ResourceLocation getAnimationResource(YukiOnnaEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"animations/yukionna.animation.json");

    }



    @Override
    public void setCustomAnimations(YukiOnnaEntity animatable, long instanceId, AnimationState<YukiOnnaEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
