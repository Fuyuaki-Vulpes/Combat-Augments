package com.fuyuvulpes.combataugments.item.spells;

import com.fuyuvulpes.combataugments.item.SpellScrollItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExplosionSpell extends SpellScrollItem {
    public ExplosionSpell(Properties pProperties) {
        super(pProperties,"spell_explosion");
    }


    @Override
    public void doEffect(LivingEntity livingEntity, Level level, Vec3 startPos, Vec3 endPos) {
        level.explode(livingEntity,endPos.x,endPos.y,endPos.z,3, Level.ExplosionInteraction.MOB);
    }
}
