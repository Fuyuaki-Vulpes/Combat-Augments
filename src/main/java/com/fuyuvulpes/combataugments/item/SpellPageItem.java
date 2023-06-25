package com.fuyuvulpes.combataugments.item;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpellPageItem extends Item {

    private final String name;

    public SpellPageItem(Properties pProperties, String pName) {
        super(pProperties.rarity(Rarity.RARE).stacksTo(8));

        this.name = pName;

    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("item", new ResourceLocation(CombatantsAugmentsMod.MODID,"spell_page"));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable("item.combataugments." + name));
    }

    public Component getName(){
        return Component.translatable("item.combataugments." + name);
    }


    public void doEffect(LivingEntity livingEntity, Level level, Vec3 startPos, Vec3 endPos){

    }
}
