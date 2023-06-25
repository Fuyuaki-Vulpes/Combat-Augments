package com.fuyuvulpes.combataugments.item.spells;

import com.fuyuvulpes.combataugments.item.SpellPageItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ExplosionSpell extends SpellPageItem {
    public ExplosionSpell(Properties pProperties) {
        super(pProperties,"spell_explosion");
    }


    @Override
    public void doEffect(LivingEntity livingEntity, Level level, Vec3 startPos, Vec3 endPos) {
        BlockHitResult ray = level.clip(new ClipContext(livingEntity.getEyePosition(), endPos, net.minecraft.world.level.ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE,  (Player) livingEntity));

        level.explode(livingEntity, ray.getLocation().x, ray.getLocation().y,ray.getLocation().z,5, Level.ExplosionInteraction.MOB);

    }
}
