package com.fuyuvulpes.combataugments.client.models;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.golems.WoolGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WoolGolemModel extends GeoModel<WoolGolemEntity> {
    @Override
    public ResourceLocation getModelResource(WoolGolemEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"geo/wool_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WoolGolemEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/wool_golem/wool_golem.png");
    }


    @Override
    public ResourceLocation getAnimationResource(WoolGolemEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"animations/wool_golem.animation.json");
    }

    @Override
    public void setCustomAnimations(WoolGolemEntity animatable, long instanceId, AnimationState<WoolGolemEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
