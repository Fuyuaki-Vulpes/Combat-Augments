package com.fuyuvulpes.combataugments.client.models;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.entity.DungeonGuardEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DungeonGuardModel extends GeoModel<DungeonGuardEntity> {
    @Override
    public ResourceLocation getModelResource(DungeonGuardEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"geo/dungeon_guard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DungeonGuardEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"textures/entity/dungeon_guard/dungeon_guard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DungeonGuardEntity animatable) {
        return new ResourceLocation(CombatantsAugmentsMod.MODID,"animations/dungeon_guard.animation.json");

    }

    @Override
    public void setCustomAnimations(DungeonGuardEntity animatable, long instanceId, AnimationState<DungeonGuardEntity> animationState) {
        CoreGeoBone neck = getAnimationProcessor().getBone("neck_b");
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (neck != null && head != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            neck.setRotX((float) ((entityData.headPitch() -27.5)* Mth.DEG_TO_RAD));
            head.setRotY(entityData.netHeadYaw()* Mth.DEG_TO_RAD);
        }
    }
}
