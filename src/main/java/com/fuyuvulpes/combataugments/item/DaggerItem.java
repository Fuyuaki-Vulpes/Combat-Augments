package com.fuyuvulpes.combataugments.item;

import com.fuyuvulpes.combataugments.entity.DaggerEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class DaggerItem extends WeaponItem {
    public DaggerItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, float pRangeModifier, float speedModifier, boolean canStun, boolean causeBleeding, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pRangeModifier, speedModifier, canStun, causeBleeding, pProperties);
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CROSSBOW;
    }


    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }


    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pTimeLeft;
            if (i >= 10) {
                if (!pLevel.isClientSide) {
                    pStack.hurtAndBreak(1, player, (p_43388_) -> {
                        p_43388_.broadcastBreakEvent(pEntityLiving.getUsedItemHand());
                    });
                    DaggerEntity dagger = new DaggerEntity(pLevel, player, pStack);
                    dagger.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F + 0.5F, 1.0F);
                    if (player.getAbilities().instabuild) {
                        dagger.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    pLevel.addFreshEntity(dagger);
                    pLevel.playSound((Player) null, dagger, SoundEvents.LINGERING_POTION_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        player.getInventory().removeItem(pStack);
                    }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
            }
        }

    }
}
