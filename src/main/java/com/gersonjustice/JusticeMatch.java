package com.gersonjustice;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/** Match the real mod item and exact anvil-assigned name. */
public final class JusticeMatch {
    public static final ResourceLocation ITEM_ID = ResourceLocation.parse("alexsmobs:rocky_chestplate");
    public static final ResourceLocation TEXTURE = ResourceLocation.parse("gersonjustice:textures/armor/rocky_chestplate_justice.png");
    private JusticeMatch() {}
    public static boolean matches(ItemStack stack) {
        if (stack.isEmpty() || !BuiltInRegistries.ITEM.getKey(stack.getItem()).equals(ITEM_ID)) return false;
        var name = stack.get(DataComponents.CUSTOM_NAME);
        return name != null && name.getString().equals("Chestplate of Justice");
    }
}
