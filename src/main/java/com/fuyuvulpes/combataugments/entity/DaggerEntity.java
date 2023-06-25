package com.fuyuvulpes.combataugments.entity;

import com.fuyuvulpes.combataugments.item.DaggerItem;
import com.fuyuvulpes.combataugments.registries.EntityTypeRegistry;
import com.fuyuvulpes.combataugments.registries.ItemRegistry;
import com.fuyuvulpes.combataugments.registries.MobEffectRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class DaggerEntity extends AbstractArrow {

    private boolean dealtDamage;


    public int clientSideReturnTick;

    private ItemStack daggerItem = new ItemStack(ItemRegistry.STONE_DAGGER.get());

    public DaggerEntity(EntityType<? extends DaggerEntity> pEntityType, Level level) {
        super(EntityTypeRegistry.DAGGER.get(), level);
    }

    public DaggerEntity(Level pLevel, LivingEntity pShooter, ItemStack pStack) {
        super(EntityTypeRegistry.DAGGER.get(), pShooter, pLevel);
        this.daggerItem = pStack.copy();
    }



    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        if (this.dealtDamage || this.isNoPhysics() && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.05D;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnTick == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTick;
            }
        }


        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.daggerItem.getItem() instanceof DaggerItem daggerItem1){
            this.dealtDamage = true;
            Entity entity = pResult.getEntity();
            entity.hurt(this.damageSources().playerAttack((Player) Objects.requireNonNull(this.getOwner())),daggerItem1.getAttackDamage());
            if (entity instanceof LivingEntity livingentity) {
                livingentity.addEffect(new MobEffectInstance(MobEffectRegistry.BLEEDING.get(),80));
            }
        }


    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ARROW_HIT;
    }

    public void playerTouch(Player pEntity) {
        if (this.ownedBy(pEntity) || this.getOwner() == null) {
            super.playerTouch(pEntity);
        }

    }



    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer) || this.isNoPhysics() && this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
    }



    @Override
    protected ItemStack getPickupItem() {
        return daggerItem;
    }


    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }



}
