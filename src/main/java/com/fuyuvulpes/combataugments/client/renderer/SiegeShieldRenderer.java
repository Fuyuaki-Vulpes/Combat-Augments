package com.fuyuvulpes.combataugments.client.renderer;

import com.fuyuvulpes.combataugments.CombatantsAugmentsMod;
import com.fuyuvulpes.combataugments.item.SiegeShieldItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SiegeShieldRenderer extends GeoItemRenderer<SiegeShieldItem> {


    public SiegeShieldRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(CombatantsAugmentsMod.MODID, "siege_shield")));
    }
}
