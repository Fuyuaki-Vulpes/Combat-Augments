package com.fuyuvulpes.combataugments.item;

import com.fuyuvulpes.combataugments.client.renderer.SiegeShieldRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class SiegeShieldItem extends Item implements Equipable, GeoItem {
    public SiegeShieldItem() {
        super(new Item.Properties().durability(700));
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(itemstack);

    }




    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CUSTOM;
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }


    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(Items.IRON_INGOT) || super.isValidRepairItem(pToRepair, pRepair);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {

        consumer.accept(new IClientItemExtensions() {
            private SiegeShieldRenderer renderer = null;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new SiegeShieldRenderer();

                return this.renderer;
            }

            @Override
            public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                    return SIEGE_BLOCK;
                }

                return HumanoidModel.ArmPose.EMPTY;
            }


            //PI = 180°
            private static final HumanoidModel.ArmPose SIEGE_BLOCK = HumanoidModel.ArmPose.create("SIEGE_BLOCK",true, (model, entity, arm) -> {
                boolean a = arm == HumanoidArm.RIGHT;
                if (a) {
                    model.rightArm.xRot = (-(float) ((Math.PI / 2) * 0.95)) + model.rightArm.xRot;
                    model.rightArm.yRot = (-(float) ((Math.PI / 5) * 1.80)) + model.rightArm.yRot;

                    model.leftArm.xRot = (-(float) ((Math.PI / 2) * 0.80)) + model.leftArm.xRot;
                    model.leftArm.yRot = ((float) ((Math.PI / 5) * 2.30)) + model.leftArm.yRot;
                }
                else {
                    model.rightArm.xRot = (-(float) ((Math.PI / 2) * 0.80)) + model.rightArm.xRot;
                    model.rightArm.yRot = (-(float) ((Math.PI / 5) * 2.30)) + model.rightArm.yRot;

                    model.leftArm.xRot = (-(float) ((Math.PI / 2) * 0.95)) + model.leftArm.xRot;
                    model.leftArm.yRot = ((float) ((Math.PI / 5) * 1.80)) + model.leftArm.yRot;
                }

            });

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                int a = arm == HumanoidArm.RIGHT ? 1 : -1;
                poseStack.translate((float)a * 0.56F, -0.32F, -0.32F);
                poseStack.scale(1.5F, 1.5F, 1.5F);
                if(player.getUseItem() == itemInHand && player.isUsingItem()) {
                    poseStack.translate((float)a * 0.56F, -0.52F + equipProcess * -0.6F, -0.72F);
                    poseStack.translate((float)a * -0.8F, 0.10F, 0.5F);

                    poseStack.mulPose(Axis.ZP.rotationDegrees(a * 16));

                }

                return false;
            }

        });

    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.OFFHAND;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,20));

    }





    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
