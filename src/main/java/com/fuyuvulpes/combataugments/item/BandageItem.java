package com.fuyuvulpes.combataugments.item;

import com.fuyuvulpes.combataugments.registries.MobEffectRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;

public class BandageItem extends Item {

    public BandageItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!pLevel.isClientSide()){
            pLivingEntity.heal(4);
            if (pLivingEntity.hasEffect(MobEffectRegistry.BLEEDING.get())){
                pLivingEntity.removeEffect(MobEffectRegistry.BLEEDING.get());
            }
            if (pLivingEntity instanceof Player player){
                //player.awardStat( USE BANDAGE );
            }
        }
        return pStack;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return ItemUtils.startUsingInstantly(pLevel,pPlayer,pUsedHand);
    }
}
