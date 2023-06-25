package com.fuyuvulpes.combataugments.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class DungeonGuardArmItem extends Item{

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID BASE_ATTACK_RANGE_UUID = UUID.fromString("2392ad9f-3020-4db6-ad01-7a397c1ab577");
    protected static final UUID BASE_BLOCK_RANGE_UUID = UUID.fromString("d10c6302-3d4a-4c93-98a8-80265200461b");
    protected static final UUID BASE_ATTACK_DMG_UUID = UUID.fromString("e4ead38c-8c4a-41d4-b2ae-350421c90809");





    public DungeonGuardArmItem() {
        super(new Item.Properties().fireResistant().stacksTo(1));

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ATTACK_RANGE_UUID, "Item Modifier", 3.0, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(BASE_BLOCK_RANGE_UUID, "Item Modifier", 3.0, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DMG_UUID, "Item Modifier", 3.0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();

    }


    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pSlot) {
        return pSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pSlot);

    }




}
