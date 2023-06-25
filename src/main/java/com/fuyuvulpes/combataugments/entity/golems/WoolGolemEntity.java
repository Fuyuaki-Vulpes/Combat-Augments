package com.fuyuvulpes.combataugments.entity.golems;

import com.fuyuvulpes.combataugments.registries.EntityTypeRegistry;
import com.fuyuvulpes.combataugments.registries.MobEffectRegistry;
import com.google.common.collect.Maps;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WoolGolemEntity extends TamableAnimal implements GeoEntity {

    private AnimatableInstanceCache animatableInstanceCache = new SingletonAnimatableInstanceCache(this);


    private static final EntityDataAccessor<Byte> DATA_WOOL_ID = SynchedEntityData.defineId(WoolGolemEntity.class, EntityDataSerializers.BYTE);

    private static final Map<DyeColor, float[]> COLORARRAY_BY_COLOR = Maps.<DyeColor, float[]>newEnumMap(Arrays.stream(DyeColor.values()).collect(Collectors.toMap((color) -> {
        return color;
    }, WoolGolemEntity::createGolemColor)));


    public WoolGolemEntity(EntityType<? extends WoolGolemEntity> pEntityType, Level pLevel) {
        super(EntityTypeRegistry.WOOL_GOLEM.get(), pLevel);
        this.setTame(false);
    }

    public WoolGolemEntity(Level level, Player player){
        super(EntityTypeRegistry.WOOL_GOLEM.get(), level);
        this.tame(player);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.3D, 8.0F, 2.0F, false));
        this.goalSelector.addGoal(5,new WaterAvoidingRandomStrollGoal(this,1.0D));
        this.goalSelector.addGoal(7, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(8, new MoveTowardsTargetGoal(this, 0.9D, 12.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Monster.class, false));



    }


    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80D)
                .add(Attributes.ATTACK_DAMAGE,8.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.18D)
                .add(Attributes.FOLLOW_RANGE,32.0D)
                .build();
    }


    @Override
    public boolean dampensVibrations() {
        return true;
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!(itemStack.getItem() instanceof DyeItem dyeItem)) {
            if (this.isOwnedBy(pPlayer)){
                this.setOrderedToSit(!this.isOrderedToSit());
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        } else {
            if (!pPlayer.level().isClientSide){
                this.setColor(dyeItem.getDyeColor());
                itemStack.shrink(1);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            }

        }
        return InteractionResult.PASS;

    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_WOOL_ID, (byte)0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("Color", (byte)this.getColor().getId());


    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setColor(DyeColor.byId(pCompound.getByte("Color")));

    }





    private static float[] createGolemColor(DyeColor p_29866_) {
        if (p_29866_ == DyeColor.WHITE) {
            return new float[]{0.9019608F, 0.9019608F, 0.9019608F};
        } else {
            float[] afloat = p_29866_.getTextureDiffuseColors();
            float f = 0.75F;
            return new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
        }
    }


    public DyeColor getColor() {
        return DyeColor.byId(this.entityData.get(DATA_WOOL_ID) & 15);
    }

    public void setColor(DyeColor pDyeColor) {
        byte b0 = this.entityData.get(DATA_WOOL_ID);
        this.entityData.set(DATA_WOOL_ID, (byte)(b0 & 240 | pDyeColor.getId() & 15));
    }

    @Override
    public void tick() {
        super.tick();
        AABB aabb = this.getBoundingBox().inflate(2).expandTowards(12,12,12);
        List<Player> playerList = this.level().getEntitiesOfClass(Player.class,aabb);
        for (Player player : playerList){
            player.addEffect(new MobEffectInstance(MobEffectRegistry.VIBRATION_OCCLUSION.get(),80,0,false,false,true));
        }

    }


    //GECKOLIB



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        if (isInSittingPose()){
            controllerRegistrar.add(new AnimationController<>(this,"sit_controller",5,this::sit_controller));
            return;
        }

        controllerRegistrar.add(new AnimationController<>(this,"controller",5,this::controller));

    }

    private <T extends GeoAnimatable> PlayState sit_controller(AnimationState<T> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("animation.wool_golem.off", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;

    }

    private <T extends GeoAnimatable> PlayState controller(AnimationState<T> animationState) {
        if (isInSittingPose()){
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.wool_golem.off", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if (animationState.isMoving()){
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.wool_golem.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }


        animationState.getController().setAnimation(RawAnimation.begin().then("animation.wool_golem.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }
}
