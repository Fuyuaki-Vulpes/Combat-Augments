package com.fuyuvulpes.combataugments.item;

import com.fuyuvulpes.combataugments.registries.MobEffectRegistry;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.UUID;

public class WeaponItem extends SwordItem {

    protected static final UUID BASE_ATTACK_RANGE_UUID = UUID.fromString("936a4647-bfc5-4996-a7a7-73558d82ddaf");
    private static final UUID BASE_MOVEMENT_SPEED_UUID = UUID.fromString("641e31c6-a5f8-4201-b88f-e4a7411e6859");


    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public final float attackRange;

    public final boolean canStun;

    public final boolean causeBleeding;

    public WeaponItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, float pRangeModifier, float speedModifier, boolean canStun, boolean causeBleeding, Properties pProperties) {
        super(pTier, pAttackDamageModifier,pAttackSpeedModifier,pProperties.defaultDurability((int) (pTier.getUses() * 1.3)));
        this.attackRange = pRangeModifier;
        this.canStun = canStun;
        this.causeBleeding = causeBleeding;
        this.attackDamage = (float)pAttackDamageModifier + pTier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)pAttackSpeedModifier, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.ENTITY_REACH.get(),new AttributeModifier(BASE_ATTACK_RANGE_UUID, "Weapon modifier", (double) this.attackRange, AttributeModifier.Operation.ADDITION));
        if (speedModifier != 0) {
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_UUID, "Weapon Modifier", (double) speedModifier, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        this.defaultModifiers = builder.build();
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }


    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }


    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        if (canStun && pTarget.level().getRandom().nextBoolean()){
            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,40, 25));
            pTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,40, 25));
            pTarget.addEffect(new MobEffectInstance(MobEffects.CONFUSION,40, 25));
            pTarget.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,40, 25));
            pTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,40, 25));
        }
        if (causeBleeding && RandomSource.create().nextBoolean()){
            pTarget.addEffect(new MobEffectInstance(MobEffectRegistry.BLEEDING.get(),120, 1));

        }
        return true;

    }


    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        return false;
    }



}
