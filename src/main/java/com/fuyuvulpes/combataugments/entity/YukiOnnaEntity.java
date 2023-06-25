package com.fuyuvulpes.combataugments.entity;

import com.fuyuvulpes.combataugments.registries.EntityTypeRegistry;
import com.fuyuvulpes.combataugments.registries.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class YukiOnnaEntity extends Monster implements GeoEntity {

    private AnimatableInstanceCache animatableInstanceCache = new SingletonAnimatableInstanceCache(this);
    private final RawAnimation SWING = RawAnimation.begin().then("yukionna.swing", Animation.LoopType.PLAY_ONCE);
    private final RawAnimation SEEK = RawAnimation.begin().then("yukionna.seek", Animation.LoopType.LOOP);
    private final RawAnimation IDLE = RawAnimation.begin().then("yukionna.living", Animation.LoopType.LOOP);
    public YukiOnnaEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(EntityTypeRegistry.YUKI_ONNA.get(), pLevel);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = 5;

    }


    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this,1.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 40.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, false, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, false, false));
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.ATTACK_DAMAGE,5.0F)
                .add(Attributes.FOLLOW_RANGE,24.0D)
                .add(Attributes.FLYING_SPEED,0.6F)
                .add(Attributes.MOVEMENT_SPEED,0.2F)
                .build();
    }

    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlot.HEAD);
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag && !this.level().getBiome(this.blockPosition()).is(Tags.Biomes.IS_SNOWY)) {
                    this.setSecondsOnFire(4);
                }
            }
        }

        super.aiStep();
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if (pEntity instanceof LivingEntity living && living.canFreeze()){
            living.setTicksFrozen(living.getTicksFrozen() + 100);
        }
        return super.doHurtTarget(pEntity);
    }


    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean canSpawnSoulSpeedParticle() {
        return true;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,"baseAnim",5,animationState -> {
            if (this.getHealth() > 0.01){
                animationState.setAndContinue(animationState.isMoving() && getTarget() != null ? SEEK : IDLE);
            }
            return PlayState.CONTINUE;
        }));

        controllerRegistrar.add(new AnimationController<>(this,"swingAnim",0, animationState -> {
            if (this.swinging){
                animationState.setAndContinue(SWING);
                return PlayState.CONTINUE;
            }
            animationState.getController().forceAnimationReset();

            return PlayState.STOP;
        }));

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ALLAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ALLAY_DEATH;
    }
}
