package com.fuyuvulpes.combataugments.client.renderer;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.item.SiegeShieldItem;
import com.fuyuvulpes.combataugments.item.SpellBookItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SpellBookRenderer extends GeoItemRenderer<SpellBookItem> {


    public SpellBookRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(CombatantsAugmentsMod.MODID, "spellbook")));
    }
}
