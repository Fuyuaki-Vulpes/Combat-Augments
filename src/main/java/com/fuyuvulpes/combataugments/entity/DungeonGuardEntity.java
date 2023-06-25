package com.fuyuvulpes.combataugments.entity;

import com.fuyuvulpes.combataugments.registries.EntityTypeRegistry;
import com.fuyuvulpes.combataugments.registries.ItemRegistry;
import com.fuyuvulpes.combataugments.registries.MobEffectRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.slf4j.Logger;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class DungeonGuardEntity extends Monster implements RangedAttackMob, GeoEntity {

    private static final Logger LOGGER = LogUtils.getLogger();

    private AnimatableInstanceCache animatableInstanceCache = new SingletonAnimatableInstanceCache(this);

    private boolean shooting;
    private boolean pounding;

    private int poundTime;
    private int poundCooldown;



    private final RawAnimation ATTACK = RawAnimation.begin().then("animation.dungeon_guard.swing", Animation.LoopType.PLAY_ONCE);
    private final RawAnimation WALK = RawAnimation.begin().then("animation.dungeon_guard.walk", Animation.LoopType.LOOP);
    private final RawAnimation IDLE = RawAnimation.begin().then("animation.dungeon_guard.base", Animation.LoopType.LOOP);
    private final RawAnimation POUND = RawAnimation.begin().then("animation.dungeon_guard.pound", Animation.LoopType.PLAY_ONCE);
    private final RawAnimation BARRAGE = RawAnimation.begin().then("animation.dungeon_guard.barrage", Animation.LoopType.PLAY_ONCE);
    private final RawAnimation DEATH = RawAnimation.begin().then("animation.dungeon_guard.death", Animation.LoopType.HOLD_ON_LAST_FRAME);


    public DungeonGuardEntity(EntityType<? extends DungeonGuardEntity> pEntityType, Level pLevel) {
        super(EntityTypeRegistry.DUNGEON_GUARD.get(), pLevel);
        this.xpReward = 200;

        this.poundTime = 0;
        this.pounding = false;
        this.shooting = false;


    }


    public boolean attacking = pounding || shooting || swinging;


    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150D)
                .add(Attributes.ATTACK_DAMAGE,9.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.KNOCKBACK_RESISTANCE,1.0D)
                .add(Attributes.ARMOR,4.0D)
                .add(Attributes.ARMOR_TOUGHNESS,1.0D)
                .build();
    }



    @Override
    protected void updateSwingTime() {
        int i = 8;
        if (this.swinging) {
            ++this.swingTime;
            if (this.swingTime >= i) {
                this.swingTime = 0;
                this.swinging = false;
            }
        } else {
            this.swingTime = 0;
        }

        this.attackAnim = (float)this.swingTime / (float)i;
    }

    protected void pound(){
        if ((!this.pounding || this.poundTime >= 30 || this.poundTime < 0) && poundCooldown == 0) {
            this.poundTime = -1;
            this.pounding = true;
        }
    }

    protected void updatePoundTime(){
        int i = 30;
        if (this.pounding){
            System.out.println(poundTime);
            ++this.poundTime;
            if (this.poundTime >= i){
                this.poundTime = 0;
                this.pounding = false;
            }
        }
    }




    //GOAL REGISTRY

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new DungeonGuardPoundGoal(this,10));
        this.goalSelector.addGoal(4, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this,1.0F));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, false));

    }


    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    protected void customServerAiStep() {
        if (this.poundCooldown > 0){
            poundCooldown--;
        }
        this.updatePoundTime();
        super.customServerAiStep();

    }


    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime >= 50 && !this.level().isClientSide() && !this.isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    public void die(DamageSource pDamageSource) {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, pDamageSource)) return;
        if (!this.isRemoved() && !this.dead) {
            Entity entity = pDamageSource.getEntity();
            LivingEntity livingentity = this.getKillCredit();
            if (this.deathScore >= 0 && livingentity != null) {
                livingentity.awardKillScore(this, this.deathScore, pDamageSource);
            }

            if (!this.level().isClientSide && this.hasCustomName()) {
                LOGGER.info("Named entity {} died: {}", this, this.getCombatTracker().getDeathMessage().getString());
            }

            this.dead = true;
            this.getCombatTracker().recheckStatus();
            if (this.level() instanceof ServerLevel) {
                if (entity == null || entity.killedEntity((ServerLevel)this.level(), this)) {
                    this.gameEvent(GameEvent.ENTITY_DIE);
                    this.dropAllDeathLoot(pDamageSource);
                    this.createWitherRose(livingentity);
                }

                this.level().broadcastEntityEvent(this, (byte)3);
            }

        }
    }

    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        ItemEntity itementity = this.spawnAtLocation(ItemRegistry.DUNGEON_GUARD_ARM.get());
        if (itementity != null) {
            itementity.setExtendedLifetime();
        }

    }


    public void checkDespawn() {
    }


    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }


    @Override
    public boolean canDisableShield() {
        return true;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance pEffectInstance) {
        MobEffect mobeffect = pEffectInstance.getEffect();

        return mobeffect != MobEffects.POISON && mobeffect != MobEffects.WITHER && mobeffect != MobEffectRegistry.BLEEDING.get();
    }

    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    protected boolean canRide(Entity pVehicle) {
        return false;
    }






    //GECKOLIB ANIM STUFF



    AnimationController<DungeonGuardEntity> controller;
    AnimationController<DungeonGuardEntity> moveController;


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,"deathAnim", 0, animationState -> {
            if (this.isDeadOrDying() || this.getHealth() < 0.01) {
                animationState.setAndContinue(DEATH);
                return PlayState.CONTINUE;
            }
            return PlayState.CONTINUE;
        }));
        controller = new AnimationController<>(this, "attackController", 1, this::attackPredicate);
        moveController = new AnimationController<>(this, "moveController", 1, this::movePredicate);
        controllerRegistrar.add(controller);
        controllerRegistrar.add(moveController);


    }

    private <T extends GeoAnimatable> PlayState movePredicate(AnimationState<T> animationState) {
        if (this.attacking){
            return PlayState.STOP;
        }
        else if (this.getHealth() > 0.01){
            animationState.setAndContinue(animationState.isMoving()? WALK : IDLE);
            return PlayState.CONTINUE;

        }
        return PlayState.STOP;
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(AnimationState<T> animationState) {
        if (this.pounding || (poundTime >  0 && poundTime < 28)){
            animationState.getController().setAnimation(POUND);
            return PlayState.CONTINUE;

        }else if (this.shooting){
            animationState.getController().setAnimation(BARRAGE);

            return PlayState.CONTINUE;

        }else if (this.swinging || (swingTime > 0 && swingTime <8)){
            animationState.getController().setAnimation(ATTACK);
            return PlayState.CONTINUE;

        }

        return PlayState.STOP;

    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }











    //GOALS

    protected class DungeonGuardPoundGoal extends Goal{
        DungeonGuardEntity dungeonGuard = DungeonGuardEntity.this;

        int range;

        public DungeonGuardPoundGoal(DungeonGuardEntity dungeonGuard, int range) {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            this.range = range;
        }


        List<LivingEntity> targetable;



        @Override
        public boolean canUse() {
            LivingEntity livingentity = DungeonGuardEntity.this.getTarget();
            if (livingentity != null && livingentity.isAlive() && livingentity.distanceToSqr(DungeonGuardEntity.this) > 5) {
                return DungeonGuardEntity.this.level().getDifficulty() != Difficulty.PEACEFUL && DungeonGuardEntity.this.poundCooldown <=0;
            } else {
                return false;
            }
        }

        @Override
        public void start() {

            dungeonGuard.pound();
            super.start();
        }

        @Override
        public void tick() {
            Entity target = dungeonGuard.getTarget();
            AABB impactZone = dungeonGuard.getBoundingBox().inflate(range);
            targetable  = DungeonGuardEntity.this.level().getEntitiesOfClass(LivingEntity.class, impactZone);
            if (poundTime == 22 && target != null){
                targetable.forEach((entity -> {
                    if (!(entity instanceof DungeonGuardEntity)){
                        float distance = dungeonGuard.distanceTo(entity);
                        Vec3 range = (entity.position().subtract(dungeonGuard.position()));
                        Vec3 range2 = new Vec3(Math.min((1 / Math.sqrt(distance) + 1) * range.x, 2), Math.min(3 + (1 / distance + 1) * range.y, 4), Math.min((1 / Math.sqrt(distance) + 1) * range.z, 2)).normalize().multiply(2, 2, 2);
                        entity.setDeltaMovement(range2.add(0, 2, 0));
                        entity.hurt(level().damageSources().mobAttack(dungeonGuard), 10);
                    }
                }));

                float distance = dungeonGuard.distanceTo(target);
                Vec3 range = (target.position().subtract(dungeonGuard.position()));
                Vec3 range2 = new Vec3(Math.min((1/Math.sqrt(distance) + 1 ) * range.x,2),Math.min(3 + (1/distance + 1 ) * range.y,4),Math.min((1/Math.sqrt(distance) + 1 ) * range.z,2)).normalize().multiply(2,2,2);
                target.setDeltaMovement(range2.add(0,2,0));
                target.hurt(level().damageSources().mobAttack(dungeonGuard), 10);


            }
            if (poundTime >= 30){
                stop();
            }

            super.tick();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }


        @Override
        public void stop() {
            targetable.clear();
            DungeonGuardEntity.this.poundCooldown = 240;
            super.stop();
        }
    }




}
