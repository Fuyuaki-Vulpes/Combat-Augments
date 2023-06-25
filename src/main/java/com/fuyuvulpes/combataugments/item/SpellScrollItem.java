package com.fuyuvulpes.combataugments.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpellScrollItem extends Item {

    public SpellScrollItem(Properties pProperties) {
        super(pProperties.rarity(Rarity.RARE).stacksTo(8));

    }



    public void doEffect(LivingEntity livingEntity, Level level, Vec3 startPos, Vec3 endPos){

    }
}
