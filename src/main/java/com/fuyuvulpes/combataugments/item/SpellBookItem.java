package com.fuyuvulpes.combataugments.item;

import com.fuyuvulpes.combataugments.client.renderer.SpellBookRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SpellBookItem extends Item implements GeoItem {

    public List<SpellPageItem> spellList;

    int spellSlot;


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);



    public SpellBookItem(Properties pProperties) {
        super(pProperties.durability(250));
        spellSlot = 0;
        spellList = new ArrayList<>();
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 250;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 42000;
    }

    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.NONE;
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (spellList != null && spellList.size() > 0){
            SpellPageItem spellPage = getSpellAtIndex(spellSlot);
            pTooltipComponents.add(spellPage.getName());
            pTooltipComponents.add(Component.literal((spellSlot + 1) + " / " + spellList.size()));
        }
        pTooltipComponents.add(Component.translatable("combataugments.spellbook.count").append(spellList.size() + " / 6"));

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        ItemStack offhandStack = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
        if (!pLevel.isClientSide()) {


            if (pPlayer.isCrouching()) {
                cycleSpell();
                return InteractionResultHolder.success(itemstack);
            }

            if (offhandStack.is(Items.AMETHYST_SHARD) && isDamaged(itemstack)) {
                offhandStack.shrink(1);
                itemstack.setDamageValue(Math.max(0, itemstack.getDamageValue() - 12));
                return InteractionResultHolder.success(itemstack);

            }
            if (offhandStack.getItem() instanceof SpellPageItem spellScroll) {
                if (!canAddSpell()) {
                    pPlayer.displayClientMessage(Component.translatable("combataugments.spellbook.tooManySpells").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED), true);

                    return InteractionResultHolder.fail(itemstack);
                }

                addSpell(spellScroll, offhandStack);


                return InteractionResultHolder.success(itemstack);

            }

            if (spellList != null && spellList.size() > 0) {

                pPlayer.startUsingItem(pUsedHand);

                return InteractionResultHolder.consume(itemstack);

            } else {
                pPlayer.displayClientMessage(Component.translatable("combataugments.spellbook.noSpells").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED), true);

                return InteractionResultHolder.fail(itemstack);
            }

        }

        return InteractionResultHolder.pass(itemstack);
    }
    public AABB getUseHitResult(LivingEntity livingEntity, Level level){


        float f = livingEntity.getXRot();
        float f1 = livingEntity.getYRot();
        Vec3 vec3 = livingEntity.getEyePosition();
        float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double range = 12;
        Vec3 vec31 = vec3.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        AABB aabb = new AABB(livingEntity.position(), vec31);
        return aabb;
    }

    public Vec3 getLineEnd(LivingEntity livingEntity, Level level){
        float f = livingEntity.getXRot();
        float f1 = livingEntity.getYRot();
        Vec3 vec3 = livingEntity.getEyePosition();
        float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double range = 16;
        Vec3 vec31 = vec3.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        return vec31;
    }

    public Vec3 getLineWithRange(LivingEntity livingEntity, Level level, double range){
        float f = livingEntity.getXRot();
        float f1 = livingEntity.getYRot();
        Vec3 vec3 = livingEntity.getEyePosition();
        float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec31 = vec3.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        return vec31;
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        BlockHitResult ray = pLevel.clip(new ClipContext(pLivingEntity.getEyePosition(), getLineEnd(pLivingEntity,pLevel), net.minecraft.world.level.ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE,  (Player) pLivingEntity));

        pLevel.addParticle(ParticleTypes.SCULK_SOUL, true, ray.getLocation().x, ray.getLocation().y,ray.getLocation().z ,0,0.1,0);
    }


    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {

        Vec3 blockEffect = pLivingEntity.getEyePosition();

        for (int i = 1; i < 12; i++){
            System.out.println(blockEffect);
            pLevel.addParticle(ParticleTypes.SONIC_BOOM, true, blockEffect.x, blockEffect.y, blockEffect.z,0,0.1,0);

            blockEffect = getLineWithRange(pLivingEntity,pLevel,i);

        }

        SpellPageItem spell = getSpellAtIndex(spellSlot);

        if (spell != null){
            spell.doEffect(pLivingEntity,pLevel,pLivingEntity.getEyePosition(),getLineEnd(pLivingEntity,pLevel));
        }

        pStack.hurtAndBreak(1, pLivingEntity, p -> p.broadcastBreakEvent(pLivingEntity.getUsedItemHand()));

        //EntityHitResult entityHitResult =

    }


    public void updateTag(ItemStack spellBookStack){
        CompoundTag nbt;

        if (spellBookStack.hasTag()){
            nbt = spellBookStack.getTag();
        }
        else {
            nbt = new CompoundTag();
        }

    }


    public SpellPageItem getSpellAtIndex(int index) {
        if (this.spellList.size() < index || index < 0){
            return this.spellList.get(0);
        }
        return this.spellList.get(index);
    }



    public boolean canAddSpell(){
        return this.spellList.size() < 6;
    }

    public void addSpell(SpellPageItem spell, ItemStack stack){

        this.spellList.add(spell);
        stack.shrink(1);

    }


    public void cycleSpell(){
        this.spellSlot++;

        if (this.spellSlot >= this.spellList.size()){
            this.spellSlot = 0;
        }

    }




    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }



    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {

        consumer.accept(new IClientItemExtensions() {
            private SpellBookRenderer renderer = null;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new SpellBookRenderer();

                return this.renderer;
            }
        });
    }
}

